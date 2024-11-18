package bfk.brickbreaker;

import java.net.ConnectException;
import java.util.*;
import basicneuralnetwork.*;

public class GeneticLearn {
    static class NetworkStats {
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


    private static final int GENERATIONS = 10;
    private static final int NETWORK_COUNT = 1000;
    private static final int INPUT_SIZE = 1;
    private static final int OUTPUT_SIZE = 2;
    private static final int TOP_AMOUNT = (int) (NETWORK_COUNT * .01);
    public static final int HIDDEN_NODES = 4;

    public static void main(String[] args) {

        // Create Array of Networks
        List<NeuralNetwork> networks = new ArrayList<>();
        for (int i = 0; i < NETWORK_COUNT; i++) {
            networks.add(new NeuralNetwork(INPUT_SIZE, HIDDEN_NODES, OUTPUT_SIZE));
        }

        // For every generation
        for (int i = 0; i < GENERATIONS; i++) {
            int tickCounter = 0;
            int sumSuccess = 0;
            int networkNum = 0;
            Map<NeuralNetwork, NetworkStats> networkStatsMap = new HashMap<>();
            // Every network plays the game
            for (NeuralNetwork network : networks) {
                System.out.println("Network: " + networkNum++);
                int success = 0;
                BBController bbController = new BBController();
                bbController.reset();
                //BBFrame frame = new BBFrame(bbController);
                //frame.setVisible(true);
                bbController.startTimer();

                boolean running = bbController.isRunning();
                while (running) {
                    double[] input = new double[INPUT_SIZE];
                    input[0] = bbController.currAngle;
                    if (input[0] == 90) {
                        success++;
                        sumSuccess++;
                    }
                    double[] answer = network.guess(input);
                    // Move paddle based on network's decision
                    if (answer[0] > answer[1]) {
                        bbController.getPaddle().moveLeft();
                    } else {
                        bbController.getPaddle().moveRight();
                    }
                    running = bbController.isRunning();
                    //bbController.getView().repaint();
                }
                tickCounter += bbController.getTicks();
                //networkStatsMap.put(network, new NetworkStats(bbController.getTicks(), bbController.getScore()));
                networkStatsMap.put(network, new NetworkStats(success, bbController.getTicks(), bbController.getScore()));
                //frame.dispose();
            }
            System.out.println("Sum success: " + sumSuccess + " | tickCounter: " + tickCounter);

            // Sort by angleCounter
            List<Map.Entry<NeuralNetwork, NetworkStats>> sortedEntries = new ArrayList<>(networkStatsMap.entrySet());
            sortedEntries.sort((entry1, entry2) -> {
                NetworkStats stats1 = entry1.getValue();
                NetworkStats stats2 = entry2.getValue();

                return Integer.compare(stats2.angleCounter, stats1.angleCounter);
            });

            // Sort the map based on score, and number of ticks

//            sortedEntries.sort((entry1, entry2) -> {
//                NetworkStats stats1 = entry1.getValue();
//                NetworkStats stats2 = entry2.getValue();
//
//                int scoreComparison = Integer.compare(stats2.score, stats1.score); // compare by score
//                if (scoreComparison != 0) {
//                    return scoreComparison;
//                }
//
//                // If scores are equal
//                if (stats1.score == NUM_BRICKS && stats2.score == NUM_BRICKS) {
//                    return Integer.compare(stats1.tickCounter, stats2.tickCounter);
//                } else if (stats1.score != NUM_BRICKS && stats2.score != NUM_BRICKS) {
//                    return Integer.compare(stats2.tickCounter, stats1.tickCounter);
//                }
//
//                return Integer.compare(stats2.score, stats1.score);
//            });

            // Select the top networks
            List<NeuralNetwork> topNetworks = new ArrayList<>();
            for (int j = 0; j < TOP_AMOUNT; j++) {
                topNetworks.add(sortedEntries.get(j).getKey());
            }
            networks.clear();

            // Create new networks by merging top networks and mutating them
            for (int j = 0; j < topNetworks.size(); j++) {
                NeuralNetwork network1 = topNetworks.get(j);
                for (int k = 0; k < topNetworks.size(); k++) {
                    NeuralNetwork network2 = topNetworks.get(k);
                    NeuralNetwork merged = network1.merge(network2);
                    for (int l = 0; l < NETWORK_COUNT / (TOP_AMOUNT * TOP_AMOUNT); l++) {
                        merged.mutate(0.1);
                        networks.add(merged);
                    }
                }
            }
        }
    }
}
