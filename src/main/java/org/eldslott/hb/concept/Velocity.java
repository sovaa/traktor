/*
 * This software is the confidential and proprietary information of
 * Sigma Systems Innovation. ("Confidential Information"). You shall
 * not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sigma Systems Innovation.
 *
 * COPYRIGHT (C) 2014 SIGMA SYSTEMS INNOVATION AB.
 * All rights reserved.
 */
package org.eldslott.hb.concept;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:oscar.eriksson@sigma.se">Oscar Eriksson</a>
 * @date 12/18/13
 */
public class Velocity implements Infinite<Map<Direction, Integer>> {
    public Velocity higher;
    public Velocity lower;
    public int magnitude = 0;

    private Map<Direction, Integer> velocity = new HashMap<>();

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
        return null;
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
