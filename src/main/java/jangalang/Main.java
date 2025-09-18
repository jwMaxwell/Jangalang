package jangalang;

import javax.swing.JFrame;
import jangalang.util.GameProperties;
import jangalang.engine.KeyScanner;
import jangalang.game.Renderer;

class Main {
    public static final int WIDTH = Integer.parseInt(GameProperties.get("window.width"));
    public static final int HEIGHT = WIDTH / 12 * 9;

    public static void main(String[] args) {
        JFrame gameWindow = new JFrame();
        gameWindow.setSize(WIDTH, HEIGHT);
        gameWindow.setResizable(false); // Frick u, we pick the window size
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setTitle(GameProperties.get("game.name"));
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setVisible(true);
        gameWindow.add(new Renderer());
        gameWindow.addKeyListener(new KeyScanner());

        return;
    }
}
