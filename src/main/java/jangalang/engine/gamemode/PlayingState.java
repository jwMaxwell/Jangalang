package jangalang.engine.gamemode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import jangalang.engine.Game;
import jangalang.engine.GameState;
import jangalang.game.Player;
import jangalang.game.Vector;

import java.util.HashSet;

public class PlayingState implements GameMode {
    private HashSet<String> keySet = new HashSet<String>();
    private Player player = Game.getPlayer();
    private int mouseX = 0;

    @Override
    public void update() {
        Vector direction = player.getViewAngle();
        for (String key : keySet) {
            switch (key) {
                case "w" -> player.move(direction.x, direction.y);
                case "a" -> player.move(direction.y, -direction.x);
                case "s" -> player.move(-direction.x, -direction.y);
                case "d" -> player.move(-direction.y, direction.x);
            }
        }
    }

    @Override
    public void render(JPanel window, Graphics g) {
        window.setBackground(Color.BLACK);

        // Draw player
        g.setColor(Color.RED);
        g.fillOval((int)player.getXCoord(), (int)player.getYCoord(), player.getSize(), player.getSize());

        // Draw rays
        for (Vector v : player.getRays()) {
            g.setColor(v.equals(player.getViewAngle()) ? Color.YELLOW : Color.GREEN);
            g.drawLine((int)player.getXCoord() + player.getSize() / 2,
                       (int)player.getYCoord() + player.getSize() / 2,
                       (int)player.getXCoord() + (int)(v.x * 100), // TODO: replace `* 100` with a marching function
                       (int)player.getYCoord() + (int)(v.y * 100));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> keySet.add("w");
            case KeyEvent.VK_A -> keySet.add("a");
            case KeyEvent.VK_S -> keySet.add("s");
            case KeyEvent.VK_D -> keySet.add("d");
            case KeyEvent.VK_ESCAPE -> Game.setGameState(GameState.PAUSED);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> keySet.remove("w");
            case KeyEvent.VK_A -> keySet.remove("a");
            case KeyEvent.VK_S -> keySet.remove("s");
            case KeyEvent.VK_D -> keySet.remove("d");
        }
    }

    @Override
    public void mouseMoved(int e) {
        player.rotate(e * 0.01);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
}
