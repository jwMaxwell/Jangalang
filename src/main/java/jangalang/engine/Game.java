package jangalang.engine;

import jangalang.engine.gamemode.GameMode;
import jangalang.engine.gamemode.MainMenuState;
import jangalang.engine.gamemode.PlayingState;
import jangalang.engine.GameState;

public class Game {
    private static GameMode gameMode = new MainMenuState();

    public static void setGameState(GameState state) {
        switch (state) {
            case MAIN_MENU -> gameMode = new MainMenuState();
            case PLAYING -> gameMode = new PlayingState();
        }
        System.out.println("State: " + state);
    }

    public static GameMode getMode() {
        return gameMode;
    }
}
