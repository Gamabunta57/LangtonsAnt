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
     * Copy constructor
     *
     * @param vector2 vector2 to copy from
     */
    public Vector2(Vector2 vector2){
        this.x = vector2.x;
        this.y = vector2.y;
    }

    /**
     * Output a Vector2 which components are 0,0
     *
     * @return Vector2(0, 0);
     */
    public static Vector2 zero() {
        return new Vector2(0,0);
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
        x += vectorToAdd.x;
        y += vectorToAdd.y;
    }

    public static Vector2 maxAvailable(){
        return new Vector2(Integer.MAX_VALUE,Integer.MAX_VALUE);
    }

    public static Vector2 minAvailable(){
        return new Vector2(Integer.MIN_VALUE,Integer.MIN_VALUE);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Vector2))
            return false;

        Vector2 b = (Vector2)other;
        return x == b.x && y == b.y;
    }

    @Override
    public int hashCode() {
        return (Integer.toString(x) + ":" + Integer.toString(y)).hashCode();
    }
}
