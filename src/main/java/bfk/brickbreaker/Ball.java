package bfk.brickbreaker;

import java.awt.geom.Ellipse2D;

public class Ball extends Ellipse2D.Double {
    private double angle; //launch angle, start at 45
    private double speed = 10; //speed, start at 10

    double angleInRadians = Math.toRadians(angle);

    public Ball(double angle, double speed, double x, double y, int width, int height) {
        super(x, y, width, height);
        this.angle = angle;
        this.speed = speed;

    }


    public double updateX() {
        x += speed * Math.cos(angleInRadians);;
        return x;
    }

    public double updateY() {
        y += speed * Math.sin(angleInRadians);
        return y;
    }

    public void setPosition(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }


    public void bounce(Direction direction) {
        switch (direction) {
            case LEFT, RIGHT, TOP, BRICK -> angle = 180 - angle;
            case BOTTOMPADDLE -> { }
            default -> { }
        }
    }


}
