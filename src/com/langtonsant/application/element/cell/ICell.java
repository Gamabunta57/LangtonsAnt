package com.langtonsant.application.element.cell;

import com.langtonsant.math.Vector2;

/**
 * Contract defining the cell
 */
public interface ICell {

    /**
     * Defines what should be the next cell in the chain of cell
     * @param cell
     */
    void setNextCell(ICell cell);

    /**
     * @return the next chained cell
     */
    ICell next();

    /**
     * Rotates a given vector
     *
     * @param vectorToRotate the base vector to compute the rotation
     * @return the rotated vector
     */
    Vector2 rotateVector(Vector2 vectorToRotate);

    /**
     * @return the cell type
     */
    CellType getCellType();
}
