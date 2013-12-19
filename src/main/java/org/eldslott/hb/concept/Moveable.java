package org.eldslott.hb.concept;

/**
 * @author <a href="mailto:oscar.eriks@gmail.com">Oscar Eriksson</a>
 * @date 12/18/13
 */
public interface Moveable {
    public void tick();
    public void move();
    public void keyDown(int key);
    public void keyUp(int key);
}
