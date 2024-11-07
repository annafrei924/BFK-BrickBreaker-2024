package bfk.brickbreaker;

import javax.swing.*;

import java.awt.event.MouseEvent;
import java.util.Random;

import static bfk.brickbreaker.Direction.*;

public class BBController{
    private final Ball ball;
    private final Paddle paddle;
    private final BBComponent view;
    private final Brick[] bricks;
    private Timer timer;
    // Constants for the grid size and brick size

    public BBController(Ball ball, Paddle paddle, BBComponent view, Brick[] bricks) {
        this.ball = ball;
        this.paddle = paddle;
        this.view = view;
        this.bricks = bricks;
    }



    public void gamePlay() {

        //ball motion and ball changes direction
        timer = new Timer(1000 / 60, e -> {

            double newX = ball.updateX();
            double newY = ball.updateY();

            ball.setPosition(newX, newY);
            ball.setFrame(newX, newY, ball.width, ball.height);
            checkCollisions();


            view.repaint();
        });

        timer.start();
    }

    public void checkCollisions() {
        if (ball.y <= 0) {
            bounce(Direction.TOP);
        }
        else if (ball.x <= 0) {
            bounce(Direction.LEFT);
        }
        else if (ball.x + ball.width >= view.getWidth()) {
            bounce(Direction.RIGHT);
        }
        else if (ball.y + ball.height >= 800) {
            System.exit(0);
        }
        else if (ball.getBounds2D().intersects(paddle.getBounds2D())) {
            bounce(Direction.BOTTOMPADDLE);
            double ballCenterX = ball.x + ball.width / 2;
            double paddlePosition = ballCenterX - paddle.getX();
            //hitPaddle(paddlePosition);
        }
        else {
            // Iterate through each brick
            for (int i = 0; i < bricks.length; i++) {
                Brick brick = bricks[i];
                if (brick != null) {
                    if (ball.getBounds2D().intersects(brick.getBounds2D())) {
                        bounce(Direction.BRICK);
                        bricks[i] = null;
                        return;
                    }
                }
            }
        }
    }

    public void hitPaddle(int paddleX) {

    }

    public void bounce(Direction direction) {
        switch (direction) {
            case TOP, BRICK -> ball.setAngle(360 - ball.getAngle());
            case RIGHT, LEFT -> ball.setAngle(180 - ball.getAngle());
            default -> { }
        }
    }



}

