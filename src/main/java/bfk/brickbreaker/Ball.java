package bfk.brickbreaker;

import java.awt.geom.Ellipse2D;

public class Ball extends Ellipse2D.Double {
    private double angle; //launch angle, start at 45
    private double speed = 10; //speed, start at 10
    private double velocityX; //x velocity, depends on angle
    private double velocityY; //y velocity, depends on angle


    public Ball(double angle, double speed, double x, double y, int width, int height) {
        super(x, y, width, height);
        this.angle = angle;
        this.speed = speed;

        double angleInRadians = Math.toRadians(angle);
        this.velocityX = speed * Math.cos(angleInRadians);  // Horizontal component
        this.velocityY = speed * Math.sin(angleInRadians);  // Vertical component
    }


    public double updateX() {
        //x is updated and new position is returned
        x += velocityX;
        return x;
    }

    public double updateY() {
        //y is updated and new position is return
        y += velocityY;
        return y;
    }

    public void bounce(Direction direction) {
        // velocity will change --> angle will change on collision
        switch (direction) {
            case LEFT, RIGHT -> velocityX = -velocityX;
            case TOP, BOTTOMPADDLE -> velocityY = -velocityY;
            default -> { }
        }
    }

//    public boolean collides() {
//        return x + width >= width || x - radius <= 0
//                ||  y - radius <= 0 || y + radius >= height;
//
//    }


}
