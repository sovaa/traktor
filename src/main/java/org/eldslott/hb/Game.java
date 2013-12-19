package org.eldslott.hb;

import org.eldslott.hb.concept.Moveable;
import org.eldslott.hb.entity.Ship;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * @author <a href="mailto:oscar.eriks@gmail.com">Oscar Eriksson</a>
 * @date 12/11/13
 */
public class Game implements Runnable {
    private static boolean running = true;
    private Moveable agent;

    @Override
    public void run() {
        try {
            Display.setDisplayMode(new DisplayMode(1280,720));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        agent = new Ship();

        // init OpenGL here

        while (!Display.isCloseRequested() && Game.isRunning()) {

            pollInput();
            agent.tick();
            agent.move();

            Display.setTitle(((Ship) agent).position.toString() + " " + ((Ship) agent).velocity.toString());

            // render OpenGL here
            Display.update();

            try {
                Thread.sleep(50);
            }
            catch (InterruptedException e) {
                // TODO
            }
        }

        Display.destroy();
    }

    public void pollInput() {
        if (Mouse.isButtonDown(0)) {
            int x = Mouse.getX();
            int y = Mouse.getY();

            System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            System.out.println("SPACE KEY IS DOWN");
        }

        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                agent.keyDown(Keyboard.getEventKey());
            } else {
                agent.keyUp(Keyboard.getEventKey());
            }
        }
    }


    public static void setRunning(boolean running) {
        Game.running = running;
    }

    public static boolean isRunning() {
        return Game.running;
    }
}
