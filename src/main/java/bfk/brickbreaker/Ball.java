package bfk.brickbreaker;

import java.awt.geom.Ellipse2D;
import static bfk.brickbreaker.BBFrame.*;
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

    // Update the ball's position, checking for boundaries and resetting if needed
    public void updatePosition() {
        updateX();
        updateY();
        clampPosition();
        resetPositionIfOffScreen();
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double newAngle) {
        angle = newAngle;
    }

}

