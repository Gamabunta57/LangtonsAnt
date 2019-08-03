package com.langtonsant.application.builder.grid;

import com.langtonsant.application.element.cell.ICell;
import com.langtonsant.math.Vector2;

/**
 * Contract defining the strategies for cell grid initialisation
 */
public interface ICellInitialisationStrategy {

    /**
     * @return the width of the grid to generate
     */
    int getWidth();

    /**
     * @return the height of the grid to generate
     */
    int getHeight();

    /**
     * When grid is resize, a "shift" of the old grid occurs. This give the latest "shifting" amount that occurs
     * @return the last "shift" of the coordinates that occurs.
     */
    Vector2 getLastOffset();

    /**
     * Reset the width and height with the very first width and height initialisation.
     */
    void reset();

    /**
     * Generate a new set of cell.
     * @return the new generated cells
     */
    ICell[] getNewGrid();

    /**
     * Double the size of the grid and replace the baseGrid in the middle of the new grid.
     *
     * @param baseGrid the old grid to replace into the new one
     * @return the double sized new grid.
     */
    ICell[] doubleGridSize(ICell[] baseGrid);
}
