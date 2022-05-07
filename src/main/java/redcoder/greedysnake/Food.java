package redcoder.greedysnake;

import java.awt.*;
import java.util.Random;

import static redcoder.greedysnake.GameFrame.GF_HEIGHT;
import static redcoder.greedysnake.GameFrame.GF_WIDTH;

public class Food {

    private final Random random = new Random();
    private final GamePanel gamePanel;
    private int x;
    private int y;

    public Food(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        resetCoordinate();
    }

    public void paint(Graphics g) {
        ImageResource.FOOD.paintIcon(gamePanel, g, x, y);
    }

    public void resetCoordinate() {
        int fx = random.nextInt(GF_WIDTH / 25) * 25;
        int fy = random.nextInt(GF_HEIGHT / 25) * 25;
        if (fx < 50) {
            fx = 50;
        }
        if (fx > GF_WIDTH - 50) {
            fx = GF_WIDTH - 50;
        }
        if (fy < 50) {
            fy = 50;
        }
        if (fy > GF_HEIGHT - 75) {
            fy = GF_HEIGHT - 75;
        }

        this.x = fx;
        this.y = fy;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    @Override
    public String toString() {
        return "Food[x=" + x + ", y=" + y + "]";
    }
}
