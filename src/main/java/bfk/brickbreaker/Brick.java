package bfk.brickbreaker;


import java.awt.*;
import java.util.Random;

public class Brick extends Rectangle {
    Random rand = new Random();
    private int[][] bricks;
    private final int width;
    private final int height;

    public Brick(int width, int height) {
        this.width = width;
        this.height = height;
        bricks = new int[height][width];
    }

    public double getWidth() {
        return bricks[0].length;
    }

    public double getHeight() {
        return bricks.length;
    }

    private void populateBricks() {
        Random rand = new Random();
        for (int col = 0; col < getWidth(); col++) {
            if (rand.nextDouble() < 1.0 / 2.0) {
                bricks[0][col] = 1;
            }
        }
    }

    private void brickHit(int x, int y) {
        bricks[x][y] = 0;
    }

    private void newRound() {
        for (int i = bricks.length - 2; i >= 0; i--) {
            for (int j = 0; j < bricks[0].length; j++) {
                if (bricks[i][j] == 1) {
                    bricks[i + 1][j] = 1;  // Move brick down
                    bricks[i][j] = 0;       // Clear the original position
                }
            }
        }
        populateBricks();
    }

}

