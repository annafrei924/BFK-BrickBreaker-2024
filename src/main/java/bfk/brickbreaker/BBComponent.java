package bfk.brickbreaker;

import javax.swing.*;
import java.awt.*;

public class BBComponent extends JComponent {

    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;

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
            if (brick != null) {
                g2.fill(brick);
            }
        }

        // Draw the ball
        g.setColor(Color.black);
        g2.fill(ball);

        // Draw the paddle
        g.setColor(Color.red);
        g2.fill(paddle);
    }

}
