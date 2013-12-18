package org.eldslott.hb.entity;

import org.eldslott.hb.concept.Moveable;
import org.eldslott.hb.concept.Position;
import org.eldslott.hb.concept.Velocity;

/**
 * @author <a href="mailto:oscar.eriks@gmail.com">Oscar Eriksson</a>
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
