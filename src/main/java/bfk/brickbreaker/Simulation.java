package bfk.brickbreaker;


import basicneuralnetwork.NeuralNetwork;

import java.util.Random;

public class Simulation {
    public static final int  BRICK_WIDTH = 60;
    public static final int BRICK_HEIGHT = 20;
    private static final int INPUT_SIZE = 4;
    private NeuralNetwork network;
    private Ball ball;
    private Paddle paddle;
    private BrickFactory brickFactory;
    private Brick brick = null;
    private final int width;
    private final int height;
    private boolean gameOver;
    private int score;
    private boolean lastHitPaddle;
    Random rand = new Random();

    public Simulation(NeuralNetwork network, Ball ball, Paddle paddle, int width, int height) {
        this.network = network;
        this.ball = ball;
        this.paddle = paddle;
        this.width = width;
        this.height = height;
        createBrickFactory();
    }

    public Simulation(NeuralNetwork network, int width, int height) {
        this.network = network;
        this.width = width;
        this.height = height;
        createBall();
        createPaddle();
        createBrickFactory();

    }


    public void createBall() {
        ball = new Ball(45, 2, rand.nextInt(width) - 30, 670, 20, 20);
    }

    public void createPaddle() {
        paddle = new Paddle(rand.nextInt(width) - 110, 790, 100, 20);
    }

    public void createBrickFactory() {
        brickFactory = new BrickFactory(BBComponent.WIDTH, BBComponent.HEIGHT, BRICK_WIDTH, BRICK_HEIGHT);
    }

    public boolean advance() {
        if (brick == null) {
            brick = brickFactory.newBrick();
        }
        ball.updatePosition();
        ball.setFrame(ball.x, ball.y, ball.width, ball.height);
        networkPlay();
        paddle.setFrame(paddle.getX(), paddle.getY(), paddle.width, paddle.height);
        checkCollisions();
        return !gameOver;
    }

    public void networkPlay() {
        double[] input = new double[INPUT_SIZE];
        input[0] = ball.getCenterX();
        input[1] = paddle.getCenterX();
        input[2] = brick.getCenterX();
        input[3] = brick.getCenterY();
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
        } else if (ball.checkPaddleCollision(paddle)) {
            if (!lastHitPaddle) {
                score++;
            }
            lastHitPaddle = true;
        } else if (ball.checkBrickCollision(brick)) {
            if (lastHitPaddle) {
                score++;
            }
            lastHitPaddle = false;
            brick = null;
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

    public Brick getBrick() {
        return brick;
    }


}
