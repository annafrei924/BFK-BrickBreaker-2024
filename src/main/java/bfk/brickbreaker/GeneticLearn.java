package bfk.brickbreaker;

import java.util.*;
import basicneuralnetwork.*;
import static bfk.brickbreaker.BBFrame.NUM_BRICKS;

class NetworkStats {
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

public class GeneticLearn {

    private static final int GENERATIONS = 1;
    private static final int NETWORK_COUNT = 10;
    private static final int INPUT_SIZE = 2;
    private static final int OUTPUT_SIZE = 2;
    private static final int BREED = (int) (NETWORK_COUNT * .01);
    public static final double MUTATION_PROBABILITY = 0.1;
    public static final int HIDDEN_NODES = 128;

    public static void main(String[] args) {

        //create Array of Networks
        List<NeuralNetwork> networks = new ArrayList<>();
        for (int i = 0; i < NETWORK_COUNT; i++) {
            networks.add(new NeuralNetwork(INPUT_SIZE, HIDDEN_NODES, OUTPUT_SIZE));
        }

        //for every generation
        for (int i = 0; i < GENERATIONS; i++) {
            Map<NeuralNetwork, NetworkStats> networkStatsMap = new HashMap<>();

            //every network plays the game
            for (NeuralNetwork network : networks) {
                BBController bbController = new BBController();
                BBFrame frame = new BBFrame(bbController);
                frame.setVisible(true);
                boolean running = bbController.isRunning();
                while (running) {
                    double[] input = new double[INPUT_SIZE];
                    input[0] = bbController.currAngle;
                    input[1] = bbController.getScore();
                    double[] answer = network.guess(input);
                    if (answer[0] > answer[1]) {
                        bbController.getPaddle().moveLeft();
                    } else {
                        bbController.getPaddle().moveRight();
                    }
                    running = bbController.isRunning();
                }
                //when it loses, we add the score, ticks, and network to a map
                networkStatsMap.put(network, new NetworkStats(bbController.getTicks(), bbController.getScore()));
                System.out.println("Network " + network + " : " + bbController.getTicks() + " ticks, " + bbController.getScore() + " score");
            }

            //sort the map basic on this logic:
            // first we sort by score. If 2 have tbe same score. If that score is the highest score, the one with less ticks
            // will win. if it's not the highest score, the one with more ticks will win.
            List<Map.Entry<NeuralNetwork, NetworkStats>> sortedEntries = new ArrayList<>(networkStatsMap.entrySet());
            sortedEntries.sort((entry1, entry2) -> {
                NetworkStats stats1 = entry1.getValue();
                NetworkStats stats2 = entry2.getValue();

                int scoreComparison = Integer.compare(stats2.score, stats1.score); //compare by score
                if (scoreComparison != 0) {
                    return scoreComparison;
                }

                //if scores are equal
                if (stats1.score == NUM_BRICKS && stats2.score == NUM_BRICKS) {
                    return Integer.compare(stats1.tickCounter, stats2.tickCounter);
                } else if (stats1.score != NUM_BRICKS && stats2.score != NUM_BRICKS) {
                    return Integer.compare(stats2.tickCounter, stats1.tickCounter);
                }

                return Integer.compare(stats2.score, stats1.score);
            });

            // After sorting, print the sorted networks with their tickCounter and score
            System.out.println("Sorted networks");
            for (Map.Entry<NeuralNetwork, NetworkStats> entry : sortedEntries) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }
}
