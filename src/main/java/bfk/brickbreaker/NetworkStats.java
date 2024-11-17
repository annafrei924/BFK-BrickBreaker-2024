package bfk.brickbreaker;

public class NetworkStats {
    int tickCounter;
    int score;

    public NetworkStats(int tickCounter, int score) {
        this.tickCounter = tickCounter;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Ticks: " + tickCounter + ", Score: " + score;
    }

}
