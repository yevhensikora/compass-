package com.epam.rd.autotasks;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315);

    Direction(final int degrees) {
        this.degrees = degrees;
    }

    private int degrees;

    public int getDegrees() {
        return degrees;
    }

    private static final Map<Integer, Direction> lookup = new HashMap<Integer, Direction>();

    static {
        for (Direction d : Direction.values()) {
            lookup.put(d.getDegrees(), d);
        }
    }

    private static int degreesToRange(int degrees) {
        int _degrees = degrees;
        while (_degrees < 0) _degrees += 360;
        return _degrees % 360;
    }

    public static Direction ofDegrees(int degrees) {
        return lookup.getOrDefault(degreesToRange(degrees), null);
    }

    public static Direction closestToDegrees(int degrees) {
        Direction d = ofDegrees(degrees);
        if (d == null) {
            int _degrees = degreesToRange(degrees);
            Direction ret = null;
            int distance = 361;
            for (Integer i : lookup.keySet()) {
                if (distance > Math.abs(i - _degrees)) {
                    distance = Math.abs(i - _degrees);
                    ret = ofDegrees(i);
                }
            }
            return ret;
        } else
            return d;
    }

    public Direction opposite() {
        return ofDegrees(degreesToRange(degrees + 180));
    }

    public int differenceDegreesTo(Direction direction) {
        int q = Math.abs(degrees - direction.getDegrees());
        if (q > 180)
            return 360 - q;
        return q;
    }
}
