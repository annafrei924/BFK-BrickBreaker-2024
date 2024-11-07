package bfk.brickbreaker;

import java.awt.geom.Ellipse2D;

public class Ball extends Ellipse2D.Double {
    private double angle;
    private double speed; //speed, start at 10

    public Ball(double angle, double speed, double x, double y, int width, int height) {
        super(x, y, width, height);
        this.angle = 360 - angle;
        this.speed = speed;

    }


    public double updateX() {
        x += speed * Math.cos(Math.toRadians(angle));
        return x;
    }

    public double updateY() {
        y += speed * Math.sin(Math.toRadians(angle));
        return y;
    }

    public void setPosition(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }


    public void bounce(Direction direction) {
        switch (direction) {
            case TOP, BRICK -> angle = 360 - angle;
            case RIGHT, LEFT -> angle = 180 - angle;
            case BOTTOMPADDLE -> { }
            default -> { }
        }
    }


}
