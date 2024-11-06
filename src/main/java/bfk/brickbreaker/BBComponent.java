package bfk.brickbreaker;

import javax.swing.*;
import java.awt.*;

public class BBComponent extends JComponent {
    private Brick brick;

    private Ball ball;
    private Paddle paddle;

    public BBComponent(Brick brick, Ball ball, Paddle paddle) {
        this.brick = brick;
        this.ball = ball;
        this.paddle = paddle;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.blue);

        brick.initialPopulate();

        g.setColor(Color.black);
        g.fillOval((int) ball.getX(), (int) ball.getY(), (int) ball.getWidth(), (int) ball.getHeight());

        g.setColor(Color.red);
        g.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());

    }

}
