package com.langtonsant.application.element.cell;

import com.langtonsant.math.Vector2;

public class BlackCell extends Cell {

    @Override
    public Vector2 rotateVector(Vector2 vectorToRotate) {
        return new Vector2(-vectorToRotate.y, vectorToRotate.x);
    }

    @Override
    public CellType getCellType() {
        return CellType.Black;
    }
}
