package bfk.brickbreaker;


import java.awt.*;
import java.util.Random;

public class Brick extends Rectangle {
    Random rand = new Random();
    int [] bricks = new int[10];

    private void populateBricks() {
        for (int i = 0; i < bricks.length; i++) {
            if (rand.nextInt(0, 3) == 1) {
                bricks[i] = 1;
            } else {
                bricks[i] = 0;
            }
        }
    }

    private void hitBrick(int x) {
        bricks[x] = 0;
    }
}

