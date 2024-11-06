package bfk.brickbreaker;


import javax.swing.*;

import static bfk.brickbreaker.Direction.*;

public class BBController{
    private final Ball ball;
    private final Brick brick;
    private final Paddle paddle;
    private final BBComponent view;
    private Timer timer;
    public BBController(Ball ball, Brick brick, Paddle paddle, BBComponent view) {
        this.ball = ball;
        this.brick = brick;
        this.paddle = paddle;
        this.view = view;
    }

    public void gamePlay() {

        timer = new Timer(10, e -> {
            double newX = ball.updateX();
            double newY = ball.updateY();

            //view.repaint();

            Direction direction = NONE;

            if (ball.collides()) {
                int radius = ball.getRadius();
                if (newX - radius <= 0) {
                    direction = LEFT;
                } else if (newX + radius >= ball.getWidth()) {
                    direction = RIGHT;
                } else if (newY - radius <= 0) {
                    direction = TOP;
                } else if (newY + radius >= ball.getHeight()) {
                    timer.stop();
                }

                if (direction != NONE) {
                    ball.bounce(direction);
                }
            }

            hitPanel();
            breakBricks();
        });

        timer.start();
    }

    public void hitPanel() {

    }

    public void breakBricks() {
        double ballX = ball.getX();
        double ballY = ball.getY();

        for (int x = 0; x < brick.getHeight(); x++) {
            for (int y = 0; y < brick.getWidth(); y++) {
                if (brick.isBrick(x, y)) {
                    int brickX = x * 30;
                    int brickY = y * 18;

                    if (ballX >= brickX && ballX <= brickX + 30
                            && ballCenterY >= brickY && ballCenterY <= brickY + 18) {

                        brick.hitBrick(i, j);

                        ball.bounce();

                        view.repaint();
                    }
                }
            }
        }
    }
}

