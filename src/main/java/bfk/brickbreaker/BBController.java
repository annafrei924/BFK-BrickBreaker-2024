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
            checkBrickCollisions();


            view.repaint();
        });

        timer.start();
    }

    public void checkBrickCollisions() {
        // Iterate through each brick
        for (int i = 0; i < bricks.length; i++) {
            Brick brick = bricks[i];
            if (brick != null) {
                if (ball.getBounds2D().intersects(brick.getBounds2D())) {
                    ball.bounce(Direction.BRICK);
                    bricks[i] = null;
                    return;
                }
            }
        }
    }

    public void hitPaddle() {

    }



}

