package bfk.brickbreaker;

import javax.swing.*;

import java.awt.event.MouseEvent;

import static bfk.brickbreaker.Direction.*;

public class BBController{
    private final Ball ball;
    private final Paddle paddle;
    private final BBComponent view;
    private Timer timer;


    public BBController(Ball ball, Paddle paddle, BBComponent view) {
        this.ball = ball;
        this.paddle = paddle;
        this.view = view;
    }

//
//    public void gamePlay() {
//
//        //ball motion and ball changes direction
//        timer = new Timer(10, e -> {
//            double newX = ball.updateX();
//            double newY = ball.updateY();
//
//            view.repaint();
//
//            Direction direction = NONE;
//
////            if (ball.collides()) {
////                int radius = ball.getRadius();
////                if (newX - radius <= 0) {
////                    direction = LEFT;
////                } else if (newX + radius >= ball.getWidth()) {
////                    direction = RIGHT;
////                } else if (newY - radius <= 0) {
////                    direction = TOP;
////                } else if (newY + radius >= ball.getHeight()) {
////                    timer.stop();
////                }
////                ball.bounce(direction);
////
////            }
//
//            hitPaddle();
//            breakBricks();
//        });
//
//        timer.start();
//    }
//
//    public void hitPaddle() {
//
//    }
//
//    public void movePaddle(MouseEvent e, Boolean isMoving, int changeX) {
//        if (isMoving) {
//            int currX = e.getXOnScreen();
//            setLocation(currX - changeX, y);
//        }
//    }
//
//
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

