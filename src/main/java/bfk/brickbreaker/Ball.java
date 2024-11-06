package bfk.brickbreaker;

import java.awt.geom.Ellipse2D;

public class Ball extends Ellipse2D.Double {
    private double angle;
    private final int width;
    private final int height;
    private final double SPEED = 10;

    private double x;
    private double y;
    private double velocityX;
    private double velocityY;

    public Ball(double angle, int width, int height, double x, double y) {
        this.angle = angle;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;

        double angleInRadians = Math.toRadians(angle);
        this.velocityX = SPEED * Math.cos(angleInRadians);  // Horizontal component
        this.velocityY = SPEED * Math.sin(angleInRadians);  // Vertical component
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double updateX() {
        x += velocityX;
        return x;
    }

    public double updateY() {
        y += velocityY;
        return y;
    }

    public void bounce(Direction direction) {
        switch(direction) {
            case LEFT, RIGHT -> velocityX = -velocityX;
            case TOP, PADDLE -> velocityY = -velocityY;
        }

    }

}
