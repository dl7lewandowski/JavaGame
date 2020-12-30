package agh.cs.lab5;

import agh.cs.lab5.Animal;
import agh.cs.lab5.Vector2d;
import java.util.Collection;
import java.util.List;

public class MapEngine  implements IEngine{

    private final IWorldMap worldMap;

    public MapEngine(IWorldMap worldMap) {
        this.worldMap = worldMap;
    }

    @Override
    public void run(MoveDirection[] directions) {
        Animal[] animals = worldMap.getAnimals().values().toArray(new Animal[0]);
        for(int i=0;i<directions.length;i++) {
            if(directions[i] != null) {
                Animal animal = animals[i%animals.length];
                Vector2d position = animal.getPosition();
                worldMap.getAnimals().remove(position);
                animal.move(directions[i]);
                worldMap.getAnimals().put(animal.getPosition(),animal);
            }
        }
    }

}
