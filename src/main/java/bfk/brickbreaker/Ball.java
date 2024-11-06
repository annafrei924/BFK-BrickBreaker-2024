package bfk.brickbreaker;

import java.awt.geom.Ellipse2D;

public class Ball extends Ellipse2D.Double {
    private double angle; //launch angle, start at 45
    private final int width; //not sure
    private final int height; //not sure
    private final double speed = 10; //speed, start at 10
    private final int radius; //radius of ball

    private double x; //x location
    private double y; //y location
    private double velocityX; //x velocity, depends on angle
    private double velocityY; //y velocity, depends on angle


    public Ball(double angle, int width, int height, double x, double y, int radius) {
        this.angle = angle;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.radius = radius;

        double angleInRadians = Math.toRadians(angle);
        this.velocityX = speed * Math.cos(angleInRadians);  // Horizontal component
        this.velocityY = speed * Math.sin(angleInRadians);  // Vertical component
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getRadius() {
        return radius;
    }

    //x is updated and new position is returned

    public double updateX() {
        x += velocityX;
        return x;
    }

    //y is updated and new position is return

    public double updateY() {
        y += velocityY;
        return y;
    }

    // velocity will change --> angle will change on collision

    public void bounce(Direction direction) {
        switch(direction){
            case LEFT, RIGHT -> velocityX = -velocityX;
            case TOP, PADDLE -> velocityY = -velocityY;
            default -> {}
        }
    }

    //checks if ball collides with a wall

    public boolean collides() {
        if (x + radius >= width || x - radius <= 0
                ||  y - radius <= 0 || y + radius >= height) {
            return true;
        }
        else {
            return false;
        }
    }


}
