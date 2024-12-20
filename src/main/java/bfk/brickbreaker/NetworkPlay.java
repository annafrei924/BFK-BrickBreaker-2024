//package bfk.brickbreaker;
//
//import basicneuralnetwork.NeuralNetwork;
//
//import java.io.IOException;
//
//
//public class NetworkPlay {
//
//    public static void main(String[] args) throws InterruptedException, IOException {
//        NeuralNetwork topNetwork = NeuralNetwork.readFromFile("ai.json");
//        BBController bbController = new BBController(20);
//        BBFrame frame = new BBFrame(bbController);
//        frame.setVisible(true);
//        boolean running = !bbController.gameOver;
//        while (running) {
//            bbController.oneRound();
//            double[] input = new double[INPUT_SIZE];
//            //input[0] = bbController.getCurrAngle();
//            input[1] = bbController.getBall().getY();
//            double[] answer = topNetwork.guess(input);
//            // Move paddle based on network's decision
//            if (answer[0] > answer[1]) {
//                bbController.getPaddle().moveLeft();
//            } else {
//                bbController.getPaddle().moveRight();
//            }
//            running = !bbController.gameOver;
//            Thread.sleep(3);
//        }
//        frame.dispose();
//    }
//}
