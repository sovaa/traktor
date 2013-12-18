package org.eldslott.hb.entity;

import org.eldslott.hb.concept.Position;

/**
 * @author <a href="mailto:oscar.eriks@gmail.com">Oscar Eriksson</a>
 * @date 12/18/13
 */
public class Ship extends Entity {
    private Position destination;

    @Override
    public void move() {
        position().move(velocity());
    }
}
