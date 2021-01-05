package agh.cs.lab8;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SettingParser {

    public static Integer[] area(int weight, int height, double rate) {
        double ratio = Math.sqrt(rate);
        int x1 = (int)((weight - weight*ratio)/2);
        int y1 = (int)((height - height*ratio)/2);
        int x2 = (int)((weight + weight*ratio)/2);
        int y2 = (int)((height + height*ratio)/2);
        return new Integer[]{x1, y1, x2, y2};
    }

    public static Integer[] configGame(String confPath) throws IOException {
        String content = Files.readString(Paths.get(confPath));

        JSONObject o = new JSONObject(content);
        Integer[] areaBorders = area(o.getInt("widthX"),o.getInt("heightY"),
                o.getDouble("areaRatio"));
        return new Integer[]{
                o.getInt("widthX"), o.getInt("heightY"), areaBorders[0], areaBorders[1], areaBorders[2],
                areaBorders[3], o.getInt("numberGrass"), o.getInt("grassEnergy"),
                o.getInt("energy"), o.getInt("numberAnimals"), o.getInt("moveEnergy"),
                o.getInt("delay"), o.getInt("daysBeforeSnap")
        };
    }
}
