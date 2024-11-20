package bfk.brickbreaker;

import java.util.*;

import basicneuralnetwork.*;

public class GeneticLearn {
     class NetworkStats implements Comparable<NetworkStats> {
        public NeuralNetwork network;
        public int paddleHits;
        public int score;

        public NetworkStats(NeuralNetwork network, int paddleHits, int score) {
            this.network = network;
            this.paddleHits = paddleHits;
            this.score = score;
        }

        @Override
        public int compareTo(NetworkStats o) {
            int scoreComparison = Integer.compare(o.paddleHits, this.paddleHits);
            return scoreComparison;
        }
    }
    private static final int GENERATIONS = 5;
    private static final int NETWORK_COUNT = 1000;
    public static final int INPUT_SIZE = 1;
    public static final int OUTPUT_SIZE = 2;
    private static final int TOP_AMOUNT = (int) (NETWORK_COUNT * .01);
    private static final int HIDDEN_LAYERS = 2;
    public static final int HIDDEN_NODES = 4;
    public static final int MAX_ROUNDS = 10000;
    private static final int NUM_BRICKS = 0;


    private NetworkStats play(NeuralNetwork network) {
        int rounds = 0;
        BBController bbController = new BBController(NUM_BRICKS);
        boolean running = !bbController.gameOver;
        while (running && rounds < MAX_ROUNDS) {
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
        return new NetworkStats(network, bbController.paddleHits, bbController.getScore());
    }

    public NeuralNetwork getTopNetwork() {
        NeuralNetwork topNetwork = null;
        List<NeuralNetwork> networks = new ArrayList<>();
        for (int i = 0; i < NETWORK_COUNT; i++) {
            networks.add(new NeuralNetwork(INPUT_SIZE, HIDDEN_LAYERS, HIDDEN_NODES, OUTPUT_SIZE));
        }

        List<NetworkStats> networkAndStats = new ArrayList<>();

        // For every generation
        for (int gen = 0; gen < GENERATIONS; gen++) {
            int totalPaddleHits = 0;
            //int totalScore = 0;
            // Every network plays the game
            for (NeuralNetwork network : networks) {
                NetworkStats networkStats = play(network);
                networkAndStats.add(networkStats);
                totalPaddleHits += networkStats.paddleHits;
                //totalScore += networkStats.score;
            }
            System.out.println("Generation " + gen + " | Paddle Hits: " + totalPaddleHits);
            Collections.sort(networkAndStats);

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
            if (gen == GENERATIONS - 1) {
                topNetwork = networkAndStats.get(0).network;
                System.out.println(networkAndStats.get(0).paddleHits);
            } else {
                networkAndStats.clear();
            }
        }
        return topNetwork;
    }
}


