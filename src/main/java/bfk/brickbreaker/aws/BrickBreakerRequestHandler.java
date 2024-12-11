package bfk.brickbreaker.aws;

import basicneuralnetwork.NeuralNetwork;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.io.IOException;
import java.io.InputStream;

public class BrickBreakerRequestHandler implements RequestHandler<String, String> {

    @Override
    public String handleRequest(String input, Context context) {

        try {
            InputStream in = ClassLoader.getSystemResourceAsStream("/src/resources/ai.json");
            NeuralNetwork network = NeuralNetwork.readFromFile(in);
            double guess[] = new double[4];
            guess[0] = 10; //x ball
            guess[1] = 20; //x paddle
            guess[2] = 40; //x brick
            guess[3] = 80; //y brick

            double output[] = network.guess(guess);
            if (output[0] > output[1]) {
                return "LEFT";
            } else {
                return "RIGHT";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
