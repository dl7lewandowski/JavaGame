package agh.cs.lab4;

import org.junit.Test;
import agh.cs.lab4.MapDirection;
import agh.cs.lab4.MoveDirection;
import agh.cs.lab4.RectangularMap;

import static org.junit.Assert.assertEquals;

public class AnimalTest {
    private Animal animal = new Animal(new RectangularMap(5,5));

    @Test
    public void MoveTest() {
        assertEquals("", new Vector2d(2,2), animal.getPosition());
        assertEquals("", MapDirection.NORTH, animal.getOrientation());
        animal.move(MoveDirection.RIGHT);
        assertEquals("", MapDirection.EAST, animal.getOrientation());
        assertEquals("", new Vector2d(2,2), animal.getPosition());
        animal.move(MoveDirection.LEFT);
        assertEquals("", MapDirection.NORTH, animal.getOrientation());
        animal.move(MoveDirection.LEFT);
        assertEquals("", MapDirection.WEST, animal.getOrientation());
        animal.move(MoveDirection.FORWARD);
        assertEquals("", new Vector2d(1,2), animal.getPosition());
        animal.move(MoveDirection.FORWARD);
        assertEquals("", new Vector2d(0,2), animal.getPosition());
        animal.move(MoveDirection.FORWARD);
        assertEquals("", new Vector2d(4,2), animal.getPosition());
        animal.move(MoveDirection.LEFT);
        assertEquals("", MapDirection.SOUTH, animal.getOrientation());
        animal.move(MoveDirection.FORWARD);
        assertEquals("", new Vector2d(4,1), animal.getPosition());
    }



}



