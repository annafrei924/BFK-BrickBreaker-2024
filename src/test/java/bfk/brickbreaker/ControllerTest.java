package bfk.brickbreaker;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

public class ControllerTest {
    @Test
    public void hitPaddle() {
        // Given
        Ball ball = new Ball(145, 10, 260, 680, 20, 20);
        Paddle paddle = new Paddle(250, 690, 100, 20);
        BBComponent view = mock(BBComponent.class);
        Brick[] bricks = new Brick[20];

        BBController controller = new BBController(ball, paddle, view, bricks);

        controller.hitPaddle(10);
        assertEquals(325, ball.getAngle());

        controller.hitPaddle(40);
        assertEquals(290, ball.getAngle());

        controller.hitPaddle(50);
        assertEquals(270, ball.getAngle());

        controller.hitPaddle(70);
        assertEquals(250, ball.getAngle());

        controller.hitPaddle(90);
        assertEquals(215, ball.getAngle());


    }

    @Test
    public void hitWall() {
        // Given
        Ball ball = new Ball(145, 10, 260, 680, 20, 20);
        Paddle paddle = mock(Paddle.class);
        BBComponent view = mock(BBComponent.class);

        Brick[] bricks = new Brick[20];

        BBController controller = new BBController(ball, paddle, view, bricks);

        // when
        ball.setAngle(45);
        controller.bounce(Direction.TOP);
        // then
        assertEquals(315, ball.getAngle());

        // when
        ball.setAngle(135);
        controller.bounce(Direction.LEFT);
        // then
        assertEquals(45, ball.getAngle());

        // when
        ball.setAngle(45);
        controller.bounce(Direction.RIGHT);
        // then
        assertEquals(135, ball.getAngle());
    }


    @Test
    public void ballMoves() {
        // given
        Ball ball = new Ball(145, 10, 260, 680, 20, 20);
        Paddle paddle = mock(Paddle.class);
        BBComponent view = mock(BBComponent.class);
        Brick[] bricks = new Brick[20];

        BBController controller = new BBController(ball, paddle, view, bricks);

        double initialX = ball.getX();
        double initialY = ball.getY();

        // when
        controller.playGame();

        // then
        assertNotEquals(initialX, ball.x);
        assertNotEquals(initialY, ball.y);
    }
}

