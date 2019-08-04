package com.langtonsant.application.element.cell;

import com.langtonsant.math.Vector2;

public class WhiteCell extends Cell {

    /**
     * {@inheritDoc}
     * Apply a 90 degrees clockwise rotation
     *
     * @param vectorToRotate the base vector to compute the rotation
     * @return a new vector rotated 90 degrees clockwise
     */
    @Override
    public Vector2 rotateVector(Vector2 vectorToRotate) {
        return new Vector2(vectorToRotate.y, -vectorToRotate.x);
    }

    @Override
    public CellType getCellType() {
        return CellType.White;
    }
}
