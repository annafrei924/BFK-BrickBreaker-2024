package bfk.brickbreaker;


import java.awt.*;
import java.util.Random;

public class Brick extends Rectangle {
    Random rand = new Random();
    private int[][] field;

    public Brick(int width, int height) {
        field = new int[height][width];
    }

    public double getWidth() {
        return field[0].length;
    }

    public double getHeight() {
        return field.length;
    }

    private void populateBricks() {
        Random rand = new Random();
        for (int col = 0; col < getWidth(); col++) {
            if (rand.nextDouble() < 1.0 / 3.0) {
                field[0][col] = 1;
            }
        }
    }

}

