package bfk.brickbreaker;

import basicneuralnetwork.NeuralNetwork;

public class NetworkStats implements Comparable<NetworkStats> {
    public NeuralNetwork network;
    public int angleCounter;
    public int tickCounter;
    public int score;

    public NetworkStats(NeuralNetwork network, int angleCounter, int tickCounter, int score) {
        this.network = network;
        this.angleCounter = angleCounter;
        this.tickCounter = tickCounter;
        this.score = score;
    }

    @Override
    public int compareTo(NetworkStats o) {
        // First compare by angleCounter
        int angleComparison = Integer.compare(o.angleCounter, this.angleCounter);
        if (angleComparison != 0) {
            return angleComparison;
        }
        // If angleCounters are the same, compare by tickCounter
        return Integer.compare(o.tickCounter, this.tickCounter);
    }

    @Override
    public String toString() {
        return "NetworkStats{" + "network=" + network + ", angleCounter=" + angleCounter
                + ", tickCounter=" + tickCounter + ", score=" + score + '}';
    }
}