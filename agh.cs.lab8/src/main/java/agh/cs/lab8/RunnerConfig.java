package agh.cs.lab8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunnerConfig extends JPanel implements ActionListener {
    public static final int HEIGHT = 250;
    public static final int WIDTH = 500;
    private final Integer[] param;
    private int count=1;
    JButton newGameButton;
    JButton startTwoGamesButton;


    public RunnerConfig(Integer[] param) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.param = param;
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);
        JPanel buttonSettings = new JPanel();
        buttonSettings.add(newGameButton);
        add(buttonSettings);
        startTwoGamesButton = new JButton("Start two New Game");
        startTwoGamesButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent a) {
                runGame();
                runGame();
            }
        });


        JPanel buttonSettings1 = new JPanel();
        buttonSettings1.add(startTwoGamesButton);
        add(buttonSettings1);
    }


    @Override
    public void actionPerformed(ActionEvent a) {

        runGame();
    }

    private void runGame() {
        AreaWorld areaWorld;
        areaWorld = new AreaWorld(param[0], param[1], new Vector2d(param[2], param[3]), new Vector2d(param[4], param[5]), param[6], param[7], param[8], param[9]);

        Animal.energyMove = param[10];
        WorldSimulator worldSimulator = new WorldSimulator(areaWorld, param[11], count, param[12]);
        count += 1;
        worldSimulator.run();
    }
}
