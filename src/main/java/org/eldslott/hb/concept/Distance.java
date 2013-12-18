package org.eldslott.hb.concept;

/**
 * @author <a href="mailto:oscar.eriks@gmail.com">Oscar Eriksson</a>
 * @date 12/19/13
 */
public class Distance implements Infinite<Integer> {
    private Distance higher;
    private Distance lower;

    private int distance = 0;
    private int magnitude = 0;

    public Distance() {
    }

    public Distance(int distance, int magnitude) {
        this.distance = distance;
        this.magnitude = magnitude;
    }

    public Distance(Position a, Position b) {
        build(a, b);
    }

    public static Distance calculate(final Position a, final Position b) {
        Position aa = a;
        Position bb = b;

        while (aa.higher != null) {
            aa = aa.higher;
        }

        while (bb.higher != null) {
            bb = bb.higher;
        }

        while (aa.magnitude < bb.magnitude) {
            aa.higher = new Position(0, 0, 0, aa.magnitude+1);
            aa.higher.lower = aa;
            aa = aa.higher;
        }

        while (bb.magnitude < aa.magnitude) {
            bb.higher = new Position(0, 0, 0, bb.magnitude+1);
            bb.higher.lower = aa;
            bb = bb.higher;
        }

        return new Distance(aa, bb);
    }

    private void build(Position aa, Position bb) {
        magnitude = aa.magnitude;

        // distance between two points in euclidean n-space is sqrt(sum((q_i-p_i)**2))
        distance = (int)Math.sqrt(
                Math.pow(bb.get(Direction.X) - aa.get(Direction.X), 2) +
                Math.pow(bb.get(Direction.Y) - aa.get(Direction.Y), 2) +
                Math.pow(bb.get(Direction.Z) - aa.get(Direction.Z), 2));

        if (aa.lower != null) {
            lower = new Distance();
            lower.higher = this;
            lower.build(aa.lower, bb.lower);
        }
    }

    @Override
    public Distance lower() {
        return lower;
    }

    @Override
    public Distance higher() {
        return higher;
    }

    @Override
    public Integer value() {
        return distance;
    }

    @Override
    public int magnitude() {
        return magnitude;
    }

    @Override
    public String toString() {
        return toString(Distance.class);
    }
}
