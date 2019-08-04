package com.langtonsant.math;

/**
 * A simple 2D Vector
 */
public class Vector2 {
    public int x;
    public int y;

    /**
     * Creates a Vector2(0,0)
     */
    public Vector2() {
        this(0, 0);
    }

    /**
     * Create a Vector2(x,y)
     *
     * @param x x component of the vector
     * @param y y component of the vector
     */
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Output a Vector2 "pointing" to the direction "right"
     *
     * @return Vector2(1, 0);
     */
    public static Vector2 right() {
        return new Vector2(1, 0);
    }

    /**
     * Sum this vector with another vector.
     * The resulting components are stored into this vector
     *
     * @param vectorToAdd the other vector to add
     */
    public void add(Vector2 vectorToAdd) {
        this.x += vectorToAdd.x;
        this.y += vectorToAdd.y;
    }
}
