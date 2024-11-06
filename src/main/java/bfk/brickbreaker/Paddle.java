package bfk.brickbreaker;

import java.awt.event.MouseEvent;

public class Paddle {
    private final int width;
    private final int height;
    private int x;
    private int y;

    public Paddle(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }


    public void movePaddle(MouseEvent e, Boolean isMoving, int changeX) {
        if (isMoving) {
            int currX = e.getXOnScreen();

            setLocation(currX - changeX, y);
        }
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
