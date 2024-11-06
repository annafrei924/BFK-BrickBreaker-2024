package bfk.brickbreaker;

import java.awt.event.MouseEvent;

public class Paddle {
    private final int width;
    private final int height;
    private int x;
    private int y;

    public Paddle(int width, int height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }


    public void movePanel(MouseEvent e, Boolean isMoving, int changeX, int changeY) {
        if (isMoving) {
            int currX = e.getXOnScreen();
            int currY = e.getYOnScreen();

            setLocation(currX - changeX, currY - changeY);
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

}
