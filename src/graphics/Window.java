package graphics;

import javax.swing.*;

public class Window {
    public JFrame frame;
    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;

    public Window() {

        frame = new JFrame();
        frame.setSize(Window.WIDTH, Window.HEIGHT);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
