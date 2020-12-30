package agh.cs.lab5;

import agh.cs.lab5.Grass;
import agh.cs.lab5.Vector2d;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class GrassField extends AbstractWorldMap {

    private final Map<Vector2d,Grass> grass;

    public GrassField(int width, int height, int n) {
        super(width, height);
        this.grass = new LinkedHashMap<>();
        this.initGrass(n);
    }

    private void initGrass(int n) {
        List<Integer> places = ThreadLocalRandom.current().ints(0, this.height*this.width).distinct().limit(n).boxed().collect(Collectors.toList());
        for(int i:places) {
            Vector2d v = new Vector2d(i/this.height,i%this.height);
            this.grass.put(v,new Grass(v));
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object o = super.objectAt(position);
        if(!Objects.isNull(o)) return o;
        else {
            return grass.get(position);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public Vector2d[] borders() {
        int min_x_grass = Collections.min(this.grass.entrySet(), Comparator.comparing(g -> g.getValue().getPosition().getX())).getValue().getPosition().getX();
        int min_x_animal = Collections.min(this.animals.entrySet(), Comparator.comparing(a -> a.getValue().getPosition().getX())).getValue().getPosition().getX();
        int min_x = Math.min(min_x_animal, min_x_grass);
        int max_x_grass = Collections.max(this.grass.entrySet(), Comparator.comparing(g -> g.getValue().getPosition().getX())).getValue().getPosition().getX();
        int max_x_animal = Collections.max(this.animals.entrySet(), Comparator.comparing(a -> a.getValue().getPosition().getX())).getValue().getPosition().getX();
        int max_x = Math.max(max_x_animal, max_x_grass);
        int min_y_grass = Collections.min(this.grass.entrySet(), Comparator.comparing(g -> g.getValue().getPosition().getY())).getValue().getPosition().getY();
        int min_y_animal = Collections.min(this.animals.entrySet(), Comparator.comparing(a -> a.getValue().getPosition().getY())).getValue().getPosition().getY();
        int min_y = Math.min(min_y_animal, min_y_grass);
        int max_y_grass = Collections.max(this.grass.entrySet(), Comparator.comparing(g -> g.getValue().getPosition().getY())).getValue().getPosition().getY();
        int max_y_animal = Collections.max(this.animals.entrySet(), Comparator.comparing(a -> a.getValue().getPosition().getY())).getValue().getPosition().getY();
        int max_y = Math.max(max_y_animal, max_y_grass);
        return new Vector2d[] {new Vector2d(min_x, min_y), new Vector2d(max_x, max_y)};
    }
}
