package org.eldslott.hb.concept;

import java.text.DecimalFormat;

/**
 * @author <a href="mailto:oscar.eriks@gmail.com">Oscar Eriksson</a>
 * @date 12/19/13
 */
public class Distance implements Infinite<Integer> {
    private Distance higher;
    private Distance lower;
    private static char[] alphas = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    private int distance = 0;
    private int magnitude = 0;

    public static class DistanceUnit {
        public double constant;
        public String unit;
        public int magnitude;

        public DistanceUnit(Distance d) {
            while (d.higher != null) {
                d = d.higher;
            }
            if (d.magnitude == 0) {
                if (d.distance < 1000) {
                    this.unit = "m";
                    this.constant = d.distance;
                }
                else {
                    this.unit = "km";
                    this.constant = d.distance / 1000;
                }
            }
            else if (d.magnitude == 1) {
                this.unit = "AU";
                this.constant = 0.02871 * d.distance;
            }
            else if (d.magnitude == 2) {
                double constant = 0.5978 * d.distance;

                if (constant < 1000) {
                    this.unit = "k ps";
                    this.constant = constant;
                }
                else if (constant < 1000000) {
                    this.unit = "M ps";
                    this.constant = constant / 1000;
                }
                else {
                    this.unit = "T ps";
                    this.constant = constant / 1000000;
                }
            }
            else if (d.magnitude == 3) {
                double constant = 0.002568 * d.distance;

                if (constant < 1000) {
                    this.unit = "P ps";
                    this.constant = constant;
                }
                else if (constant < 1000000) {
                    this.unit = "E ps";
                    this.constant = constant / 1000;
                }
                else {
                    this.unit = "Z ps";
                    this.constant = constant / 1000000;
                }
            }
            else if (d.magnitude == 4) {
                this.unit = "Y ps";
                this.constant = 0.01103 * d.distance;
            }
            else if (d.magnitude == 5) {
                this.unit = "qf";
                this.constant = 0.0000001 * d.distance;
            }
            else {
                this.unit = alphas[(d.magnitude-6) % alphas.length] + " qf";
                this.constant = 0.0000001 * d.distance;
            }

            this.magnitude = d.magnitude;
        }
    }

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

        buildLower(bb);
        buildLower(aa);

        return new Distance(aa, bb);
    }

    private static void buildLower(Position p) {
        while (p.magnitude > 0) {
            if (p.lower != null) {
                p = p.lower;
                continue;
            }

            p.lower = new Position(0, 0, 0, p.magnitude-1);
            p.lower.higher = p;
            p = p.lower;
        }
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

    public String toPrettyString() {
        StringBuilder builder = new StringBuilder();
        DistanceUnit unit = new DistanceUnit(this);

        if (unit.constant < 100) {
            DecimalFormat df = new DecimalFormat("#.#####");
            builder.append(df.format(unit.constant));
        }
        else if (unit.constant < 1000) {
            DecimalFormat df = new DecimalFormat("#.###");
            builder.append(df.format(unit.constant));
        }
        else if (unit.constant < 10000) {
            DecimalFormat df = new DecimalFormat("#.#");
            builder.append(df.format(unit.constant));
        }
        else {
            builder.append(Long.toString((long)Math.floor(unit.constant)));
        }

        return String.format("%s %s", builder.toString(), unit.unit);
    }
}
