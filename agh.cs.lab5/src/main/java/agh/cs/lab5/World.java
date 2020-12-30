package agh.cs.lab5;

import agh.cs.lab5.*;

public class World {

    public static void main(String[] args) {
        try {
            args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
            MoveDirection[] directions = OptionsParser.parse(args);
            IWorldMap map = new GrassField(10, 5, 10);
            map.place(new Animal(map));
            map.place(new Animal(map, new Vector2d(3, 4)));
            map.place(new Animal(map, new Vector2d(5, 4)));
            IEngine engine = new MapEngine(map);
            engine.run(directions);
            System.out.println(map);
        }
        catch(IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}
