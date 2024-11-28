//package bfk.brickbreaker;
//
//import basicneuralnetwork.NeuralNetwork;
//import org.junit.jupiter.api.Test;
//import org.mockito.stubbing.OngoingStubbing;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.mockito.Mockito.*;
//
//public class SimulationTest {
//    @Test
//    public void hitPaddle() {
//        //given
//        Ball ball = mock(Ball.class);
//        Paddle paddle = mock(Paddle.class);
//        double initialdx = ball.getDx();
//        double initialdy = ball.getDy();
//
//        //when
//        when(ball.checkPaddleCollision(paddle)).thenReturn(true);
//
//        //then
//        assertNotEquals(initialdx, ball.getDx());
//        assertNotEquals(initialdy, ball.getDy());
//
//
//    }
//
//    @Test
//    public void hitWall() {
//        // Given
//        Ball ball = new Ball(145, 10, 260, 680, 20, 20);
//        double initialdx = ball.getDx();
//        double initialdy = ball.getDy();
//
//        // when
//        ball.collideWall();
//
//        //when
//        assertEquals(initialdx, -initialdx);
//        assertEquals(initialdy, initialdy);
//    }
//
//    @Test
//    public void hitTopOrBrick() {
//        // Given
//        Ball ball = new Ball(145, 10, 260, 680, 20, 20);
//        double initialdx = ball.getDx();
//        double initialdy = ball.getDy();
//
//        // when
//        ball.collideTop();
//
//        //then
//        assertEquals(initialdx, initialdx);
//        assertEquals(initialdy, -initialdy);
//    }
//
//
//    @Test
//    public void ballMoves() {
//        // given
//        Ball ball = new Ball(145, 10, 260, 680, 20, 20);
//        double initialx = ball.x;
//        double initialy = ball.y;
//
//        //when
//        ball.updatePosition();
//
//        //then
//        assertNotEquals(ball.x, initialx);
//        assertNotEquals(ball.y, initialy);
//
//
//    }
//}
//
