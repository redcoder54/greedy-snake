package redcoder.greedysnake;

import javax.swing.*;

public class ReduceTimerDelaySnakeLengthListener implements SnakeLengthListener {

    private Timer timer;

    public ReduceTimerDelaySnakeLengthListener(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void lengthChange(int length) {
        if (length > LengthDelayPair.L4.length) {
            timer.setDelay(LengthDelayPair.L4.delay);
        } else if (length > LengthDelayPair.L3.length) {
            timer.setDelay(LengthDelayPair.L3.delay);
        } else if (length > LengthDelayPair.L2.length) {
            timer.setDelay(LengthDelayPair.L2.delay);
        } else if (length > LengthDelayPair.L1.length) {
            timer.setDelay(LengthDelayPair.L1.delay);
        }
    }
}
