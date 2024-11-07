package bfk.brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class BBFrame extends JFrame {
    private static final int NUM_BRICKS = 20;
    private static final int BRICK_WIDTH = 60;
    private static final int BRICK_HEIGHT = 20;
    Ball ball = new Ball(45, 5, 295, 720, 20, 20);
    Paddle paddle = new Paddle(250, 740, 100, 20);
    Brick[] bricks = new Brick[NUM_BRICKS];

    public BBFrame() {
        setTitle("Brick Breaker");
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initializeBricks();

        BBComponent bbComponent = new BBComponent(ball, paddle, bricks);
        BBController bbController = new BBController(ball, paddle, bbComponent, bricks);

        add(bbComponent, BorderLayout.CENTER);
        bbController.gamePlay();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_LEFT) {
                    paddle.moveLeft();
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    paddle.moveRight();
                }
                repaint();
            }
        });



        // Make sure the frame can get key events by setting focusable
        setFocusable(true);

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
