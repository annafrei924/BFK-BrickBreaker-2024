package bfk.brickbreaker;


import java.awt.*;
import java.util.Random;

public class Brick extends Rectangle {
    Random rand = new Random();
    private int[][] bricks; //field of all the bricks
    private final int width; //width of a brick
    private final int height; //height of a brick
    private final int cols = 15; //cols of bricks in grid

    private final int rows = 10; //rows of bricks in grid

    public Brick(int width, int height, int cols, int rows) {
        this.width = width;
        this.height = height;
        bricks = new int[rows][cols];
    }

    public double getWidth() {
        return bricks[0].length;
    }

    public double getHeight() {
        return bricks.length;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    private void populateBricks() {
        //randomly populate top row with new bricks
        for (int col = 0; col < getWidth(); col++) {
            if (rand.nextDouble() < 1.0 / 2.0) {
                bricks[0][col] = 1;
            }
        }
    }

    public void brickHit(int x, int y) {
        //brick disappears when it gets hit
        bricks[x][y] = 0;
    }

    public boolean isBrick(int x, int y) {
        //is there a brick?
        return bricks[x][y] == 1;
    }



    private void newRound() {
        //all bricks move down and top row is repopulated
        for (int i = bricks.length - 2; i >= 0; i--) {
            for (int j = 0; j < bricks[0].length; j++) {
                if (bricks[i][j] == 1) {
                    bricks[i + 1][j] = 1;
                    bricks[i][j] = 0;
                }
            }
        }
        populateBricks();
    }

}

