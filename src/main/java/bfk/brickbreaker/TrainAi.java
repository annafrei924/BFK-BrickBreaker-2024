package bfk.brickbreaker;

import basicneuralnetwork.NeuralNetwork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainAi {

    static class NetworkStats implements Comparable<NetworkStats> {
        public NeuralNetwork network;
        public int score;

        public NetworkStats(NeuralNetwork network, int score) {
            this.network = network;
            this.score = score;
        }

        @Override
        public int compareTo(NetworkStats o) {
            int scoreComparison = Integer.compare(o.score, this.score);
            return scoreComparison;
        }
    }

    private static final int GENERATIONS = 100;
    private static final int AGENTS = 1000;
    public static final int INPUT_SIZE = 4;
    public static final int OUTPUT_SIZE = 2;
    private static final int TOP_AMOUNT = (int) (AGENTS * .01);
    private static final int HIDDEN_LAYERS = 2;
    public static final int HIDDEN_NODES = 4;
    public static final int MAX_ROUNDS = 10000;

    public static NetworkStats play(NeuralNetwork network) {
        int rounds = 0;
        Simulation simulation = new Simulation(network, BBComponent.WIDTH, BBComponent.HEIGHT);
        while (rounds < MAX_ROUNDS) {
             if (!simulation.advance()) {
                 break;
             }
             rounds++;
        }
        return new NetworkStats(network, simulation.getScore());
    }

    public static NeuralNetwork getTopNetwork() {
        NeuralNetwork topNetwork = null;
        List<NeuralNetwork> networks = new ArrayList<>();
        for (int i = 0; i < AGENTS; i++) {
            networks.add(new NeuralNetwork(INPUT_SIZE, HIDDEN_LAYERS, HIDDEN_NODES, OUTPUT_SIZE));
        }

        List<NetworkStats> networkAndStats = new ArrayList<>();

        // For every generation
        for (int gen = 0; gen < GENERATIONS; gen++) {
            int totalScore = 0;
            // Every network plays the game
            for (NeuralNetwork network : networks) {
                NetworkStats networkStats = play(network);
                networkAndStats.add(networkStats);
                totalScore += networkStats.score;
            }
            System.out.println("Generation " + gen + " | Score: " + totalScore);
            Collections.sort(networkAndStats);

            for (int j = 0; j < TOP_AMOUNT; j++) {
                NeuralNetwork network1 = networkAndStats.get(j).network;
                for (int k = 0; k < TOP_AMOUNT; k++) {
                    NeuralNetwork network2 = networkAndStats.get(k).network;
                    NeuralNetwork merged = network1.merge(network2);
                    for (int l = 0; l < AGENTS / (TOP_AMOUNT * TOP_AMOUNT); l++) {
                        merged.mutate(0.1);
                        networks.add(merged);
                    }
                }
            }

            if (gen == GENERATIONS - 1) {
                topNetwork = networkAndStats.get(0).network;
            } else {
                networkAndStats.clear();
            }
        }
        return topNetwork;
    }

    public static void main(String[] args) {
        NeuralNetwork topNetwork = getTopNetwork();
        try {
            topNetwork.writeToFile("src/resources/ai.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ;
    }


}
