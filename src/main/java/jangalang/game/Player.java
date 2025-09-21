package jangalang.game;

import jangalang.engine.maps.Map;
import jangalang.engine.maps.Wall;
import jangalang.util.GameProperties;
import jangalang.util.types.Vector;

public class Player {
    private double xCoord;
    private double yCoord;
    private int size;

    private double velX = 0;
    private double velY = 0;

    private static final double ACCEL = 0.8;
    private static final double MAX_SPEED = 2;
    private static final double FRICTION = 0.85;

    public static double RAY_MAX_LENGTH = GameProperties.getDouble("game.user.viewdist");
    public static int RAY_COUNT = GameProperties.getInt("game.user.resolution");
    public static double FOV = Math.toRadians(GameProperties.getInt("game.user.fov"));
    private Vector[] rays = new Vector[RAY_COUNT];
    private double viewAngleOffset = 0;

    public Player (double xCoord, double yCoord, int size) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.size = size;

        for (int i = 0; i < rays.length; ++i) {
            final double angle = this.viewAngleOffset - FOV / 2 + (FOV * i / (RAY_COUNT - 1));
            rays[i] = new Vector(Math.cos(angle), Math.sin(angle));
        }
    }

    public void move(Map gameMap, double dirX, double dirY, boolean accelerating) {
        if (accelerating) {
            // Normalize direction
            double len = Math.sqrt(dirX * dirX + dirY * dirY);
            if (len > 0) {
                dirX /= len;
                dirY /= len;
            }

            velX += dirX * ACCEL;
            velY += dirY * ACCEL;

            double speed = Math.sqrt(velX * velX + velY * velY);
            if (speed > MAX_SPEED) {
                velX = (velX / speed) * MAX_SPEED;
                velY = (velY / speed) * MAX_SPEED;
            }
        } else {
            velX *= FRICTION;
            velY *= FRICTION;
        }

        double newX = this.xCoord + velX;
        double newY = this.yCoord + velY;

        double radius = this.size / 2.0;
        for (Wall wall : gameMap.getWalls()) {
            if (wall.playerIntersect(newX, newY, radius)) {
                return; // Collide -> stop
            }
        }

        this.xCoord = newX;
        this.yCoord = newY;
    }

    public double getXCoord() {
        return this.xCoord;
    }

    public double getYCoord() {
        return this.yCoord;
    }

    public int getSize() {
        return this.size;
    }

    public Vector getViewAngle() {
        return rays[RAY_COUNT / 2]; // center ray
    }

    public Vector[] getRays() {
        return this.rays;
    }

    public void updateRay(int index, Vector v) {
        this.rays[index] = v;
    }

    public void rotate(double angleDelta) {
        this.viewAngleOffset += angleDelta;

        for (int i = 0; i < rays.length; ++i) {
            final double angle = this.viewAngleOffset - FOV / 2.0 + (FOV * i / (RAY_COUNT - 1));
            this.rays[i].x = Math.cos(angle);
            this.rays[i].y = Math.sin(angle);
        }
    }
}
