package com.langtonsant.application.element.ant;

import com.langtonsant.math.Vector2;

/**
 * Contract defining a machine
 */
public interface IMachine {

    /**
     * @return the current position of the machine
     */
    Vector2 getPosition();

    /**
     * Set the new position of the machine
     *
     * @param position the new position to set
     */
    void setPosition(Vector2 position);

    /**
     * Get the direction the machine is pointing at
     *
     * @return the direction the machine is pointing at
     */
    Vector2 getHeading();

    /**
     * Set the direction the machine is pointing at
     *
     * @param heading the new direction to point at
     */
    void setHeading(Vector2 heading);

    /**
     * Moves the machine
     */
    void move();

    /**
     * Reset the position of the machine to Vector(0,0) and the heading to the right Vector(1,0)
     */
    void reset();
}
