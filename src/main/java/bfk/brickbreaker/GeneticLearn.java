package bfk.brickbreaker;

import java.util.*;
import basicneuralnetwork.*;

public class GeneticLearn {
    private static final Random random = new Random();
    private static final int GENERATIONS = 25;
    private static final int PADDLES = 1000;
    private static final int INPUT_SIZE = 1;
    private static final int OUTPUT_SIZE = 2;
    private static final int BREED = (int) (PADDLES * .01);
    public static final double MUTATION_PROBABILITY = 0.1;
    public static final int HIDDEN_NODES = 256;

    public static void main(String[] args) {
        List<NeuralNetwork> networks = new ArrayList<>();
        for (int i = 0; i < PADDLES; i++) {
            networks.add(new NeuralNetwork(INPUT_SIZE, HIDDEN_NODES, OUTPUT_SIZE));
        }

        Map<NeuralNetwork, Integer> networkTickMap = new HashMap<>();

        for (int i = 0; i < GENERATIONS; i++) {
            for (NeuralNetwork network : networks) {
                BBController bbController = new BBController();
                boolean running;
                do {
                    double[] input = new double[INPUT_SIZE];
                    input[0] = bbController.oneRound();
                    double[] answer = network.guess(input);
                    running = bbController.gameOver;
                    if (answer[0] > input[0]) {
                        bbController.getPaddle().moveLeft();
                    }
                    else {
                        bbController.getPaddle().moveRight();
                    }
                } while (running);

                int tickCounter = bbController.getTicks();
                networkTickMap.put(network, tickCounter);
            }

            // Sort the map based on tickCounter values (in descending order)
            List<Map.Entry<NeuralNetwork, Integer>> sortedEntries = new ArrayList<>(networkTickMap.entrySet());
            sortedEntries.sort((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()));

            // Optional: Print the sorted results
            System.out.println("Sorted networks by tick counter for generation " + (i + 1) + ":");
            for (Map.Entry<NeuralNetwork, Integer> entry : sortedEntries) {
                System.out.println("Network: " + entry.getKey() + " | Ticks: " + entry.getValue());
            }

        }
    }
}
