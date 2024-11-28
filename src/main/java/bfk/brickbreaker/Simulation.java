package bfk.brickbreaker;


import basicneuralnetwork.NeuralNetwork;

import java.util.Random;

public class Simulation {

    private static final int INPUT_SIZE = 2;
    private NeuralNetwork network;
    private Ball ball;
    private Paddle paddle;
    private int width;
    private int height;
    private boolean gameOver;
    private int score;
    Random rand = new Random();

    public Simulation(NeuralNetwork network, Ball ball, Paddle paddle, int width, int height) {
        this.network = network;
        this.ball = ball;
        this.paddle = paddle;
        this.width = width;
        this.height = height;
    }

    public Simulation(NeuralNetwork network, int width, int height) {
        this.network = network;
        this.width = width;
        this.height = height;
        createBall();
        createPaddle();

    }


    public void createBall() {
        ball = new Ball(45, 1, rand.nextInt(width) - 30, 670, 20, 20);
    }

    public void createPaddle() {
        paddle = new Paddle(rand.nextInt(width) - 110, 790, 100, 20);
    }


    public boolean advance() {
        ball.updatePosition();
        ball.setFrame(ball.x, ball.y, ball.width, ball.height);
        networkPlay();
        paddle.setFrame(paddle.getX(), paddle.getY(), paddle.width, paddle.height);
        checkCollisions();
        return !gameOver;
        //view.repaint();
    }

    public void networkPlay() {
        double[] input = new double[INPUT_SIZE];
        input[0] = ball.getCenterX();
        input[1] = paddle.getCenterX();
        double[] answer = network.guess(input);
        if (answer[0] > answer[1]) {
            paddle.moveLeft();
        } else {
            paddle.moveRight();
        }
    }

    public void checkCollisions() {
        if (ball.y <= 0) {
            ball.collideTop();
        } else if (ball.x <= 0 || ball.x + ball.width >= width) {
            ball.collideWall();
        }  else if (ball.y + ball.height >= height) {
            gameOver = true;
            // } else if (score == NUM_BRICKS) {
            //gameOver = true;
        } else if (ball.checkPaddleCollision(paddle)) {
            score++;
        } else {

            // Iterate through each brick
            //            for (int i = 0; i < bricks.length; i++) {
            //                Brick brick = bricks[i];
            //                if (brick != null) {
            //                    if (ball.getBounds2D().intersects(brick.getBounds2D())) {
            //                        ball.collideTop();
            //                        bricks[i] = null;
            //                        score++;
            //                        return;
            //                    }
            //                }
            //            }
        }
    }

    public int getScore() {
        return score;
    }

    public Ball getBall() {
        return ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }


}
