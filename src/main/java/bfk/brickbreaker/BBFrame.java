package bfk.brickbreaker;

import javax.swing.*;
import java.awt.*;

public class BBFrame extends JFrame {
    Brick brick = new Brick(300, 300, 15, 15);
    Ball ball = new Ball(45, 300, 700, 10);
    Paddle paddle = new Paddle(100, 20, 250, 750);

    public BBFrame() {
        setTitle("Brick Breaker");
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        BBComponent bbComponent = new BBComponent(brick, ball, paddle);
        BBController bbController = new BBController(ball, brick, paddle, bbComponent);

        add(bbComponent, BorderLayout.CENTER);

        brick.initialPopulate();
    }

}
