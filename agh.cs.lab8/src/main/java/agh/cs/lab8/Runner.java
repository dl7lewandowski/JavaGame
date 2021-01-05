package agh.cs.lab8;

import javax.swing.*;

public class Runner {
    public JFrame settings;

    public Runner() {
        settings = new JFrame();
        settings.setSize(500, 250);
        settings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settings.setLocationRelativeTo(null);

    }
    public void startSimulation(Integer[] defaultSettings){
        settings.add(new RunnerConfig(defaultSettings));
        settings.setVisible(true);
    }
}
