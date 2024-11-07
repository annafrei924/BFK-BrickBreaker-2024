package bfk.brickbreaker;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Paddle extends Rectangle {
    private final int SPEED = 2;

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public void moveLeft() {
        if (x > 0) {
            x -= SPEED;
        }
    }

    public void moveRight() {
        if (x < 600 - width) {
            x += SPEED;
        }
    }




}
