package org.eldslott.hb.entity;

import junit.framework.TestCase;
import org.eldslott.hb.concept.Position;
import org.eldslott.hb.concept.Velocity;
import org.junit.Test;

/**
 * @author <a href="mailto:oscar.eriks@gmail.com">Oscar Eriksson</a>
 * @date 12/18/13
 */
public class PositionTest extends TestCase {
    @Test
    public void testMove() {
        Position pos  = new Position(-12, Integer.MIN_VALUE, 0, 0);

        Velocity vel  = new Velocity(90,  Integer.MIN_VALUE,      2, 0);
        Velocity vel2 = new Velocity(1,   Integer.MIN_VALUE,      0, 1);
        Velocity vel3 = new Velocity(0,   Integer.MIN_VALUE,      0, 2);
        Velocity vel4 = new Velocity(0,   Integer.MIN_VALUE,      0, 3);

        vel4.lower = vel3;
        vel3.higher = vel4;
        vel3.lower = vel2;
        vel2.higher = vel3;
        vel2.lower = vel;
        vel.higher = vel2;

        pos.move(vel);
        pos.move(vel);
        pos.move(vel);
        pos.move(vel);
        pos.move(vel);
        pos.move(vel);
        pos.move(vel);
        pos.move(vel);

        pos.move(vel);
    }

    @Test
    public void testDistance() {
        Position ship    = new Position(-84, -48989, 2450, 0);
        Position station;

        for (int j = 0; j < 3; j++) {
            for (int i = 1; i < 500; i++) {
                station = new Position((Integer.MAX_VALUE/100)*i, 75, 99, j);
                ship.distance(station);
            }
        }
    }
}
