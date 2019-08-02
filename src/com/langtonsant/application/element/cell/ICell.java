package com.langtonsant.application.element.cell;

import com.langtonsant.math.Vector2;

public interface ICell {

    void setNextCell(ICell cell);
    ICell next();
    Vector2 rotateVector(Vector2 vectorToRotate);
    CellType getCellType();
}
