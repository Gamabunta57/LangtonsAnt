package com.langtonsant.application.element.grid;

import com.langtonsant.application.builder.grid.ICellInitialisationStrategy;
import com.langtonsant.application.element.cell.ICell;
import com.langtonsant.math.Vector2;

/**
 * Contract defining a standard grid
 */
public interface IGrid {

    /**
     * @return the current width of the grid
     */
    int getWidth();

    /**
     * @return the current height of the grid
     */
    int getHeight();

    /**
     * Return the cell from the grid at the given vector2 coordinates
     *
     * @param position the coordinates ot the desired cell
     * @return the cell at the given position
     */
    ICell getCellAt(Vector2 position);

    /**
     * Return the cell from the grid at the given x and y coordinates
     *
     * @param x the x coordinate in the grid
     * @param y the y coordinate in the grid
     * @return the cell at the given position
     */
    ICell getCellAt(int x, int y);

    /**
     * Set the cell at the coordinates in the grid
     *
     * @param position the target coordinates
     * @param cell the cell to set
     */
    void setCellAt(Vector2 position, ICell cell);

    /**
     * Defines the strategy to initialize the grid with
     *
     * @param strategy the chosen strategy to set the grid
     */
    void setInitialisationStrategy(ICellInitialisationStrategy strategy);

    /**
     * Compute a new direction based on a given position in the grid and a given direction
     * @param positionInGrid the given position from which to compute the new heading
     * @param headingVector the base heading vector from which to compute the new heading
     * @return the computed new direction
     */
    Vector2 computeNewHeadingFrom(Vector2 positionInGrid, Vector2 headingVector);

    /**
     * Transform the cell at a given position with the next cell
     * @param positionInGrid
     */
    void cycleCellAt(Vector2 positionInGrid);

    /**
     * Check if the given coordinates is inside the grid
     *
     * @param positionInGrid the given coordinates
     * @return true if the coordinates are inside the grid, false otherwise
     */
    boolean isValidPosition(Vector2 positionInGrid);

    /**
     * Reset the grid by reinitialising each cell
     */
    void reset();

    /**
     * Size the grid up.
     * Should be called if an invalid coordinates is found.
     * The process place the old grid in the center of the new grid. So the old grid coordinates are "shifted"
     *
     * @return the shifting amount of the old coordinates.
     */
    Vector2 sizeGridUp();

}
