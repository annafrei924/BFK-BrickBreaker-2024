package bfk.brickbreaker;

import java.awt.*;



public class Paddle extends Rectangle {
    private final int speed = 10;

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void moveLeft() {
        if (x > 0) {
            x -= speed;
        }
    }

    public void moveRight() {
        if (x < BBFrame.width - width) {
            x += speed;
        }
    }
}
