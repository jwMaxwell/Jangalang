package jangalang;

import jangalang.engine.Window;
import jangalang.engine.Renderer;
import jangalang.engine.GameLoop;
import jangalang.engine.KeyScanner;
import jangalang.engine.MouseScanner;

class Main {
    public static void main(String[] args) {
        new Window(new Renderer(), new KeyScanner(), new MouseScanner());
        GameLoop.run();
    }
}
