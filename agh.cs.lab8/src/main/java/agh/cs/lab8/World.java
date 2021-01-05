package agh.cs.lab8;

import java.io.IOException;

public class World {

    public static void main(String[] args) {
        try {
            String path = "src/main/java/agh/cs/lab8/params.json";
            Integer[] worldMap = SettingParser.configGame(path);
            Runner runner = new Runner();
            runner.startSimulation(worldMap);

        } catch (IllegalArgumentException | IOException e) {

            System.out.println(e);
        }
    }

}