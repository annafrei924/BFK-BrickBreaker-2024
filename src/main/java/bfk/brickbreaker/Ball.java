package bfk.brickbreaker;

import java.awt.geom.Ellipse2D;

public class Ball extends Ellipse2D.Double {
    private int angle;
    private final int width;
    private final int height;
    final int XVELOCITY = 10;
    final int YVELOCITY = 10;

    public Ball(int angle, int width, int height) {
        this.angle = angle;
        this.width = width;
        this.height = height;
    }
    public double getLocationX() {
        return getX() + Math.cos(angle) * XVELOCITY;
    }

    public double getLocationY() {
        return getY() + Math.sin(angle) * YVELOCITY;
    }

    //should change the angle
    public void hitWall(boolean hitRight) {

    }

}
