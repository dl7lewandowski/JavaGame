package agh.cs.lab4;



import java.util.Objects;

public class Animal {
    private MapDirection orientation;
    private  Vector2d position;
    private final IWorldMap map;


    public Animal(IWorldMap map){
        this.map = map;
        this.position = new Vector2d(2,2);
        this.orientation = MapDirection.NORTH;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
        this.orientation = MapDirection.NORTH;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }


    @Override
    public String toString() {
        switch (orientation) {
            case NORTH -> {
                return "N";
            }
            case EAST -> {
                return "E";
            }
            case WEST -> {
                return "W";
            }
            default -> {
                return "S";
            }
        }
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



    public void move(MoveDirection direction) {
        Vector2d new_position;
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> {
                new_position = map.targetPosition(this.position.add(this.orientation.toUnitVector()));
                if (this.map.canMoveTo(new_position)) {
                    this.position = new_position;
                }
            }
            default -> {
                new_position = map.targetPosition(this.position.subtract(this.orientation.toUnitVector()));
                if (this.map.canMoveTo(new_position)) {
                    this.position = new_position;
                }
            }
        }
    }

}

