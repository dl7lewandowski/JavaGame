package agh.cs.lab8;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Animal implements IWorldMapElement {
    private MapDirection orientation;
    private Vector2d vector2d;
    private final int idAnimal;
    private final AbstractMapWorld mapWorld;
    private int animalEnergy;
    private int daysOfLive=0;
    private final List<IWorldChangePosition> positions = new LinkedList<>();
    private final List<Integer> dna;
    public static int energyMove=1;
    private final LinkedList<Animal> animalKids = new LinkedList<>();


    public Animal(AbstractMapWorld mapWorld, int animalEnergy, Vector2d vector2d) {
        this.mapWorld = mapWorld;
        this.positions.add(mapWorld);
        this.vector2d = vector2d;
        this.orientation = MapDirection.randomDirection();
        this.dna = DNA.createDNA();
        this.animalEnergy = animalEnergy;
        this.idAnimal = mapWorld.getCounter();
        mapWorld.updateCounter();
    }

    public Animal(AbstractMapWorld mapWorld, int energyAnimal, Vector2d positionAnimal, List<Integer> dna) {
        this.mapWorld = mapWorld;
        this.positions.add(mapWorld);
        this.vector2d = positionAnimal;
        this.orientation = MapDirection.randomDirection();
        this.dna = dna;
        this.animalEnergy = energyAnimal;
        this.idAnimal = mapWorld.getCounter();
        mapWorld.updateCounter();
    }

    @Override
    public Vector2d getPosition() {
        return vector2d;
    }

    public int getEnergy() {
        return animalEnergy;
    }

    public List<Animal> getKids() {
        return animalKids;
    }

    public int getDaysAlive() {
        return daysOfLive;
    }

    public int getId() {
        return idAnimal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return mapWorld == animal.mapWorld &&
                orientation == animal.orientation &&
                Objects.equals(vector2d, animal.vector2d);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientation, vector2d);
    }

    public void moveAnimal() {
        Vector2d startPosition = this.vector2d;
        Vector2d nextPosition = mapWorld.targetPosition(this.vector2d.add(this.orientation.toUnitVector()));
        this.vector2d = nextPosition;
        this.nextPosition(startPosition, nextPosition);
    }

    private void nextPosition(Vector2d startPosition, Vector2d nextPosition) {
        for (IWorldChangePosition o:this.positions) {
            o.positionChanged(startPosition, nextPosition, this);
        }
    }

    private void death() {
        for (IWorldChangePosition animal:this.positions) {
            animal.dead(this);
        }
    }

    public void consumeOfEnergy(){
        this.daysOfLive = daysOfLive + 1;
        this.animalEnergy = animalEnergy - energyMove;
        if(this.animalEnergy <= 0) {
            death();
        }
    }

    public void animalRotate(){
        int random_idx = ThreadLocalRandom.current().nextInt(0, Setting.dnaLen);
        this.orientation = this.orientation.next(this.dna.get(random_idx));
    }

    public static Animal reproduceAnimal(Animal a1, Animal a2) {

        int energy = a1.animalEnergy/4+a2.animalEnergy/4;
        a1.animalEnergy -= a1.animalEnergy/4;
        a2.animalEnergy -= a2.animalEnergy/4;
        Vector2d vector2d = a1.vector2d;
        for(MapDirection d:MapDirection.randomDirections()) {
            Vector2d nextPosition = a1.mapWorld.targetPosition(vector2d.add(d.toUnitVector()));
            if (!a1.mapWorld.isOccupied(nextPosition)) {
                vector2d = nextPosition;
                break;
            }
        }
        if(vector2d == a1.vector2d) {
            vector2d = a1.mapWorld.targetPosition(vector2d.add(MapDirection.randomDirections().get(0).toUnitVector()));
        }
        Animal kid = new Animal(a1.mapWorld, energy, vector2d,
                DNA.recreateDNA(a1.dna, a2.dna));
        a1.animalKids.add(kid);
        a2.animalKids.add(kid);
        return kid;
    }

    public void addEnergy(int energy) {
        this.animalEnergy += energy;
    }

    public String getDnaText() {
        return dna.toString();
    }

    public int numberOfDescendant() {
        int number =0;
        HashSet<Animal> setAnimal = new HashSet<>();
        LinkedList<Animal> check = (LinkedList) animalKids.clone();
        while (check.size() != 0) {
            Animal animal = check.poll();
            setAnimal.add(animal);
            number += 1;
            for(int i=0;i<animal.animalKids.size();i++) {
                if(!setAnimal.contains(animal.animalKids.get(i))) {
                    check.add(animal.animalKids.get(i));
                }
            }
        }
        return number;
    }
}