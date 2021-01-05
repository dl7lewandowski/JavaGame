package agh.cs.lab8;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class WorldConfig extends JPanel {
    public AreaWorld areaWorld;
    public WorldSimulator worldSimulator;

    public WorldConfig(AreaWorld areaWorld, WorldSimulator worldSimulator) {
        this.areaWorld = areaWorld;
        this.worldSimulator = worldSimulator;
    }

    @Override
    protected void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);

        this.setSize((int)   (worldSimulator.jframe.getWidth() * 0.70), worldSimulator.jframe.getHeight());
        this.setLocation((int)   (0.25 * worldSimulator.jframe.getWidth()), 0);
        int w = this.getWidth();
        int h = this.getHeight();
        int widthSize = w/areaWorld.getWidthX();
        int heightSize = h/areaWorld.getHeightY();
        graphic.setColor(new Color(83, 204, 141));
        graphic.fillRect(0, 0, w, h);
        graphic.setColor(new Color(0, 160, 16));
        graphic.fillRect(areaWorld.getVector2dLeftDown().getX() * widthSize,
                areaWorld.getVector2dLeftDown().getY() * heightSize,
                (areaWorld.getVector2dRightUp().getX() - areaWorld.getVector2dLeftDown().getX()) * widthSize,
                (areaWorld.getVector2dRightUp().getY() - areaWorld.getVector2dLeftDown().getY()) * heightSize);

        for (Map.Entry<Vector2d, Grass> start : areaWorld.getGrass().entrySet()){
            graphic.setColor(Color.GREEN);
            int x=start.getKey().getY()*heightSize;
            int y=start.getKey().getX()*widthSize;
            graphic.fillRect(x, y, widthSize, heightSize);
        }
        Font font = new Font("Arial", Font.BOLD, 22);
        graphic.setFont(font);
        for(List<Animal> list: areaWorld.getAnimalsPositions().values()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Animal animal : list) {
                int a = 255 - Math.min(animal.getEnergy(),255);
                graphic.setColor(new Color(a, 0, 0));
                int y =animal.getPosition().getY()*heightSize;
                int x =animal.getPosition().getX()*widthSize;
                graphic.fillOval(x, y, widthSize, heightSize);
                if(a>100) {
                    graphic.setColor(Color.BLACK);
                }
                else {
                    graphic.setColor(Color.WHITE);
                }
                stringBuilder.append(animal.getId()).append(" ");
                graphic.drawString(stringBuilder.toString(), animal.getPosition().getX() * widthSize + widthSize/3,
                        animal.getPosition().getY() * heightSize + heightSize/2);
            }
        }
    }
}
