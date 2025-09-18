package jangalang.game;

public class Player {
    private double xCoord;
    private double yCoord;
    private int size;

    public Player (double xCoord, double yCoord, int size) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.size = size;
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
}
