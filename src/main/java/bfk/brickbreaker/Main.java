package bfk.brickbreaker;

public class Main {
    public static void main(String[] args) {
        BBFrame frame = new BBFrame(new BBController(20));
        frame.setVisible(true);
    }

}
