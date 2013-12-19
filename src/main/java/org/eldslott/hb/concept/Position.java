package org.eldslott.hb.concept;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:oscar.eriks@gmail.com">Oscar Eriksson</a>
 * @date 12/18/13
 */
public class Position implements Infinite<Map<Direction, Integer>> {
    public Position higher = null;
    public Position lower = null;
    public int magnitude = 0;

    private Map<Direction, Integer> position = new HashMap<>();

    public Position() {
        position.put(Direction.X, 100);
        position.put(Direction.Y, 100);
        position.put(Direction.Z, 100);
    }

    public Position(int x, int y, int z) {
        position.put(Direction.X, x);
        position.put(Direction.Y, y);
        position.put(Direction.Z, z);
    }

    public Position(int x, int y, int z, int magnitude) {
        position.put(Direction.X, x);
        position.put(Direction.Y, y);
        position.put(Direction.Z, z);
        this.magnitude = magnitude;
    }

    public void move(Velocity velocity) {
        Velocity v = velocity;
        Position p = this;

        while (v.higher != null) {
            v = v.higher;
        }

        while (p.magnitude < v.magnitude && p.higher != null) {
            p = p.higher;
        }

        while (p.magnitude < v.magnitude) {
            Position np = new Position(0, 0, 0);
            np.magnitude = p.magnitude + 1;
            np.lower = p;
            p.higher = np;
            p = np;
        }

        do {
            p.add(Direction.X, v);
            p.add(Direction.Y, v);
            p.add(Direction.Z, v);
            v = v.lower;
            p = p.lower;
        } while (v != null);
    }

    private void add(Direction dir, Velocity v) {
        int pos = get(dir);
        int dpos = v.get(dir);

        if ((pos > 0) && (dpos > (Integer.MAX_VALUE - pos))) {
            linkHigher();
            higher.add(dir, 1);
            set(dir, Integer.MIN_VALUE + (dpos - (Integer.MAX_VALUE - pos)));
        }
        else if ((pos < 0) && (dpos < (Integer.MIN_VALUE - pos))) {
            linkHigher();
            higher.add(dir, -1);
            set(dir, Integer.MAX_VALUE - ((Integer.MIN_VALUE - pos) - dpos));
        }
        // TODO: link lower....
        else {
            set(dir, pos + dpos);
        }
    }

    private void linkHigher() {
        if (higher != null) {
            return;
        }

        higher = new Position(0, 0, 0, magnitude+1);
        higher.lower = this;
    }

    public Distance distance(Position b) {
        return Distance.calculate(this, b);
    }

    public Integer get(Direction d) {
        return position.get(d);
    }

    private void add(Direction dir, int amount) {
        set(dir, get(dir) + amount);
    }

    private void set(Direction d, Integer i) {
        position.put(d, i);
    }

    @Override
    public Infinite lower() {
        return lower;
    }

    @Override
    public Infinite higher() {
        return higher;
    }

    @Override
    public int magnitude() {
        return magnitude;
    }

    @Override
    public Map<Direction, Integer> value() {
        return position;
    }

    @Override
    public String toString() {
        return toString(Position.class);
    }
}
