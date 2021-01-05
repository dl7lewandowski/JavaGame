package agh.cs.lab8;

import java.util.List;

public interface IWorldMaps {
    void place(Animal a);

    List<Animal> getAnimals();

    Vector2d targetPosition(Vector2d vector2d);

}
