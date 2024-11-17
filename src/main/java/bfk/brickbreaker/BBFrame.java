package bfk.brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BBFrame extends JFrame {
    static final int NUM_BRICKS = 20;
    static final int width = 600;
    static final int height = 800;
    static final int BRICK_WIDTH = 60;
    static final int BRICK_HEIGHT = 20;
    private int time = 0;
    BBController bbController = new BBController();

    public BBFrame() {
        setTitle("Brick Breaker");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


//        JButton startButton = new JButton("Start");
//        startButton.addActionListener(e -> {
        bbController.startTimer();
        requestFocusInWindow();
//        });

//        JPanel bottomPanel = new JPanel();
//        bottomPanel.add(startButton);
//        add(bottomPanel, BorderLayout.SOUTH);


        add(bbController.getView(), BorderLayout.CENTER);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    bbController.getPaddle().moveLeft();
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    bbController.getPaddle().moveRight();
                }
                repaint();
            }
        });
        setFocusable(true);
    }
}
