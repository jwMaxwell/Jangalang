package jangalang.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyScanner implements KeyListener {
    private static HashMap<String, Boolean> keyState;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W -> keyState.put("w", true);
            case KeyEvent.VK_A -> keyState.put("a", true);
            case KeyEvent.VK_S -> keyState.put("s", true);
            case KeyEvent.VK_D -> keyState.put("d", true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
     int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W -> keyState.put("w", false);
            case KeyEvent.VK_A -> keyState.put("a", false);
            case KeyEvent.VK_S -> keyState.put("s", false);
            case KeyEvent.VK_D -> keyState.put("d", false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public boolean isPressed(String key) {
        return keyState.get(key);
    }
}
