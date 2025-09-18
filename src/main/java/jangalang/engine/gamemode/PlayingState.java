package jangalang.engine.gamemode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import jangalang.engine.Game;
import jangalang.engine.GameState;
import jangalang.game.Player;

import java.util.HashSet;

public class PlayingState implements GameMode {
    HashSet<String> keySet = new HashSet<String>();
    Player player = Game.getPlayer();

    @Override
    public void update() {
        for (String key : keySet) {
            switch (key) {
                case "w" -> player.move(0, -1);
                case "a" -> player.move(-1, 0);
                case "s" -> player.move(0, 1);
                case "d" -> player.move(1, 0);
            }
        }
    }

    @Override
    public void render(JPanel window, Graphics g) {
        window.setBackground(Color.BLACK);

        g.setColor(Color.RED);
        g.fillOval((int)player.getXCoord(), (int)player.getYCoord(), player.getSize(), player.getSize());
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
    public void mouseClicked(MouseEvent e) {

    }
}
