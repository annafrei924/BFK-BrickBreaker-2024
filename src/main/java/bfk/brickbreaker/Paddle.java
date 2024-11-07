package bfk.brickbreaker;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Paddle extends Rectangle {
    private final int speed = 5;

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void moveLeft() {
        if (x > 0) {
            x -= speed;
        }
    }

    public void moveRight() {
        if (x < 600 - width) {
            x += speed;
        }
    }




}
