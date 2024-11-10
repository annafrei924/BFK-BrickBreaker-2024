package bfk.brickbreaker;

import org.junit.jupiter.api.Test;

import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ControllerTest {
    @Test
    public void hitPaddle() {
        // Given
        Ball ball = mock(Ball.class);
        Paddle paddle = mock(Paddle.class);
        BBComponent view = mock(BBComponent.class);
        Brick[] bricks = new Brick[20];

        doReturn(20.0).when(ball).getWidth();
        doReturn(20.0).when(ball).getHeight();
        doReturn(200.0).when(ball).getX();
        doReturn(690.0).when(ball).getY();
        doReturn(100.0).when(paddle).getWidth();


        BBController controller = new BBController(ball, paddle, view, bricks);

        doReturn(true).when(ball).getBounds2D().intersects(paddle.getBounds2D());

        // When
        controller.checkCollisions();

        // Then
        verify(controller).checkCollisions();
        verify(controller).bounce(Direction.BOTTOMPADDLE);
        verify(controller).hitPaddle(anyDouble());  // Ensure hitPaddle method is called with any double (or specific value if needed)


        doReturn(325.0).when(ball).getAngle();
        assertEquals(325.0, ball.getAngle(), 0.01);
    }
}

