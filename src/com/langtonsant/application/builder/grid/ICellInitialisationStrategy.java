package com.langtonsant.application.builder.grid;

import com.langtonsant.application.element.cell.ICell;
import com.langtonsant.math.Vector2;

public interface ICellInitialisationStrategy {
    int getWidth();
    int getHeight();
    Vector2 getLastOffset();

    void reset();
    ICell[] getNewGrid();
    ICell[] doubleGridSize(ICell[] baseGrid);
}
