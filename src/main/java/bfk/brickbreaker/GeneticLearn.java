package bfk.brickbreaker;

import java.util.*;
import basicneuralnetwork.*;


public class GeneticLearn {
    private static final int GENERATIONS = 1;
    private static final int NETWORK_COUNT = 10;
    private static final int INPUT_SIZE = 1;
    private static final int OUTPUT_SIZE = 2;
    private static final int BREED = (int) (NETWORK_COUNT * .01);
    public static final double MUTATION_PROBABILITY = 0.1;
    public static final int HIDDEN_NODES = 128;

    public static void main(String[] args) {
        List<NeuralNetwork> networks = new ArrayList<>();
        for (int i = 0; i < NETWORK_COUNT; i++) {
            networks.add(new NeuralNetwork(INPUT_SIZE, HIDDEN_NODES, OUTPUT_SIZE));
        }

        Map<NeuralNetwork, Integer> networkTickMap = new HashMap<>();

        for (int i = 0; i < GENERATIONS; i++) {
            for (NeuralNetwork network : networks) {
                BBController bbController = new BBController();
                BBFrame frame = new BBFrame(bbController);
                frame.setVisible(true);

                boolean running = bbController.isRunning();
                while(running) {
                    double[] input = new double[INPUT_SIZE];
                    input[0] = bbController.currAngle;
                    double[] answer = network.guess(input);

                    if (answer[0] > answer[1]) {
                        bbController.getPaddle().moveLeft();
                    }
                    else {
                        bbController.getPaddle().moveRight();
                    }
                    running = bbController.isRunning();
                }
                int tickCounter = bbController.getTicks();
                networkTickMap.put(network, tickCounter);
                System.out.println("Network " + network + " : " + tickCounter);
            }

            // Sort the map based on tickCounter values (in descending order)
            List<Map.Entry<NeuralNetwork, Integer>> sortedEntries = new ArrayList<>(networkTickMap.entrySet());
            sortedEntries.sort((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()));

        }
    }
}
