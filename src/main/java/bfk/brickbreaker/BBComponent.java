package bfk.brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.sql.Array;

public class BBComponent extends JComponent {

    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;
    private int width;
    private int height;

    public BBComponent(Ball ball, Paddle paddle, Brick[] bricks, int width, int height) {
        this.ball = ball;
        this.paddle = paddle;
        this.bricks = bricks;
        this.width = width;
        this.height = height;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());


        g.setColor(Color.blue);

        int brickWidth = 30;
        int brickHeight = 60;

        int numCols = width / brickWidth;

        bricks = new Brick[15];

        for (int i = 0; i < bricks.length; i++) {
            int row = i / numCols;
            int col = i % numCols;

            int x = col * brickWidth;
            if (row % 2 != 0) {
                x += brickWidth / 2;
            }
            int y = row * brickHeight + 10;

            bricks[i] = new Brick(x, y, brickWidth, brickHeight);
        }

        // Optional: Print the positions of each brick (for testing purposes)
        for (int i = 0; i < bricks.length; i++) {
            System.out.println("Brick " + (i + 1) + " at (" + bricks[i].getX() + ", " + bricks[i].getY() + ")");
        }

        for (int i = 0; i < bricks.length; i++) {
            g2.draw(bricks[i]);
        }




        g.setColor(Color.black);
        g2.fill(ball);

        g.setColor(Color.red);
        g2.fill(paddle);

    }



}
