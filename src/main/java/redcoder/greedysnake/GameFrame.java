package redcoder.greedysnake;

import javax.swing.*;

public class GameFrame extends JFrame {

    public static final int GF_WIDTH = 900;
    public static final int GF_HEIGHT = 700;

    public GameFrame() {
        super("贪吃蛇");
        setSize(GF_WIDTH, GF_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(new GamePanel());
        setVisible(true);
    }
}
