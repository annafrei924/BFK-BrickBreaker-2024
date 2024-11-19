package bfk.brickbreaker;

import java.util.*;
import basicneuralnetwork.*;

public class GeneticLearn {

    static class NetworkStats implements Comparable<NetworkStats> {
        public NeuralNetwork network;
        public int tickCounter;
        public int score;

        public NetworkStats(NeuralNetwork network, int tickCounter, int score) {
            this.network = network;
            this.tickCounter = tickCounter;
            this.score = score;
        }

        @Override
        public int compareTo(NetworkStats o) {
            int tickComparison = Integer.compare(o.tickCounter, this.tickCounter);
            return tickComparison;

        }

        @Override
        public String toString() {
            return "NetworkStats{" + "network=" + network
                    + ", tickCounter=" + tickCounter + ", score=" + score + '}';
        }
    }


    private static final int GENERATIONS = 500;
    private static final int NETWORK_COUNT = 1000;
    private static final int INPUT_SIZE = 1;
    private static final int OUTPUT_SIZE = 2;
    private static final int TOP_AMOUNT = (int) (NETWORK_COUNT * .01);
    private static final int HIDDEN_LAYERS = 2;
    public static final int HIDDEN_NODES = 4;

    public static void main(String[] args) {

        // Create Array of Networks
        List<NeuralNetwork> networks = new ArrayList<>();
        for (int i = 0; i < NETWORK_COUNT; i++) {
            networks.add(new NeuralNetwork(INPUT_SIZE, HIDDEN_LAYERS, HIDDEN_NODES, OUTPUT_SIZE));
        }
        List<NetworkStats> networkAndStats = new ArrayList<>();

        // For every generation
        for (int i = 0; i < GENERATIONS; i++) {
            System.out.println("Starting Generation " + i);
            int totalTickCounter = 0;
            // Every network plays the game
            for (NeuralNetwork network : networks) {
                int rounds = 0;
                BBController bbController = new BBController();
                boolean running = !bbController.gameOver;
                while (running && rounds < 1000) {
                    bbController.oneRound();
                    double[] input = new double[INPUT_SIZE];
                    input[0] = bbController.getCurrAngle();
                    double[] answer = network.guess(input);
                    // Move paddle based on network's decision
                    if (answer[0] > answer[1]) {
                        bbController.getPaddle().moveLeft();
                    } else {
                        bbController.getPaddle().moveRight();
                    }
                    running = !bbController.gameOver;
                    rounds++;
                }
                totalTickCounter += bbController.getTicks();
                NetworkStats stats = new NetworkStats(network, bbController.getTicks(), bbController.getScore());
                networkAndStats.add(stats);
            }
            System.out.println("Tickcounter: " + totalTickCounter);
            Collections.sort(networkAndStats);

            // Select the top networks
            List<NeuralNetwork> topNetworks = new ArrayList<>();
            for (int j = 0; j < TOP_AMOUNT; j++) {
                topNetworks.add(networkAndStats.get(j).network);
            }
            networks.clear();

            // Create new networks by merging top networks and mutating them
            for (int j = 0; j < TOP_AMOUNT; j++) {
                NeuralNetwork network1 = networkAndStats.get(j).network;
                for (int k = 0; k < TOP_AMOUNT; k++) {
                    NeuralNetwork network2 = networkAndStats.get(k).network;
                    NeuralNetwork merged = network1.merge(network2);
                    for (int l = 0; l < NETWORK_COUNT / (TOP_AMOUNT * TOP_AMOUNT); l++) {
                        merged.mutate(0.1);
                        networks.add(merged);
                    }
                }
            }
            networkAndStats.clear();
        }
    }
}
