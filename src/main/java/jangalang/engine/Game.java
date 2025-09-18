package jangalang.engine;

import jangalang.engine.gamemode.GameMode;
import jangalang.engine.gamemode.MainMenuState;
import jangalang.engine.gamemode.PauseState;
import jangalang.engine.gamemode.PlayingState;
import jangalang.engine.GameState;
import jangalang.game.Player;

public class Game {
    private static GameMode gameMode = new MainMenuState();
    private static Player player = new Player(150, 150, 5);

    public static void setGameState(GameState state) {
        switch (state) {
            case MAIN_MENU -> gameMode = new MainMenuState();
            case PLAYING -> gameMode = new PlayingState();
            case PAUSED -> gameMode = new PauseState();
        }
        System.out.println("State: " + state);
    }

    public static GameMode getMode() {
        return gameMode;
    }

    public static Player getPlayer() {
        return player;
    }
}
