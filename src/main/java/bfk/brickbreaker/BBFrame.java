package bfk.brickbreaker;

import javax.swing.*;
import java.awt.*;


public class BBFrame extends JFrame {

    private BBComponent view;

    public BBFrame(Simulation simulation) {
        setTitle("Brick Breaker");
        setSize(BBComponent.WIDTH, BBComponent.HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        view = new BBComponent(simulation);
        add(view, BorderLayout.CENTER);
        setFocusable(true);
        requestFocusInWindow();
    }

    public BBComponent getView() {
        return view;
    }
}
