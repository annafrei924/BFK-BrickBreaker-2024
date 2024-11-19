package bfk.brickbreaker;

import javax.swing.*;

import java.util.Random;

import static bfk.brickbreaker.BBFrame.*;


public class BBController{
    private Ball ball;
    private Paddle paddle;
    private BBComponent view;
    private Brick[] bricks;
    private Timer timer;
    private int tickCounter = 0;
    private double currAngle = 0;
    private int score = 0;
    boolean gameOver = false;
    final int numBricks = 1;


    public BBController(Ball ball, Paddle paddle, BBComponent view, Brick[] bricks) {
        this.ball = ball;
        this.paddle = paddle;
        this.view = view;
        this.bricks = bricks;
    }

    public BBController() {
       createBall();
       createPaddle();
       createBricks();
       createView();
   }

    public void startTimer() {
        timer = new Timer(1000 / 60, e -> {
            if (!gameOver) {
                oneRound();
            }
        });
        timer.start();
    }

    //ball moves & then checks for collisions
    public void oneRound() {
        tickCounter++;
        ball.updatePosition();
        ball.setFrame(ball.x, ball.y, ball.width, ball.height);
        paddle.setFrame(paddle.getX(), paddle.getY(), paddle.width, paddle.height);

        currAngle = getCurrAngle();
        checkCollisions();
        view.repaint();
    }


    public void checkCollisions() {
        if (ball.y <= 0) {
            bounce(Direction.TOP);
        } else if (ball.x <= 0) {
            bounce(Direction.LEFT);
        } else if (ball.x + ball.width >= BBComponent.WIDTH) {
            bounce(Direction.RIGHT);
        } else if (ball.y + ball.height >= BBComponent.HEIGHT) {
            gameOver = true;
        //} else if (score == numBricks) {
            //gameOver = true;
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
                        score++;
                        return;
                    }
                }
            }
        }
    }

    public void hitPaddle(double paddleX) {
        if (paddleX < paddle.width / 4) {
            ball.setAngle(45);
        } else if (paddleX < paddle.width / 2) {
            ball.setAngle(75);
        } else if (paddleX == paddle.width / 2) {
            ball.setAngle(90);
        } else if (paddleX < paddle.width * 3.0 / 4.0) {
            ball.setAngle(105);
        } else {
            ball.setAngle(135);
        }
    }

    public void bounce(Direction direction) {
        switch (direction) {
            case TOP, BRICK -> ball.setAngle((360 - ball.getAngle()) % 360);
            case RIGHT, LEFT -> ball.setAngle((180 - ball.getAngle()) % 360);
            default -> { }
        }
    }


    public void createBall() {
        ball = new Ball(45, 10, 290, 670, 20, 20);
    }

    public void createPaddle() {
        paddle = new Paddle(250, 690, 100, 20);
    }

    public void createBricks() {
        bricks = new Brick[numBricks];
        initializeBricks();
    }

    public void createView() {
        view = new BBComponent(ball, paddle, bricks);
    }

    public void initializeBricks() {
        Random rand = new Random();
        for (int i = 0; i < bricks.length; i++) {
            boolean overlap;
            int x;
            int y;
            do {
                x = rand.nextInt(BBComponent.WIDTH - BRICK_WIDTH);
                y = rand.nextInt((int) ((BBComponent.HEIGHT * 0.66) - BRICK_HEIGHT));

                // Check if the new brick overlaps with any existing brick
                overlap = false;
                for (int j = 0; j < i; j++) {
                    if (bricks[j].intersects(x, y, BRICK_WIDTH, BRICK_HEIGHT)) {
                        overlap = true;
                        break;
                    }
                }
            } while (overlap);
            bricks[i] = new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        }
    }

    public BBComponent getView() {
        return view;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public Ball getBall() {
        return ball;
    }

    public int getTicks() {
        return tickCounter;
    }

    public int getScore() {
        return score;
    }

    public double getCurrAngle() {
        double angleInRadians = Math.atan2(paddle.getY() - (ball.y + ball.height),
                paddle.getCenterX() - ball.getCenterX());
        return Math.toDegrees(angleInRadians);
    }



}

