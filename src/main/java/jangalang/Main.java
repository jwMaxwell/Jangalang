package jangalang;

import jangalang.engine.Window;
import jangalang.engine.Renderer;
import jangalang.engine.GameLoop;
import jangalang.engine.KeyScanner;
import jangalang.engine.MouseScanner;

class Main {
    private static Renderer renderer;
    private static KeyScanner keyScanner;
    private static MouseScanner mouseScanner;

    public static void main(String[] args) {
        renderer = new Renderer();
        keyScanner = new KeyScanner();
        mouseScanner = new MouseScanner();

        new Window(renderer, keyScanner, mouseScanner);
        GameLoop.run();
    }
}
