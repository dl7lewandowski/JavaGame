package agh.cs.lab8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserConfig extends JPanel {
    public AreaWorld areaWorld;
    public WorldSimulator worldSimulator;
    private final JButton pauseButton;
    private final JTextField nEra;
    private final JButton buttonInfo;
    private final JTextField animalIdentity;

    public UserConfig(AreaWorld areaWorld, WorldSimulator worldSimulator) {
        setLayout(null);
        this.areaWorld = areaWorld;
        this.worldSimulator = worldSimulator;
        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(this::stopAction);
        Rectangle rect = new Rectangle(25, 0, 250, 45);
        pauseButton.setBounds(rect);
        add(pauseButton,BorderLayout.CENTER);

        animalIdentity = new JTextField("");
        animalIdentity.addActionListener(this::stopAction);
        Rectangle rect3 = new Rectangle(75, 120, 150, 50);
        animalIdentity.setBounds(rect3);
        animalIdentity.setEnabled(false);
        add(animalIdentity,BorderLayout.CENTER);

        buttonInfo = new JButton("Information about animal");
        buttonInfo.addActionListener((this::showInfo));
        Rectangle rect2 = new Rectangle(25, 180, 250, 45);
        buttonInfo.setBounds(rect2);
        buttonInfo.setEnabled(false);
        add(buttonInfo,BorderLayout.CENTER);

        nEra = new JTextField("");
        nEra.addActionListener(this::stopAction);
        Rectangle rect4 = new Rectangle(75, 300, 150, 50);
        nEra.setBounds(rect4);
        nEra.setEnabled(false);
        add(nEra,BorderLayout.CENTER);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize((int) (worldSimulator.jframe.getWidth() * 0.25), (int)(worldSimulator.jframe.getHeight()*0.5));
        this.setLocation(0, (int)(worldSimulator.jframe.getHeight()*0.5));
        int width = this.getWidth();
        int height = this.getHeight();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, width, height);
        Font drawFont = new Font("Times", Font.BOLD, 18);
        g.setFont(drawFont);
        g.setColor(Color.WHITE);
        g.drawString("ID animal:", 125,100);
        g.drawString("Era:", 125,275);
    }

    public void stopAction(ActionEvent e) {
        if (!worldSimulator.time.isRunning()) {
            pauseButton.setText("Pause");
            worldSimulator.time.start();
            animalIdentity.setEnabled(false);
            buttonInfo.setEnabled(false);
            nEra.setEnabled(false);
        } else {
            pauseButton.setText("Run");
            worldSimulator.time.stop();
            animalIdentity.setEnabled(true);
            buttonInfo.setEnabled(true);
            nEra.setEnabled(true);

        }
    }

    public void showInfo(ActionEvent e) {
        try{
            int identity = Integer.parseInt(animalIdentity.getText());
            String info = areaWorld.dataAnimal(identity);
            JOptionPane.showMessageDialog(null, info);
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Wrong input format!");
        }
    }



}
