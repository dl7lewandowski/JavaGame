package agh.cs.lab5;
import static org.junit.Assert.assertEquals;

import agh.cs.lab5.MapDirection;
import agh.cs.lab5.MoveDirection;
import agh.cs.lab5.RectangularMap;
import org.junit.Test;

public class AnimalTest {
    private Animal animal = new Animal(new RectangularMap(4,4));

    @Test
    public void moveTest() {
        assertEquals("", new Vector2d(2,2), animal.getPosition());
        animal.move(MoveDirection.RIGHT);
        assertEquals("", MapDirection.EAST, animal.getOrientation());
        animal.move(MoveDirection.FORWARD);
        assertEquals("", new Vector2d(3,2), animal.getPosition());
    }

}
