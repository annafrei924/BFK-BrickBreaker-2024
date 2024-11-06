package bfk.brickbreaker;

import javax.swing.*;
import java.awt.*;

public class BBFrame extends JFrame {
    Ball ball = new Ball(45, 10, 295, 720, 10, 10);
    Paddle paddle = new Paddle(250, 740, 100, 20);
    Brick[] bricks = new Brick[15];

    int width = 600;
    int height = 800;

    public BBFrame() {
        setTitle("Brick Breaker");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        BBComponent bbComponent = new BBComponent(ball, paddle, bricks, width, height);
        BBController bbController = new BBController(ball, paddle, bbComponent);

        add(bbComponent, BorderLayout.CENTER);

    }

}
