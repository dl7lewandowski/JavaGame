package agh.cs.lab8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum MapDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    public static List<MapDirection> VALUE = List.of(values());
    public static int SIZE = VALUE.size();
    private static final Random RAND = new Random();

    @Override
    public String toString() {
        return switch (this) {
            case NORTH -> ("Polnoc");
            case SOUTH -> ("Poludnie");
            case EAST -> ("Wschod");
            case NORTHEAST -> ("Polnocny wschod");
            case SOUTHEAST -> ("Poludniowy wschod");
            case SOUTHWEST -> ("Poludniowy zachod");
            case NORTHWEST -> ("Połnocny zachod");
            default -> ("Zachod");
        };
    }

    public MapDirection next() {
        return switch (this) {
            case NORTH -> (NORTHEAST);
            case NORTHEAST -> (EAST);
            case EAST -> (SOUTHEAST);
            case SOUTHEAST -> (SOUTH);
            case SOUTH -> (SOUTHWEST);
            case SOUTHWEST -> (WEST);
            case WEST -> (NORTHWEST);
            default -> (NORTH);
        };
    }

    public MapDirection next(int n) {
        n = n%8;
        MapDirection direction = this;
        for(int i=0;i<n;i++) {
            direction = direction.next();
        }
        return direction;
    }

    public Vector2d toUnitVector() {
        return switch (this) {
            case EAST -> (new Vector2d(1, 0));
            case SOUTH -> (new Vector2d(0, -1));
            case WEST -> (new Vector2d(-1, 0));
            case NORTHEAST -> (new Vector2d(1, 1));
            case SOUTHEAST -> (new Vector2d(1, -1));
            case SOUTHWEST -> (new Vector2d(-1, -1));
            case NORTHWEST -> (new Vector2d(-1, 1));
            default -> (new Vector2d(0, 1));
        };
    }

    public static MapDirection randomDirection()  {
        return VALUE.get(RAND.nextInt(SIZE));
    }

    public static List<MapDirection> randomDirections()  {
        List<MapDirection> listDirection = new ArrayList<>(VALUE);
        Collections.shuffle(listDirection);
        return listDirection;
    }
}
