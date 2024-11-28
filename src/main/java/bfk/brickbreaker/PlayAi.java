package bfk.brickbreaker;

import basicneuralnetwork.NeuralNetwork;

import java.io.IOException;

public class PlayAi {
    public static void main(String[] args) throws InterruptedException, IOException {
        NeuralNetwork network = NeuralNetwork.readFromFile("ai.json");
        Simulation simulation = new Simulation(network, 600, 800);

        BBFrame frame = new BBFrame(simulation);
        frame.setVisible(true);

        boolean running;

        do {
            running = simulation.advance();
            frame.getView().repaint();
            Thread.sleep(3);
        } while (running);

        frame.dispose();
    }
}
