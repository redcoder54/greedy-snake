package redcoder.greedysnake;

import java.awt.*;
import java.util.ArrayList;

public class Snake {

    private GamePanel gamePanel;
    private int length;
    private int[] snakeX = new int[600];
    private int[] snakeY = new int[500];
    private Direction dir;
    private boolean died;

    private final ArrayList<SnakeLengthListener> listeners;

    public Snake(GamePanel gamePanel,ArrayList<SnakeLengthListener> listeners) {
        this.gamePanel = gamePanel;
        this.listeners = listeners;

        length = 3;
        dir = Direction.RIGHT;

        snakeX[0] = 100;snakeY[0] = 100;
        snakeX[1] = 75;snakeY[1] = 100;
        snakeX[2] = 50;snakeY[2] = 100;
    }

    public void paint(Graphics g) {
        // 画蛇头
        switch (dir) {
            case LEFT:
                ImageResource.HEAD_LEFT.paintIcon(gamePanel, g, snakeX[0], snakeY[0]);
                break;
            case UP:
                ImageResource.HEAD_UP.paintIcon(gamePanel, g, snakeX[0], snakeY[0]);
                break;
            case RIGHT:
                ImageResource.HEAD_RIGHT.paintIcon(gamePanel, g, snakeX[0], snakeY[0]);
                break;
            case DOWN:
                ImageResource.HEAD_DOWN.paintIcon(gamePanel, g, snakeX[0], snakeY[0]);
                break;
            default:
                break;
        }
        // 画蛇尾
        for (int i = 1; i < length; i++) {
            ImageResource.BODY.paintIcon(gamePanel, g, snakeX[i], snakeY[i]);
        }

        move();
    }

    private void move() {
        if (!gamePanel.isStart()) {
            return;
        }

        // 移动身体
        for (int i = length - 1; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }
        // 移动头
        switch (dir) {
            case LEFT:
                snakeX[0] = snakeX[0] - 25;
                break;
            case UP:
                snakeY[0] = snakeY[0] - 25;
                break;
            case RIGHT:
                snakeX[0] = snakeX[0] + 25;
                break;
            case DOWN:
                snakeY[0] = snakeY[0] + 25;
                break;
            default:
                break;
        }

        // 边界检查
        boundaryCheck();
        // 死亡检测
        deathDetection();
    }

    private void boundaryCheck(){
        if (snakeX[0] < 0) {
            snakeX[0] = GameFrame.GF_WIDTH - 25;
        }
        if (snakeX[0] > GameFrame.GF_WIDTH - 25) {
            snakeX[0] = 0;
        }
        if (snakeY[0] < 0) {
            snakeY[0] = GameFrame.GF_HEIGHT - 25;
        }
        if (snakeY[0] > GameFrame.GF_HEIGHT - 25) {
            snakeY[0] = 0;
        }
    }

    private void deathDetection() {
        for (int i = 1; i < length; i++) {
            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                died = true;
                return;
            }
        }
    }

    public boolean eat(Food food) {
        if (snakeX[0] == food.getX() && snakeY[0] == food.getY()) {
            switch (dir) {
                case LEFT:
                    snakeX[length] = snakeX[length - 1] + 25;
                    snakeY[length] = snakeY[length - 1];
                    break;
                case UP:
                    snakeX[length] = snakeX[length - 1];
                    snakeY[length] = snakeY[length - 1] + 25;
                    break;
                case RIGHT:
                    snakeX[length] = snakeX[length - 1] - 25;
                    snakeY[length] = snakeY[length - 1];
                    break;
                case DOWN:
                    snakeX[length] = snakeX[length - 1];
                    snakeY[length] = snakeY[length - 1] - 25;
                    break;
                default:
                    break;
            }

            length++;
            noticeListener();

            return true;
        }
        return false;
    }

    private void noticeListener() {
        for (SnakeLengthListener listener : listeners) {
            listener.lengthChange(length);
        }
    }

    public boolean isDied() {
        return died;
    }

    public void setDir(Direction dir) {
        if (gamePanel.isStart()) {
            this.dir = dir;
        }
    }
}
