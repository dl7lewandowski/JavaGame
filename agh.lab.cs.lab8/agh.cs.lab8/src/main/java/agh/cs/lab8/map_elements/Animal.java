package agh.cs.lab8.map_elements;

import agh.cs.lab8.maps.*;
import agh.cs.lab8.utils.Config;
import agh.cs.lab8.utils.DNAUtils;
import agh.cs.lab8.utils.Vector2d;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Animal implements IMapElement {
    private MapDirection orientation;
    private Vector2d position;
    private final AbstractWorldMap map;
    private int energy;
    public static int moveEnergy=1;
    private final int id;
    private int daysAlive=0;
    private final List<Integer> dna;
    private final List<IPositionChangeObserver> observers = new LinkedList<>();
    private final LinkedList<Animal> kids = new LinkedList<>();


    public Animal(AbstractWorldMap map, int energy, Vector2d initialPosition) {
        this.map = map;
        this.observers.add(map);
        this.position = initialPosition;
        this.orientation = MapDirection.randomDirection();
        this.dna = DNAUtils.drawDNA();
        this.energy = energy;
        this.id = map.getCounter();
        map.updateCounter();
    }

    public Animal(AbstractWorldMap map, int energy, Vector2d initialPosition, List<Integer> dna) {
        this.map = map;
        this.observers.add(map);
        this.position = initialPosition;
        this.orientation = MapDirection.randomDirection();
        this.dna = dna;
        this.energy = energy;
        this.id = map.getCounter();
        map.updateCounter();
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }

    public List<Animal> getKids() {
        return kids;
    }

    public int getDaysAlive() {
        return daysAlive;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return map == animal.map &&
                orientation == animal.orientation &&
                Objects.equals(position, animal.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientation, position);
    }

    public void move() {
        Vector2d oldPosition = this.position;
        Vector2d future_position = map.targetPosition(this.position.add(this.orientation.toUnitVector()));
        this.position = future_position;
        this.positionChanged(oldPosition, future_position);
    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver o:this.observers) {
            o.positionChanged(oldPosition, newPosition, this);
        }
    }

    void dead() {
        for (IPositionChangeObserver o:this.observers) {
            o.dead(this);
        }
    }

    public void useEnergy(){
        this.daysAlive += 1;
        this.energy -= moveEnergy;
        if(this.energy <= 0) {
            dead();
        }
    }

    public void rotate(){
        int rand_idx = ThreadLocalRandom.current().nextInt(0,Config.dnaLength);
        this.orientation = this.orientation.next(this.dna.get(rand_idx));
    }

    public static Animal reproduce(Animal animal1, Animal animal2) {

        int energy = animal1.energy/4+animal2.energy/4;
        animal1.energy -= animal1.energy/4;
        animal2.energy -= animal2.energy/4;
        Vector2d position = animal1.position;
        for(MapDirection d:MapDirection.randomDirections()) {
            Vector2d future_position = animal1.map.targetPosition(position.add(d.toUnitVector()));
            if (!animal1.map.isOccupied(future_position)) {
                position = future_position;
                break;
            }
        }
        if(position == animal1.position) {
            position = animal1.map.targetPosition(position.add(MapDirection.randomDirections().get(0).toUnitVector()));
        }
        Animal child = new Animal(animal1.map, energy, position,
                DNAUtils.recombineDNA(animal1.dna, animal2.dna));
        animal1.kids.add(child);
        animal2.kids.add(child);
        return child;
    }

    public void addEnergy(int energy) {
        this.energy += energy;
    }

    public String getDnaString() {
        return dna.toString();
    }

    public int numberOfDescendant() {
        int result =0;
        HashSet<Animal> animalSet = new HashSet<>();
        LinkedList<Animal> queue = (LinkedList) kids.clone();
        while (queue.size() != 0) {
            Animal a = queue.poll();
            animalSet.add(a);
            result += 1;
            for(int i=0;i<a.kids.size();i++) {
                if(!animalSet.contains(a.kids.get(i))) {
                    queue.add(a.kids.get(i));
                }
            }
        }
        return result;
    }
}
