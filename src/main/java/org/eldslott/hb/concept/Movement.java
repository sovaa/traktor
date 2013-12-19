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

/**
 * @author <a href="mailto:oscar.eriksson@sigma.se">Oscar Eriksson</a>
 * @date 12/19/13
 */
public class Movement {
    public int x = 0;
    public int y = 0;
    public int z = 0;

    public void increase(Rotation r, Direction d) {
        switch (d) {
            case FORWARD:
                x = 1;
                break;
            case BACK:
                x = -1;
                break;
            case LEFT:
                y = 1;
                break;
            case RIGHT:
                y = -1;
                break;
            case UP:
                z = 1;
                break;
            case DOWN:
                z = -1;
                break;
        }
    }

    public void stop(Rotation r, Direction d) {
        switch (d) {
            case FORWARD:
                x = 0;
                break;
            case BACK:
                x = 0;
                break;
            case LEFT:
                y = 0;
                break;
            case RIGHT:
                y = 0;
                break;
            case UP:
                z = 0;
                break;
            case DOWN:
                z = 0;
                break;
        }
    }
}
