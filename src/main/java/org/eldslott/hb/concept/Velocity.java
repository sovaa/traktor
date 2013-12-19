package org.eldslott.hb.concept;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:oscar.eriks@gmail.com">Oscar Eriksson</a>
 * @date 12/18/13
 */
public class Velocity implements Infinite<Map<Direction, Integer>> {
    public Velocity higher;
    public Velocity lower;
    public int magnitude = 0;

    private Map<Direction, Integer> velocity = new HashMap<>();

    public Velocity() {
        velocity.put(Direction.X, 0);
        velocity.put(Direction.Y, 0);
        velocity.put(Direction.Z, 0);
    }

    public Velocity(int dx, int dy, int dz) {
        velocity.put(Direction.X, dx);
        velocity.put(Direction.Y, dy);
        velocity.put(Direction.Z, dz);
    }

    public Velocity(int dx, int dy, int dz, int magnitude) {
        velocity.put(Direction.X, dx);
        velocity.put(Direction.Y, dy);
        velocity.put(Direction.Z, dz);
        this.magnitude = magnitude;
    }

    public Velocity(int dx, int dy, int dz, int magnitude, Velocity lower, Velocity higher) {
        velocity.put(Direction.X, dx);
        velocity.put(Direction.Y, dy);
        velocity.put(Direction.Z, dz);
        this.magnitude = magnitude;
        this.lower = lower;
        this.higher = higher;
    }

    /**
     * TODO: when using front thrusters to decrease forward speed, reduce different directional speeds by looking at the current Rotation
     */
    public void increase(Direction d) {
        Velocity v = this;

        while (v.lower != null) {
            v = v.lower;
        }

        v.velocity.put(d, v.velocity.get(d) + 1); // TODO: propagate to highers
    }

    // TODO: will probably never be used
    public void decrease(Direction d) {
        Velocity v = this;

        while (v.lower != null) {
            v = v.lower;
        }

        v.velocity.put(d, v.velocity.get(d) - 1); // TODO: propagate to highers
    }

    public Integer get(Direction d) {
        return velocity.get(d);
    }

    @Override
    public Infinite higher() {
        return null;
    }

    @Override
    public Infinite lower() {
        return null;
    }

    @Override
    public Map<Direction, Integer> value() {
        return velocity;
    }

    @Override
    public int magnitude() {
        return 0;
    }

    @Override
    public String toString() {
        return toString(Velocity.class);
    }
}
