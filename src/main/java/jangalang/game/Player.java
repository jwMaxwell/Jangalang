package jangalang.game;

import jangalang.util.GameProperties;
import jangalang.util.types.Vector;

public class Player {
    private double xCoord;
    private double yCoord;
    private int size;

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

    public void move(double xInc, double yInc) {
        this.xCoord += xInc;
        this.yCoord += yInc;
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
            final double angle = this.viewAngleOffset - FOV / 2 + (FOV * i / (RAY_COUNT - 1));
            this.rays[i].x = Math.cos(angle);
            this.rays[i].y = Math.sin(angle);
        }
    }
}
