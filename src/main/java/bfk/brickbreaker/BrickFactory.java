package bfk.brickbreaker;

public class BrickFactory {
    private int screenWidth;
    private int screenHeight;
    private int brickWidth;
    private int brickHeight;

    public BrickFactory(int screenWidth, int screenHeight, int brickWidth, int brickHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.brickWidth = brickWidth;
        this.brickHeight = brickHeight;
    }

    public Brick newBrick() {
        return new Brick(0, 0, brickWidth, brickHeight);
    }

}
