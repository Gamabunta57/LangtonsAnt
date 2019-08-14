package com.langtonsant.application.element.grid;

import com.langtonsant.math.Rectangle;
import com.langtonsant.math.Vector2;

/**
 * Contract defining a standard grid
 */
public interface IGrid extends Iterable<Vector2> {

    /**
     * @return the current width of the grid
     */
    int getWidth();

    /**
     * @return the current height of the grid
     */
    int getHeight();

    /**
     * Compute a new direction based on a given position in the grid and a given direction
     *
     * @param positionInGrid the given position from which to compute the new heading
     * @param headingVector  the base heading vector from which to compute the new heading
     * @return the computed new direction
     */
    Vector2 computeNewHeadingFrom(Vector2 positionInGrid, Vector2 headingVector);

    /**
     * Transform the cell at a given position with the next cell
     *
     * @param positionInGrid the coordinates in the grid
     */
    void cycleCellAt(Vector2 positionInGrid);

    /**
     * Reset the grid by reinitialising each cell
     */
    void reset();

    /**
     * Returns true if there's a cell at the given position.
     *
     * @param position the coordinates to check in the grid
     * @return true if a black cell can be found at the given position, false otherwise.
     */
    boolean hasCellAt(Vector2 position);

    /**
     * Returns a rectangle matching the smallest area where all the black cells are in
     *
     * @return a rectangle matching the smallest area where all the black cells are in
     */
    Rectangle GetArea();
}
