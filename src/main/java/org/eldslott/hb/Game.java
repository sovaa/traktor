package org.eldslott.hb;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * @author <a href="mailto:oscar.eriks@gmail.com">Oscar Eriksson</a>
 * @date 12/11/13
 */
public class Game implements Runnable {
    private static boolean running = true;

    @Override
    public void run() {
        try {
            Display.setDisplayMode(new DisplayMode(1280,720));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // init OpenGL here

        while (!Display.isCloseRequested() && Game.isRunning()) {
            // render OpenGL here
            Display.update();

            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
                // TODO
            }
        }

        Display.destroy();
    }

    public static void setRunning(boolean running) {
        Game.running = running;
    }

    public static boolean isRunning() {
        return Game.running;
    }
}
