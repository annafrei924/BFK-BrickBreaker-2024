//package bfk.brickbreaker;
//
//import basicneuralnetwork.NeuralNetwork;
//import bfk.brickbreaker.GeneticLearn;
//
//public class NetworkStats implements Comparable<NetworkStats> {
//    public NeuralNetwork network;
//    public int angleCounter;
//    public int tickCounter;
//    public int score;
//
//    public NetworkStats(NeuralNetwork network, int angleCounter, int tickCounter, int score) {
//        this.network = network;
//        this.angleCounter = angleCounter;
//        this.tickCounter = tickCounter;
//        this.score = score;
//    }
//
//    @Override
//    public int compareTo(NetworkStats o) {
//        return Integer.compare(o.angleCounter, this.angleCounter);
//    }
//}
