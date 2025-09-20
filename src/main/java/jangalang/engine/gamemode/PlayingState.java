package jangalang.engine.gamemode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import jangalang.engine.Game;
import jangalang.engine.GameState;
import jangalang.engine.maps.Map;
import jangalang.engine.maps.Wall;
import jangalang.game.Player;
import jangalang.util.types.Vector;
import jangalang.util.GameProperties;

import java.util.HashSet;

public class PlayingState implements GameMode {
    private HashSet<String> keySet = new HashSet<String>();
    private Player player = Game.getPlayer();
    private Map gameMap = Game.getMap();

    @Override
    public void update() {
        Vector direction = player.getViewAngle();
        for (String key : keySet) {
            switch (key) {
                case "w" -> player.move(gameMap, direction.x, direction.y);
                case "a" -> player.move(gameMap, direction.y, -direction.x);
                case "s" -> player.move(gameMap, -direction.x, -direction.y);
                case "d" -> player.move(gameMap, -direction.y, direction.x);
            }
        }
    }

    @Override
    public void render(JPanel window, Graphics g) {
        window.setBackground(Color.BLACK);

        // Draw walls
        g.setColor(Color.GRAY);
        for (Wall wall : gameMap.getWalls()) {
            g.drawLine((int)(double) wall.start.getKey(),
                       (int)(double)wall.start.getValue(),
                       (int)(double)wall.end.getKey(),
                       (int)(double)wall.end.getValue());
        }

        // Draw player
        g.setColor(Color.RED);
        g.fillOval((int)player.getXCoord(), (int)player.getYCoord(), player.getSize(), player.getSize());

        // Draw rays
        for (Vector v : player.getRays()) {
            double closest = Double.MAX_VALUE;

            // Calculate ray length
            for (Wall wall : gameMap.getWalls()) {
                Double u = wall.rayIntersect(
                    player.getXCoord() + player.getSize() / 2,
                    player.getYCoord() + player.getSize() / 2,
                    v.x, v.y
                );
                if (u != null && u < closest) closest = u;
            }

            // Draw that sucker
            if (closest != Double.MAX_VALUE) {
                g.drawLine(
                    (int)player.getXCoord() + player.getSize() / 2,
                    (int)player.getYCoord() + player.getSize() / 2,
                    (int)(player.getXCoord() + player.getSize() / 2 + v.x * Math.min(closest, Player.RAY_MAX_LENGTH)),
                    (int)(player.getYCoord() + player.getSize() / 2 + v.y * Math.min(closest, Player.RAY_MAX_LENGTH))
                );
            }
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
        player.rotate(e * GameProperties.getDouble("game.user.sensitivity"));
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
}
