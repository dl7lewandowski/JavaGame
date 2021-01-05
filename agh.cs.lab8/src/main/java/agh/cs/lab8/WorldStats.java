package agh.cs.lab8;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class WorldStats extends JPanel {
    public AreaWorld areaWorld;
    public WorldSimulator worldSimulator;
    private final int idAnimal;
    private final int n;
    private StringBuilder info = new StringBuilder();

    public WorldStats(AreaWorld areaWorld, WorldSimulator worldSimulator,  int idAnimal, int n) {
        this.areaWorld = areaWorld;
        this.worldSimulator = worldSimulator;
        this.idAnimal = idAnimal;
        this.n = n;
    }

    @Override
    protected void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        this.setSize((int) (worldSimulator.jframe.getWidth() * 0.25), (int)(worldSimulator.jframe.getHeight()*0.5));
        this.setLocation(0, 0);
        int widthX = this.getWidth();
        int heightY = this.getHeight();
        graph.setColor(Color.GRAY);
        graph.fillRect(0, 0, widthX, heightY);
        graph.setColor(Color.BLACK);
        Font font = new Font("Times", Font.BOLD, 22);
        graph.setFont(font);


        DecimalFormat decimalFormat;
        decimalFormat = new DecimalFormat("##.00");
        String tempFile = "Day: "+areaWorld.firstDay;
        info.append(tempFile).append('\n');
        graph.drawString(tempFile, 0,20);
        font = new Font("Arial", Font.BOLD, 18);
        graph.setFont(font);
        tempFile = "Animal living: ";
        info.append(tempFile).append('\n');
        graph.drawString(tempFile, 0,50);
        tempFile = ""+areaWorld.getAnimals().size();
        info.append(tempFile).append('\n');
        graph.drawString(tempFile, 10,70);
        tempFile = "Animal dead: ";
        info.append(tempFile).append('\n');
        graph.drawString(tempFile, 0,100);
        tempFile = ""+areaWorld.getDeadAnimals().size();
        info.append(tempFile).append('\n');
        graph.drawString(tempFile, 10,120);
        tempFile = "Grass: ";
        info.append(tempFile).append('\n');
        graph.drawString(tempFile, 0,150);
        tempFile = ""+areaWorld.getGrass().size();
        info.append(tempFile).append('\n');
        graph.drawString(tempFile, 10,170);
        tempFile = "Mean life among dead animal: ";
        info.append(tempFile).append('\n');
        graph.drawString(tempFile, 0,200);
        tempFile = ""+decimalFormat.format(areaWorld.getDeadAnimals().stream()
                .mapToInt(Animal::getDaysAlive)
                .average()
                .orElse(0));
        info.append(tempFile).append('\n');
        graph.drawString(tempFile, 10,220);
        tempFile = "Mean energy animal: ";
        info.append(tempFile).append('\n');
        graph.drawString(tempFile, 0,250);
        tempFile = ""+decimalFormat.format(areaWorld.getAnimals().stream()
                .mapToInt(Animal::getEnergy)
                .average()
                .orElse(0));
        info.append(tempFile).append('\n');
        graph.drawString(tempFile, 10,270);
        tempFile = "Mean children: ";
        info.append(tempFile).append('\n');
        graph.drawString(tempFile, 0,300);
        tempFile = ""+decimalFormat.format(areaWorld.getAnimals().stream()
                .mapToInt(a -> a.getKids().size())
                .average()
                .orElse(0));
        info.append(tempFile).append('\n');
        graph.drawString(tempFile, 10,320);
        tempFile = "genotype dominate: ";
        info.append(tempFile).append('\n');
        graph.drawString(tempFile, 0,350);
        font = new Font("Times", Font.BOLD, 14);
        graph.setFont(font);
        String dna = areaWorld.mostCommonGenotype();
        info.append(dna).append('\n');
        if(dna.length() > 0) {
            graph.drawString("" + dna.substring(1, 31), 10, 370);
            graph.drawString("" + dna.substring(31, 63), 10, 390);
            graph.drawString("" + dna.substring(63, 95), 10, 410);
        }
        if(n == areaWorld.firstDay) {
            try {
                File myObj = new File("information"+idAnimal+".txt");
                if (myObj.createNewFile()) {
                    System.out.println(myObj.getName());
                } else {
                    System.out.println("name is busy.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FileWriter myWriter = new FileWriter("information"+idAnimal+".txt");
                myWriter.write(info.toString());
                myWriter.close();
                System.out.println("File is created");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
