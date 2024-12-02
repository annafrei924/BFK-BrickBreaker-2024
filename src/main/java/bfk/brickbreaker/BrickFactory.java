package bfk.brickbreaker;

import java.util.Random;

public class BrickFactory {
    private int screenWidth;
    private int screenHeight;
    private int brickWidth;
    private int brickHeight;
    Random random = new Random();

    public BrickFactory(int screenWidth, int screenHeight, int brickWidth, int brickHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight / 2;
        this.brickWidth = brickWidth;
        this.brickHeight = brickHeight;
    }

    public Brick newBrick() {
        return new Brick(random.nextInt(screenWidth - brickWidth),
                random.nextInt(screenHeight / 2), brickWidth, brickHeight);
    }

}
