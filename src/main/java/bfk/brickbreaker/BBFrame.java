package bfk.brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BBFrame extends JFrame {
    Ball ball = new Ball(45, 10, 295, 720, 10, 10);
    Paddle paddle = new Paddle(250, 740, 100, 20);
    Brick[] bricks = new Brick[20];
    private static final int BRICK_WIDTH = 60;  // Brick width (in pixels)
    private static final int BRICK_HEIGHT = 20;  // Brick height (in pixels)
    private static final int NUM_BRICKS = 20;  // Number of bricks

    public BBFrame() {
        setTitle("Brick Breaker");
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initializeBricks();

        BBComponent bbComponent = new BBComponent(ball, paddle, bricks);
        BBController bbController = new BBController(ball, paddle, bbComponent, bricks);

        add(bbComponent, BorderLayout.CENTER);

    }

    public void initializeBricks() {
        Random rand = new Random();

        for (int i = 0; i < bricks.length; i++) {
            boolean overlap;
            int x;
            int y;
            do {
                x = rand.nextInt(getWidth() - BRICK_WIDTH);
                y = rand.nextInt((int) ((getHeight() * 0.66) - BRICK_HEIGHT));

                // Check if the new brick overlaps with any existing brick
                overlap = false;
                for (int j = 0; j < i; j++) {
                    if (bricks[j].intersects(x, y, BRICK_WIDTH, BRICK_HEIGHT)) {
                        overlap = true;
                        break;
                    }
                }
            } while (overlap);

            bricks[i] = new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        }
    }
}
