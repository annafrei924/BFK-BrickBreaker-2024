package bfk.brickbreaker;

class NetworkStats {
    public int tickCounter;
    public int score;

    public NetworkStats(int tickCounter, int score) {
        this.tickCounter = tickCounter;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Ticks: " + tickCounter + ", Score: " + score;
    }

}
