package bfk.brickbreaker;

import javax.swing.*;
import java.awt.*;

public class BBComponent extends JComponent {

    private Simulation simulation;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    //private JLabel scoreLabel;


    public BBComponent(Simulation simulation) {
        this.simulation = simulation;
//        scoreLabel = new JLabel("Score: 0", SwingConstants.RIGHT);
//        scoreLabel.setForeground(Color.GREEN);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());  // Clear the screen

        // Draw the brick
        g.setColor(Color.blue);
        if (simulation.getBrick() != null) {
            g2.fill(simulation.getBrick());
        }

        // Draw the ball
        g.setColor(Color.black);
        g2.fill(simulation.getBall());

        // Draw the paddle
        g.setColor(Color.red);
        g2.fill(simulation.getPaddle());

        String scoreText = "Score: " + simulation.getScore();
        g.drawString(scoreText, getWidth() - 120, 30);
    }

}
