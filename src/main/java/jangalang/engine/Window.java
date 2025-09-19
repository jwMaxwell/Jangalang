package jangalang.engine;

import javax.swing.JFrame;
import javax.swing.JComponent;
import jangalang.util.GameProperties;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public class Window {
    private static final int WIDTH = GameProperties.getInt("window.width");
    private static final int HEIGHT = WIDTH / 12 * 9;
    private static JFrame gameWindow;
    private static JComponent renderer;
    private static KeyListener keyScanner;
    private static MouseScanner mouseScanner;

    public Window(JComponent renderer, KeyListener keyScanner, MouseScanner mouseScanner) {
        this.renderer = renderer;
        this.keyScanner = keyScanner;
        this.mouseScanner = mouseScanner;
        this.gameWindow = this.createWindow();
    }

    private JFrame createWindow() {
        JFrame result = new JFrame();
        result.setSize(WIDTH, HEIGHT);
        result.setResizable(false); // Frick u, we pick the window size
        result.setLocationRelativeTo(null);
        result.setTitle(GameProperties.get("game.name"));
        result.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        result.setVisible(true);

        result.add(this.renderer);
        result.addKeyListener(this.keyScanner);
        result.addMouseListener(this.mouseScanner);
        result.addMouseMotionListener(this.mouseScanner);

        return result;
    }

    public static JComponent getRenderer() {
        return renderer;
    }
}
