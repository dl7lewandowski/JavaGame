package agh.cs.lab8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorldSimulator implements ActionListener {
    public AreaWorld areaWorld;
    public JFrame jframe;
    public WorldConfig worldConfig;
    public WorldStats worldStats;
    public UserConfig userConfig;
    public Timer time;
    public boolean flag = false;
    private AnimalInfo animalInfo;
    private Animal targetedAnimal;


    public WorldSimulator(AreaWorld areaWorld, int timeDelay, int idAnimal, int n) {
        this.areaWorld = areaWorld;
        time = new Timer(timeDelay, this);
        jframe = new JFrame("WorldGame");
        jframe.setSize(1300, 1000);
        jframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        worldConfig = new WorldConfig(areaWorld, this);
        worldConfig.setSize(new Dimension(1, 1));
        jframe.add(worldConfig);
        worldStats = new WorldStats(areaWorld, this, idAnimal, n);
        worldStats.setSize(new Dimension(1, 1));
        jframe.add(worldStats);
        userConfig = new UserConfig(areaWorld, this);
        userConfig.setSize(new Dimension(1, 1));
        jframe.add(userConfig);
    }

    public void run() {
        time.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        worldConfig.repaint();
        worldStats.repaint();
        userConfig.repaint();
        areaWorld.delete();
        areaWorld.nextStep();
        areaWorld.consume();
        areaWorld.createAnimal();
        areaWorld.addNewGrass();
        if(flag) {
            if(areaWorld.firstDay >= animalInfo.era+animalInfo.numberEra) {
                String msg = getInfo();
                JOptionPane.showMessageDialog(null, msg);
                flag = false;
            }
        }
    }

    private String getInfo() {
        StringBuilder info = new StringBuilder("");
        info.append("Id animal: ").append(targetedAnimal.getId()).append("\n");
        info.append("New children: ").append(targetedAnimal.getKids().size() - animalInfo.numberChildren).append("\n");
        info.append("New descendant: ").append(targetedAnimal.numberOfDescendant() -
                animalInfo.numberDescendant);
        if(targetedAnimal.getDaysAlive()<animalInfo.numberEra+animalInfo.age-1){
            info.append("\n");
            info.append("Death in era: ").append(animalInfo.era-animalInfo.age+targetedAnimal.getDaysAlive())
                    .append("\n");
            info.append("Death in age: ").append(targetedAnimal.getDaysAlive());
        }
        return info.toString();
    }

    public void follow(Animal a, int n) {
        flag = true;
        animalInfo = new AnimalInfo(a.getId(), a.getKids().size(), a.numberOfDescendant(),
                a.getDaysAlive(), areaWorld.firstDay, n);
        targetedAnimal = a;
    }
}
