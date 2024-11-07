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
            view.repaint();
        });

        timer.start();
    }

    public void hitPaddle() {

    }

    



//    public void breakBricks() {
//        //makes brick disapper
//        double ballX = ball.getX();
//        double ballY = ball.getY();
//
//        for (int x = 0; x < brick.getHeight(); x++) {
//            for (int y = 0; y < brick.getWidth(); y++) {
//                if (brick.isBrick(x, y)) {
//                    int brickX = x * brick.getCols();
//                    int brickY = y * brick.getRows();
//
//                    if (ballX >= brickX && ballX <= brickX + brick.getCols()
//                            && ballX >= brickY && ballY <= brickY + brick.getRows()) {
//
//                        brick.brickHit(x, y);
//
//                        ball.bounce(TOP);
//
//                        view.repaint();
//                    }
//                }
//            }
//        }
//    }

}

