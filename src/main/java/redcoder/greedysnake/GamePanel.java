package redcoder.greedysnake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    public static final Font DEFAULT_FONT = new Font(null, Font.BOLD, 40);
    public static final Color BACKGROUND_COLOR = Color.BLACK;

    private boolean start;
    private Snake snake;
    private Food food;
    private Timer timer;
    private ArrayList<SnakeLengthListener> listeners = new ArrayList<>();

    public GamePanel() {
        setFocusable(true);
        addKeyListener(new GameKeyListener());

        timer = new Timer(LengthDelayPair.L0.delay, e -> repaint());
        listeners.add(new ReduceTimerDelaySnakeLengthListener(timer));
        snake = new Snake(this, listeners);
        food = new Food(this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, GameFrame.GF_WIDTH, GameFrame.GF_HEIGHT);

        snake.paint(g);
        food.paint(g);

        if (snake.isDied()) {
            Color c = g.getColor();
            g.setColor(Color.RED);
            g.setFont(DEFAULT_FONT);
            g.drawString("游戏结束，按回车键重新开始", GameFrame.GF_WIDTH / 2 - 250, GameFrame.GF_HEIGHT / 2);
            g.setColor(c);

            start = false;
            return;
        }

        if (snake.eat(food)) {
            food.resetCoordinate();
        }

        if (!start) {
            Color c = g.getColor();
            g.setColor(Color.WHITE);
            g.setFont(DEFAULT_FONT);
            g.drawString("按下空格键开始游戏", GameFrame.GF_WIDTH / 2 - 200, GameFrame.GF_HEIGHT / 2);
            g.setColor(c);
        }
    }

    public boolean isStart() {
        return start;
    }

    public void restart() {
        snake = new Snake(this, listeners);
        food = new Food(this);
        timer.setDelay(LengthDelayPair.L0.delay);
    }

    class GameKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int kc = e.getKeyCode();
            switch (kc) {
                case KeyEvent.VK_SPACE:
                    start = !start && !snake.isDied();
                    break;
                case KeyEvent.VK_LEFT:
                    snake.setDir(Direction.LEFT);
                    break;
                case KeyEvent.VK_UP:
                    snake.setDir(Direction.UP);
                    break;
                case KeyEvent.VK_RIGHT:
                    snake.setDir(Direction.RIGHT);
                    break;
                case KeyEvent.VK_DOWN:
                    snake.setDir(Direction.DOWN);
                    break;
                case KeyEvent.VK_ENTER:
                    if (snake.isDied()) {
                        restart();
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
