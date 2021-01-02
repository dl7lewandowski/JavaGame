package agh.cs.lab5;
import static org.junit.Assert.assertEquals;

import agh.cs.lab5.MapDirection;
import org.junit.Test;

public class MapDirectionTest {

    @Test
    public void nextDirectionTest() {
        assertEquals("NORTH to EAST", MapDirection.EAST, MapDirection.NORTH.next());
        assertEquals("EAST to SOUTH", MapDirection.SOUTH, MapDirection.EAST.next());
        assertEquals("SOUTH to WEST", MapDirection.WEST, MapDirection.SOUTH.next());
        assertEquals("WEST to NORTH", MapDirection.NORTH, MapDirection.WEST.next());
    }

    @Test
    public void previousDirectionTest() {
        assertEquals("EAST to NORTH", MapDirection.NORTH, MapDirection.EAST.previous());
        assertEquals("SOUTH to EAST", MapDirection.EAST, MapDirection.SOUTH.previous());
        assertEquals("WEST to SOUTH", MapDirection.SOUTH, MapDirection.WEST.previous());
        assertEquals("NORTH to WEST", MapDirection.WEST, MapDirection.NORTH.previous());
    }
}
