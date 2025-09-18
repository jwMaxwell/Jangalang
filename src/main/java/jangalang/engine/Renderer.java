package jangalang.engine;

import javax.swing.JPanel;
import java.awt.Graphics;
import jangalang.engine.Game;
import jangalang.util.GameProperties;
import java.util.Timer;
import java.util.TimerTask;

public class Renderer extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Game.getMode().render(this, g);
    }
}
