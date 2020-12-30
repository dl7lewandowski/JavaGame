package agh.cs.lab5;

import agh.cs.lab5.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MapTest {

    @Test
    public void rectangularMapRunTest() {
        String[] args = new String[] {"f","b","r","l","f","f","r","r","f","f","f","f","f","f","f","f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(map);
        Animal animal2 = new Animal(map,new Vector2d(3,4));
        IEngine engine = new MapEngine(map);
        map.place(animal1);
        map.place(animal2);
        engine.run(directions);
        assertEquals("first animal", animal1, map.objectAt(new Vector2d(2,4)));
        assertEquals("second animal", animal2, map.objectAt(new Vector2d(3,2)));
        assertEquals("expect South", animal1.getOrientation(), MapDirection.SOUTH);
        assertEquals("expect NORTH", animal2.getOrientation(), MapDirection.NORTH);
    }

    @Test
    public void grassMapRunTest() {
        String[] args = new String[] {"f","f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new GrassField(10, 5, 50);
        Animal animal = new Animal(map,new Vector2d(3,4));
        IEngine engine = new MapEngine(map);
        map.place(animal);
        engine.run(directions);
        assertEquals("first animal", animal, map.objectAt(new Vector2d(3,1)));
        assertEquals("direction NORTH", animal.getOrientation(), MapDirection.NORTH);
        assertTrue("should be grass", map.objectAt(new Vector2d(0, 1)) instanceof Grass);
    }

}
