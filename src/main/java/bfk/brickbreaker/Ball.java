package bfk.brickbreaker;

import java.awt.geom.Ellipse2D;
import static bfk.brickbreaker.BBFrame.*;
public class Ball extends Ellipse2D.Double {
    private double angle;

    private double speed;
    private double dx;
    private double dy;

    public Ball(double angle, double speed, double x, double y, int width, int height) {
        super(x, y, width, height);
        this.angle = angle;
        this.speed = speed;
        dx = speed * Math.cos(Math.toRadians(angle));
        dy = speed * Math.sin(Math.toRadians(angle));


    }

    // Update the ball's position, checking for boundaries and resetting if needed
    public void updatePosition() {
        x += dx;
        y -= dy;
        clampPosition();
        resetPositionIfOffScreen();
    }

    //checks if ball collides with paddle
    public boolean checkPaddleCollision(Paddle paddle) {
        if (intersects(paddle.getBounds2D())) {
            //angle change
            double paddlePosition = getCenterX() - paddle.getX();
            if (paddlePosition < paddle.width / 4) {
                setAngle(45);
            } else if (paddlePosition < paddle.width / 2) {
                setAngle(75);
            } else if (paddlePosition == paddle.width / 2) {
                setAngle(90);
            } else if (paddlePosition < paddle.width * 3.0 / 4.0) {
                setAngle(105);
            } else {
                setAngle(135);
            }
            dx = speed * Math.cos(Math.toRadians(angle));
            // the first is physically correct but told to use second for now
            //dy = speed * Math.sin(Math.toRadians(angle));
            dy = -dy;
            return true;
        }
        return false;
    }


    public void collideWall() {
        dx = -dx;
    }

    public void collideTop() {
        dy = -dy;
    }

    // Clamp the ball's position to the screen bounds (0 <= x <= screenWidth, 0 <= y <= screenHeight)
    private void clampPosition() {
        x = Math.max(0, Math.min(x, BBComponent.WIDTH - width));
        y = Math.max(0, Math.min(y, BBComponent.HEIGHT - height));
    }

    // Check if the ball is off the screen, if it is, reset to the center
    public void resetPositionIfOffScreen() {
        if (x < 0 || x > BBComponent.WIDTH || y < 0 || y > BBComponent.HEIGHT) {
            x = BBComponent.WIDTH / 2 - width / 2;
            y = BBComponent.HEIGHT / 2 - height / 2;
        }
    }


    public double getAngle() {
        return angle;
    }

    public void setAngle(double newAngle) {
        angle = newAngle;
    }



}

