package agh.cs.lab4;

import java.util.List;

public class SimulationEngine implements IEngine {
    private final IWorldMap worldMap;

    public SimulationEngine(IWorldMap worldMap) {
        this.worldMap = worldMap;
    }

    @Override
    public void run(MoveDirection[] directions) {
        for(int i=0;i<directions.length;i++) {
            if(directions[i] != null) {
                List<Animal> animals = worldMap.getAnimals();
                animals.get(i%animals.size()).move(directions[i]);
            }
        }
    }
}
