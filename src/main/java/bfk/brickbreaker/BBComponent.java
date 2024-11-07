package bfk.brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BBComponent extends JComponent {

    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;

    // Constants for the grid layout and brick size


    public BBComponent(Ball ball, Paddle paddle, Brick[] bricks) {
        this.ball = ball;
        this.paddle = paddle;
        this.bricks = bricks;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());  // Clear the screen

        // Draw the bricks
        g.setColor(Color.blue);
        for (Brick brick : bricks) {
            g2.fill(brick);  // Draw the brick
        }

        // Draw the ball
        g.setColor(Color.black);
        g2.fill(ball);

        // Draw the paddle
        g.setColor(Color.red);
        g2.fill(paddle);
    }
}
