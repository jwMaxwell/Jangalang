package jangalang.engine;

import jangalang.engine.gamemode.GameMode;
import jangalang.engine.gamemode.HelpState;
import jangalang.engine.gamemode.MainMenuState;
import jangalang.engine.gamemode.PauseState;
import jangalang.engine.gamemode.PlayingState;
import jangalang.game.Player;
import jangalang.engine.maps.Map;
import jangalang.engine.maps.MapLoader;
import jangalang.util.types.Pair;

public class Game {
    private static GameMode gameMode = new MainMenuState();
    private static Map gameMap = MapLoader.parseMap("/maps/test.map");
    private static final Pair<Double, Double> spawn = gameMap.getSpawns().getFirst();
    private static Player player = new Player(spawn.getKey(), spawn.getValue(), 5);

    public static void setGameState(GameState state) {
        switch (state) {
            case MAIN_MENU -> gameMode = new MainMenuState();
            case PLAYING -> gameMode = new PlayingState();
            case PAUSED -> gameMode = new PauseState();
            case HELP -> gameMode = new HelpState();
        }
    }

    public static GameMode getMode() {
        return gameMode;
    }

    public static Player getPlayer() {
        return player;
    }

    public static Map getMap() {
        return gameMap;
    }
}
