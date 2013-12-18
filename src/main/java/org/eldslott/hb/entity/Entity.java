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
package org.eldslott.hb.entity;

import org.eldslott.hb.concept.Moveable;
import org.eldslott.hb.concept.Position;
import org.eldslott.hb.concept.Velocity;

/**
 * @author <a href="mailto:oscar.eriksson@sigma.se">Oscar Eriksson</a>
 * @date 12/18/13
 */
public abstract class Entity implements Moveable {
    private Position position;
    private Velocity velocity;

    public Position position() {
        return position;
    }

    public Velocity velocity() {
        return velocity;
    }
}
