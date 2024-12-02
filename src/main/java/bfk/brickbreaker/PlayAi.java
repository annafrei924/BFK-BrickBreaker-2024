package bfk.brickbreaker;

import basicneuralnetwork.NeuralNetwork;

import java.io.IOException;

public class PlayAi {
    public static void main(String[] args) throws InterruptedException, IOException {
        NeuralNetwork network = NeuralNetwork.readFromFile("ai.json");
        Simulation simulation = new Simulation(network, BBComponent.WIDTH, BBComponent.HEIGHT);

        BBFrame frame = new BBFrame(simulation);
        frame.setVisible(true);

        boolean running;
        do {
            running = simulation.advance();
            frame.getView().repaint();
            Thread.sleep(2);
        } while (running);

        frame.dispose();
    }
}
