package jangalang.game;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Renderer extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.GREEN);
    }
}
