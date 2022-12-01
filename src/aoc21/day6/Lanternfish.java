package aoc21.day6;

public class Lanternfish {

    private int internalTimer;

    public Lanternfish() {
        this.internalTimer = 2;
    }

    public Lanternfish(int internalTimer) {
        this.internalTimer = internalTimer;
    }

    public void resetInternalTimer() {
        this.internalTimer = 6;
    }

    public void decreaseInternalTimer() {
        this.internalTimer -= 1;
    }

    public int getInternalTimer() {
        return internalTimer;
    }
}
