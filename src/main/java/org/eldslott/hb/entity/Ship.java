package org.eldslott.hb.entity;

import org.eldslott.hb.concept.Direction;
import org.eldslott.hb.concept.Movement;
import org.eldslott.hb.concept.Position;
import org.eldslott.hb.ship.EngineSystem;
import org.eldslott.hb.ship.HullSystem;
import org.eldslott.hb.ship.ShieldSystem;
import org.eldslott.hb.ship.WeaponSystem;
import org.lwjgl.input.Keyboard;

/**
 * @author <a href="mailto:oscar.eriks@gmail.com">Oscar Eriksson</a>
 * @date 12/18/13
 */
public class Ship extends Entity {
    private Position destination;

    private EngineSystem engine;
    private HullSystem hull;
    private ShieldSystem shield;
    private WeaponSystem weapon;

    public Ship() {
        movement = new Movement();
    }

    /**
     * TODO: Decreasing speed will never happen (while in a spacecraft), you will just increase
     * the speed in the different direction... need to implement directional thrusters first.
     */
    public void tick() {
        if (movement.x > 0) {
            velocity.increase(Direction.X);
        }
        else if (movement.x < 0) {
            velocity.decrease(Direction.X);
        }

        if (movement.y > 0) {
            velocity.increase(Direction.Y);
        }
        else if (movement.y < 0) {
            velocity.decrease(Direction.Y);
        }

        if (movement.z > 0) {
            velocity.increase(Direction.Z);
        }
        else if (movement.z < 0) {
            velocity.decrease(Direction.Z);
        }
    }

    @Override
    public void move() {
        // TODO: the velocity now specifies meters/tick
        position().move(velocity());
    }

    @Override
    public void keyUp(int key) {
        if (key == Keyboard.KEY_LEFT) {
            movement.stop(rotation, Direction.LEFT);
        }
        else if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
            movement.stop(rotation, Direction.RIGHT);
        }
        else if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
            movement.stop(rotation, Direction.BACK);
        }
        else if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
            movement.stop(rotation, Direction.FORWARD);
        }
    }
}
