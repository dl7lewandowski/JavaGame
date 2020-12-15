package agh.cs.lab4;

public class World {
    public static void main(String[] args) {
        args = new String[] {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = OptionParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        map.place(new Animal(map));
        map.place(new Animal(map,new Vector2d(3,4)));
        IEngine engine = new SimulationEngine(map);
        engine.run(directions);
        System.out.println(map);

    }
}

