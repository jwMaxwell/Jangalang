import java.util.Timer;
import java.util.TimerTask;

public class GameLoop {
    private static Timer deltaTime;
    private static final long tickRate = 1000 / 60;

    public static void run() {
        deltaTime = new Timer();
        deltaTime.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                // update()
                // render()
                // do stuff
            }
        }, 0, tickRate);
    }

}
