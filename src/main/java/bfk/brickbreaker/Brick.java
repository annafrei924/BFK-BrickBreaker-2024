package bfk.brickbreaker;


import java.awt.*;

public class Brick extends Rectangle {

    public Brick(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

//    public void initialPopulate() {
//        for (int col = 0; col < getCols(); col++) {
//            for (int row = 0; row < 4; row++) {
//                if (rand.nextDouble() < 1.0 / 4.0) {
//                    bricks[row][col] = 1;
//                }
//            }
//        }
//    }
//
//    public void populateBricks() {
//        //randomly populate top row with new bricks
//        for (int col = 0; col < getWidth(); col++) {
//            if (rand.nextBoolean()) {
//                bricks[0][col] = 1;
//            }
//        }
//    }
//    private void newRound() {
//        //all bricks move down and top row is repopulated
//        for (int i = bricks.length - 2; i >= 0; i--) {
//            for (int j = 0; j < bricks[0].length; j++) {
//                if (bricks[i][j] == 1) {
//                    bricks[i + 1][j] = 1;
//                    bricks[i][j] = 0;
//                }
//            }
//        }
//        populateBricks();
//    }

}

