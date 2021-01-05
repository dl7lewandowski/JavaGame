package agh.cs.lab8;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public abstract class AbstractMapWorld implements IWorldMaps, IWorldChangePosition {

    protected int widthX;
    protected int heightY;
    protected List<Animal> listAnimal;
    protected List<Animal> deadAnimals;
    protected Map<Vector2d, List<Animal>> animalVector;
    private int counter =0;

    public AbstractMapWorld(int widthX, int heightY) {
        this.widthX = widthX;
        this.heightY = heightY;
        this.animalVector = new ConcurrentHashMap<>();
        this.listAnimal = Collections.synchronizedList(new LinkedList<>());
        this.deadAnimals = Collections.synchronizedList(new LinkedList<>());
    }

    public Map<Vector2d, List<Animal>> getAnimalsPositions() {
        return animalVector;
    }

    public List<Animal> getAnimals() {
        return listAnimal;
    }

    public List<Animal> getDeadAnimals() {
        return deadAnimals;
    }

    public int getWidthX() {
        return widthX;
    }

    public int getHeightY() {
        return heightY;
    }

    public void place(Animal a) {
        this.listAnimal.add(a);
        this.animalVector.computeIfAbsent(a.getPosition(), k -> new LinkedList<>());
        this.animalVector.get(a.getPosition()).add(a);
    }

    @Override
    public Vector2d targetPosition(Vector2d vector2d) {
        if(vector2d.getX() < widthX && vector2d.getX() >= 0) {
            if(vector2d.getY() < heightY && vector2d.getY() >= 0) {
                return vector2d;
            }
            else {
                int y = vector2d.getY()%heightY;
                if (y<0) y += heightY;
                return new Vector2d(vector2d.getX(), y);
            }
        }
        else {
            if(vector2d.getY() < heightY && vector2d.getY() >= 0) {
                int x = vector2d.getX()%widthX;
                if (x<0) x += widthX;
                return new Vector2d(x, vector2d.getY());
            }
            else {
                int x = vector2d.getX()%widthX;
                if (x<0) x += widthX;
                int y = vector2d.getY()%heightY;
                if (y<0) y += heightY;
                return new Vector2d(x, y);
            }
        }
    }

    public Vector2d[] borders() {
        return new Vector2d[] {new Vector2d(0,0), new Vector2d(this.widthX, this.heightY)};
    }

    @Override
    public void positionChanged(Vector2d vector2d, Vector2d nextVector2d, Animal a) {
        this.animalVector.get(vector2d).remove(a);
        List<Animal> elements = this.animalVector.get(nextVector2d);
        if(elements==null) {
            this.animalVector.put(nextVector2d, new LinkedList<>());
            this.animalVector.get(nextVector2d).add(a);
        }
        else {
            elements.add(a);
        }
    }

    @Override
    public void dead(Animal a) {
        this.animalVector.get(a.getPosition()).remove(a);
        this.listAnimal.remove(a);
        this.deadAnimals.add(a);
    }

    public boolean isOccupied(Vector2d vector2d) {
        if(this.animalVector.get(vector2d) == null) {
            return false;
        }
        else {
            if(this.animalVector.get(vector2d).size() == 0) {
                return false;
            }
            else {
                return true;
            }
        }
    }


    public String mostCommonGenotype() {
        if(listAnimal.size() == 0) {
            return "";
        }
        List<String> genotypes = listAnimal.stream().map(Animal::getDnaText).collect(Collectors.toList());
        Optional<Map.Entry<String, Long>> result = genotypes.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue));
        return result.get().getKey();
    }

    public int getCounter() {
        return counter;
    }

    public void updateCounter() {
        this.counter += 1;
    }
}
