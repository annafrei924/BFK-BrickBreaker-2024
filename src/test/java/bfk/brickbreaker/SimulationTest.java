package bfk.brickbreaker;

import basicneuralnetwork.NeuralNetwork;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

public class SimulationTest {
    @Test
    public void hitPaddle() {
        //given
        Ball ball = new Ball(45, 2, 400, 781, 20, 20);
        Paddle paddle = new Paddle(350, 790, 100, 20);

        double initialdx = ball.getDx();
        double initialdy = ball.getDy();

        //when
        ball.checkPaddleCollision(paddle);

        //then
        assertNotEquals(initialdx, ball.getDx());
        assertNotEquals(initialdy, ball.getDy());


    }

    @Test
    public void hitWall() {
        // Given
        Ball ball = new Ball(145, 10, 260, 680, 20, 20);
        double initialdx = ball.getDx();
        double initialdy = ball.getDy();

        // when
        ball.collideWall();

        //when
        assertEquals(initialdx, -ball.getDx());
        assertEquals(initialdy, ball.getDy());
    }

    @Test
    public void hitTopOrBrick() {
        // Given
        Ball ball = new Ball(145, 10, 260, 680, 20, 20);
        double initialdx = ball.getDx();
        double initialdy = ball.getDy();


        // when
        ball.collideTop();

        //then
        assertEquals(initialdx, ball.getDx());
        assertEquals(initialdy, -ball.getDy());
    }

    @Test
    public void ballMoves() {
        // given
        Ball ball = new Ball(145, 10, 260, 680, 20, 20);
        double initialx = ball.x;
        double initialy = ball.y;

        //when
        ball.updatePosition();

        //then
        assertNotEquals(ball.x, initialx);
        assertNotEquals(ball.y, initialy);


    }
}

