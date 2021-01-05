package agh.cs.lab8;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class AreaWorld extends AbstractMapWorld {
    private final Map<Vector2d, Grass> grass = new ConcurrentHashMap<>();
    protected Vector2d vector2dLeftDown;
    protected Vector2d vector2dRightUp;
    protected int energy;
    protected int grassEnergy;
    public int firstDay = 0;


    public AreaWorld(int w, int h, Vector2d vector2dLeftDown, Vector2d vector2dRightUp, int grassEnergy, int nGrass,
                  int energy, int nAnimals) {
        super(w, h);
        this.vector2dLeftDown = vector2dLeftDown;
        this.vector2dRightUp = vector2dRightUp;
        this.grassEnergy = grassEnergy;
        this.initAnimals(nAnimals);
        this.energy = energy;
        this.initGrass(nGrass);
    }

    public Map<Vector2d, Grass> getGrass() {
        return grass;
    }

    public Vector2d getVector2dRightUp() {
        return vector2dRightUp;
    }

    public Vector2d getVector2dLeftDown() {
        return vector2dLeftDown;
    }

    private void initGrass(int n) {
        List<Integer> places = ThreadLocalRandom.current().ints(0,
                this.heightY*this.widthX).distinct().limit(n).boxed().collect(Collectors.toList());
        for(int i:places) {
            Vector2d v = new Vector2d(i/this.heightY,i%this.heightY);
            this.grass.put(v,new Grass(v, this.grassEnergy));
        }
    }

    private void initAnimals(int n) {
        List<Integer> places = ThreadLocalRandom.current().ints(0,
                this.heightY*this.widthX).distinct().limit(n).boxed().collect(Collectors.toList());
        for(int i:places) {
            Vector2d v = new Vector2d(i/this.heightY,i%this.heightY);
            Animal animal = new Animal(this, this.energy, v);
            this.listAnimal.add(animal);
            this.animalVector.put(v, new LinkedList<>());
            this.animalVector.get(v).add(animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if(super.isOccupied(position)) {
            return true;
        }
        else {
            return this.grass.get(position) != null;
        }
    }

    public void addNewGrass() {
        List<Integer> place = ThreadLocalRandom.current().ints(0,
                this.heightY*this.widthX).distinct().limit((long) this.heightY *this.widthX).boxed().collect(Collectors.toList());
        boolean flag1=false, flag2=false;
        for(int i:place) {
            Vector2d v = new Vector2d(i/this.heightY,i%this.heightY);
            if(!this.isOccupied(v)) {
                if(v.getX() < this.vector2dLeftDown.getX() || v.getX() > this.vector2dRightUp.getX() || v.getY() < this.vector2dLeftDown.getY() || v.getY() > this.vector2dRightUp.getY()) {
                    if(!flag1) {
                        this.grass.put(v,new Grass(v, this.grassEnergy));
                        flag1 = true;
                    }
                }
                else {
                    if(!flag2) {
                        this.grass.put(v,new Grass(v, this.grassEnergy));
                        flag2 = true;
                    }
                }
            }
        }
    }

    public void delete() {
        firstDay += 1;
        for (int i = 0; i < this.listAnimal.size(); i++)
        {
            listAnimal.get(i).consumeOfEnergy();
        }
    }

    public void nextStep() {
        for(Animal animal:this.listAnimal) {
            animal.animalRotate();
            animal.moveAnimal();
        }
    }

    public void consume() {
        for (Map.Entry<Vector2d,Grass> entry : this.grass.entrySet()){
            List<Animal> animalsAtPlace = this.animalVector.get(entry.getKey());
            if(animalsAtPlace != null) {
                if(animalsAtPlace.size() != 0) {
                    animalsAtPlace = animalsAtPlace.stream()
                            .collect(Collectors.groupingBy(
                                    Animal::getEnergy,
                                    TreeMap::new,
                                    Collectors.toList()
                            ))
                            .lastEntry()
                            .getValue();
                    int energy = this.grassEnergy/animalsAtPlace.size();
                    for(Animal animal:animalsAtPlace) {
                        animal.addEnergy(energy);
                    }
                    this.grass.remove(entry.getKey());
                }
            }
        }
    }

    public void createAnimal() {
        for (Map.Entry<Vector2d, List<Animal>> entry : this.animalVector.entrySet()) {
            List<Animal> animalsAtPlace = this.animalVector.get(entry.getKey());
            if (animalsAtPlace != null) {
                if (animalsAtPlace.size() >= 2) {
                    animalsAtPlace.sort(Comparator.comparing(Animal::getEnergy));
                    Collections.reverse(animalsAtPlace);
                    if (animalsAtPlace.get(1).getEnergy() >= this.energy / 2) {
                        this.place(Animal.reproduceAnimal(animalsAtPlace.get(0), animalsAtPlace.get(1)));
                    }
                }
            }
        }
    }

    public String dataAnimal(int identity) {
        StringBuilder data = new StringBuilder();
        Animal a = listAnimal.stream().filter(obj -> obj.getId() == identity).findFirst().orElse(null);
        if(a != null) {
            data.append("IDENTITY: ").append(identity).append("\n");
            data.append("Animal DNA: ").append(a.getDnaText()).append("\n");
            data.append("Age animal: ").append(a.getDaysAlive()).append("\n");
            data.append("energy animal: ").append(a.getEnergy()).append("\n");
            data.append("position animal: ").append(a.getPosition()).append("\n");
            data.append("children animal: ").append(a.getKids().size()).append("\n");
            data.append("descendant animal: ").append(a.numberOfDescendant());
        }
        else {
            a = deadAnimals.stream().filter(obj -> obj.getId() == identity).findFirst().orElse(null);
            if(a != null) {
                data.append("Not a live this animal").append("\n");
                data.append("Identity: ").append(identity).append("\n");
                data.append("DNA animal: ").append(a.getDnaText()).append("\n");
                data.append("How long lived: ").append(a.getDaysAlive()).append("\n");
                data.append("Children animal: ").append(a.getKids().size()).append("\n");
                data.append("Descendant anmial: ").append(a.numberOfDescendant());
            }
            else {
                data.append("No animal with this id").append(identity);
            }
        }
        return data.toString();
    }
}
