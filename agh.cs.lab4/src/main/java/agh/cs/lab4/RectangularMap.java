package agh.cs.lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class RectangularMap implements IWorldMap {
    private final int width;
    private final int height;
    private final List<Animal> animals;


    public List<Animal> getAnimals(){
        return animals;
    }
    public RectangularMap(int width,int height){
        this.height = height;
        this.width = width;
        this.animals = new ArrayList<Animal>();
    }
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    public boolean place(Animal animal) {
        if(!isOccupied(animal.getPosition())) {
            this.animals.add(animal);
            return(true);
        }
        return(false);
    }

    public boolean isOccupied(Vector2d position) {
        return(!Objects.isNull(objectAt(position)));
    }

    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getPosition().equals(position)) {
                return(animal);
            }
        }
        return(null);
    }


    @Override
    public String toString() {
        MapVisualizer mv = new MapVisualizer(this);
        return(mv.draw(new Vector2d(0,0), new Vector2d(this.width-1,this.height-1)));
    }

    @Override
    public Vector2d targetPosition(Vector2d position) {
        if(position.getX() < width && position.getX() >= 0) {
            if(position.getY() < height && position.getY() >= 0) {
                return position;
            }
            else {
                int y = position.getY()%height;
                if (y<0) y += height;
                return new Vector2d(position.getX(), y);
            }
        }
        else {
            if(position.getX() < height && position.getY() >= 0) {
                int x = position.getX()%width;
                if (x<0) x += width;
                return new Vector2d(x, position.getY());
            }
            else {
                int x = position.getX()%width;
                if (x<0) x += width;
                int y = position.getY()%height;
                if (y<0) y += height;
                return new Vector2d(x, y);
            }
        }
    }
}
