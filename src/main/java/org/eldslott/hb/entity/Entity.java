package org.eldslott.hb.entity;

import org.eldslott.hb.concept.*;
import org.lwjgl.input.Keyboard;

/**
 * @author <a href="mailto:oscar.eriks@gmail.com">Oscar Eriksson</a>
 * @date 12/18/13
 */
public abstract class Entity implements Moveable {
    public Position position = new Position();
    public Velocity velocity = new Velocity();
    public Movement movement = new Movement();
    public Rotation rotation = new Rotation();

    public Position position() {
        return position;
    }

    public Velocity velocity() {
        return velocity;
    }

    @Override
    public void keyDown(int key) {
        if (key == Keyboard.KEY_LEFT) {
            movement.increase(rotation, Direction.LEFT);
        }
        else if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
            movement.increase(rotation, Direction.RIGHT);
        }
        else if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
            movement.increase(rotation, Direction.BACK);
        }
        else if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
            movement.increase(rotation, Direction.FORWARD);
        }
    }
}
