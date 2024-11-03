package bfk.brickbreaker;

import java.awt.geom.Ellipse2D;

public class Ball extends Ellipse2D.Double {
    private int angle;
    private final int width;
    private final int height;
    final int VELOCITY = 10;

    public Ball(int angle, int width, int height) {
        this.angle = angle;
        this.width = width;
        this.height = height;
    }
    public double getLocationX() {
        return getX() + Math.cos(angle) * VELOCITY;
    }

    public double getLocationY() {
        return getY() + Math.sin(angle) * VELOCITY;
    }

    public int bounce(int direction) {
        angle = direction + 90;
        return angle;
    }

}
