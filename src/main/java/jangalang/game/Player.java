package jangalang.game;

import jangalang.engine.maps.Map;
import jangalang.engine.maps.Wall;
import jangalang.util.GameProperties;
import jangalang.util.types.Vector;

public class Player {
    private double xCoord;
    private double yCoord;
    private int size;

    public static double RAY_MAX_LENGTH = 250.0;
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

    public void move(Map gameMap, double xInc, double yInc) {
        double newX = this.xCoord + xInc;
        double newY = this.yCoord + yInc;

        double radius = this.size / 2.0;

        // Check collisions
        for (Wall wall : gameMap.getWalls()) {
            if (wall.playerIntersect(newX, newY, radius)) {
                return; // Block move
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

    public void rotate(double angleDelta) {
        this.viewAngleOffset += angleDelta;

        for (int i = 0; i < rays.length; ++i) {
            final double angle = this.viewAngleOffset - FOV / 2.0 + (FOV * i / (RAY_COUNT - 1));
            this.rays[i].x = Math.cos(angle);
            this.rays[i].y = Math.sin(angle);
        }
    }
}
