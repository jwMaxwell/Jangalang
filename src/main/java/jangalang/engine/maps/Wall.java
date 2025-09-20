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

    public boolean playerIntersect(double cx, double cy, double radius) {
        double x1 = start.getKey();
        double y1 = start.getValue();
        double x2 = end.getKey();
        double y2 = end.getValue();

        // Vector math: distance from circle center to line segment
        double dx = x2 - x1;
        double dy = y2 - y1;
        double t = ((cx - x1) * dx + (cy - y1) * dy) / (dx*dx + dy*dy);

        t = Math.max(0, Math.min(1, t)); // clamp to segment
        double closestX = x1 + t * dx;
        double closestY = y1 + t * dy;

        double distSq = (cx - closestX)*(cx - closestX) + (cy - closestY)*(cy - closestY);
        return distSq <= radius*radius;
    }

    public Double rayIntersect(double rx, double ry, double rdx, double rdy) {
        double x1 = start.getKey();
        double y1 = start.getValue();
        double x2 = end.getKey();
        double y2 = end.getValue();

        double sx = x2 - x1;
        double sy = y2 - y1;

        double denom = rdx * sy - rdy * sx; // r.x * s.y - r.y * s.x
        if (Math.abs(denom) < 1e-9)
            return null; // parallel or nearly so

        double qx = rx - x1;
        double qy = ry - y1;

        double t = (rdx * qy - rdy * qx) / denom; // segment param (0..1)
        double u = (sx * qy - sy * qx) / denom; // ray param (>=0)

        if (t >= 0.0 && t <= 1.0 && u >= 0.0) {
            return u;
        }
        return null;
    }

    public boolean equals(Wall w) {
        return this.start.equals(w.start) && this.end.equals(w.end);
    }
}
