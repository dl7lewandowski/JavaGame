package agh.cs.lab4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapTest {

    @Test
    public void mapRunTest(){
        String[] args = new String[] {"f","b","r","l","f","f","r","r","f","f","f","f","f","f","f","f"};
        MoveDirection[] directions = OptionParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(map);
        Animal animal2 = new Animal(map,new Vector2d(3,4));
        IEngine engine = new SimulationEngine(map);
        map.place(animal1);
        map.place(animal2);
        engine.run(directions);
        assertEquals("", animal1, map.objectAt(new Vector2d(2,4)));
        assertEquals("", animal2, map.objectAt(new Vector2d(3,2)));
        assertEquals("", animal1.getOrientation(), MapDirection.SOUTH);
        assertEquals("", animal2.getOrientation(), MapDirection.NORTH);
    }
}
