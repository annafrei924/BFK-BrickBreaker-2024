package bfk.brickbreaker;

import javax.swing.*;

public class BBController{
    private final Ball ball;
    private final Paddle paddle;
    private final BBComponent view;
    private final Brick[] bricks;
    private Timer timer;
    private int time = 0;
    private boolean gameOver = false;
    private GameOverListener gameOverListener;

    public BBController(Ball ball, Paddle paddle, BBComponent view, Brick[] bricks, GameOverListener gameOverListener) {
        this.ball = ball;
        this.paddle = paddle;
        this.view = view;
        this.bricks = bricks;
        this.gameOverListener = gameOverListener;
    }

    public int startTimer() {
        timer = new Timer(1000 / 60, e -> {
            if (!gameOver) {
                playGame();
                time++;
            }
        });
        timer.start();
        return time;
    }

    //ball moves & then checks for collisions
    public void playGame() {
        double newX = ball.updateX();
        double newY = ball.updateY();

        ball.setFrame(newX, newY, ball.width, ball.height);
        getAngle();
        checkCollisions();

        view.repaint();
    }

    public void checkCollisions() {
        if (ball.y <= 0) {
            bounce(Direction.TOP);
        } else if (ball.x <= 0) {
            bounce(Direction.LEFT);
        } else if (ball.x + ball.width >= view.getWidth()) {
            bounce(Direction.RIGHT);
        } else if (ball.y + ball.height >= 800) {
            timer.stop();
            gameOver = true;
            if (gameOverListener != null) {
                gameOverListener.gameOver(time);
            }
        } else if (ball.getBounds2D().intersects(paddle.getBounds2D())) {
            bounce(Direction.BOTTOMPADDLE);
            double paddlePosition = ball.getCenterX() - paddle.getX();
            hitPaddle(paddlePosition);
        } else {
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

    public void hitPaddle(double paddleX) {
        if (paddleX < paddle.width / 4) {
            ball.setAngle(325);
        } else if (paddleX < paddle.width / 2) {
            ball.setAngle(290);
        } else if (paddleX == paddle.width / 2) {
            ball.setAngle(270);
        } else if (paddleX < paddle.width * 3.0 / 4.0) {
            ball.setAngle(250);
        } else {
            ball.setAngle(215);
        }
    }

    public void bounce(Direction direction) {
        switch (direction) {
            case TOP, BRICK -> ball.setAngle(360 - ball.getAngle());
            case RIGHT, LEFT -> ball.setAngle(180 - ball.getAngle());
            default -> { }
        }
    }

    public void getAngle() {
        double angleInRadians = Math.atan2(ball.getCenterY() - paddle.getCenterY(), ball.getCenterX() - paddle.getCenterX());
        double angleInDegrees = Math.toDegrees(angleInRadians);
        System.out.println(angleInDegrees);
    }

}

