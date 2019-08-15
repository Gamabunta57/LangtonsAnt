package com.langtonsant.math;

/**
 * Simple class to handle a rectangle defined by a position (x,y) and a size (width, height).
 */
public class Rectangle {

    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * @param x      the x coordinates of the rectangle
     * @param y      the y coordinates of the rectangle
     * @param width  the width of the rectangle (must be a positive integer)
     * @param height the height of the rectangle (must be a positive integer)
     */
    public Rectangle(int x, int y, int width, int height) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
    }

    /**
     * Get the x coordinate of the rectangle
     */
    public int getX() {
        return x;
    }

    /**
     * Set the x coordinates of the rectangle
     *
     * @param x the x coordinates of the rectangle
     */
    private void setX(int x) {
        this.x = x;
    }

    /**
     * Get the y coordinate of the rectangle
     */
    public int getY() {
        return y;
    }

    /**
     * Set the y coordinates of the rectangle
     *
     * @param y the y coordinates of the rectangle
     */
    private void setY(int y) {
        this.y = y;
    }

    /**
     * Get the width of the rectangle
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set the width of the rectangle
     *
     * @param width the width of the rectangle (must be a positive integer)
     */
    private void setWidth(int width) {
        if (width < 0) throw new IllegalArgumentException(String.format("Width must be positive %s given", width));

        this.width = width;
    }

    /**
     * Get the height of the rectangle
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the height of the rectangle
     *
     * @param height the height of the rectangle (must be a positive integer)
     */
    private void setHeight(int height) {
        if (height < 0) throw new IllegalArgumentException(String.format("Height must be positive %s given", height));

        this.height = height;
    }

}
