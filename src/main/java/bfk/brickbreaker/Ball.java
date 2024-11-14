package bfk.brickbreaker;

import java.awt.geom.Ellipse2D;

public class Ball extends Ellipse2D.Double {
    private double angle;
    private double speed;

    public Ball(double angle, double speed, double x, double y, int width, int height) {
        super(x, y, width, height);
        this.angle = angle;
        this.speed = speed;

    }


    public double updateX() {
        x += speed * Math.cos(Math.toRadians(angle));
        return x;
    }

    public double updateY() {
        y -= speed * Math.sin(Math.toRadians(angle));
        return y;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double newAngle) {
        angle = newAngle;
    }

}
