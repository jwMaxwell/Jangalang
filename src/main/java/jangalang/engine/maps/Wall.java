package jangalang.engine.maps;

import jangalang.util.types.Pair;

public class Wall {
    public Pair<Double, Double> start;
    public Pair<Double, Double> end;

    public Wall(Pair<Double, Double> start, Pair<Double, Double> end) {
        this.start = start;
        this.end = end;
    }

    public Wall(Double x1, Double y1, Double x2, Double y2) {
        this.start = new Pair<Double, Double>(x1, y1);
        this.end = new Pair<Double, Double>(x2, y2);
    }

    public Wall(Wall w) {
        this.start = w.start;
        this.end = w.end;
    }

    public boolean equals(Wall w) {
        return this.start.equals(w.start) && this.end.equals(w.end);
    }
}
