package redcoder.greedysnake;

public enum LengthDelayPair {
    L0(0, 150),
    L1(5, 120),
    L2(10, 100),
    L3(15, 80),
    L4(20, 50),
    ;

    public final int length;
    public final int delay;

    LengthDelayPair(int length, int delay) {
        this.length = length;
        this.delay = delay;
    }
}
