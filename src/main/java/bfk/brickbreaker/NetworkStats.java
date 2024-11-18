package bfk.brickbreaker;

class NetworkStats {
    public int angleCounter;
    public int tickCounter;
    public int score;

    public NetworkStats(int angleCounter, int tickCounter, int score) {
        this.angleCounter = angleCounter;
        this.tickCounter = tickCounter;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Ticks: " + tickCounter + ", Score: " + score;
    }

}