package com.langtonsant.application.element.grid;

import com.langtonsant.application.builder.grid.ICellInitialisationStrategy;
import com.langtonsant.application.element.cell.ICell;
import com.langtonsant.math.Vector2;

public interface IGrid {

    int getWidth();
    int getHeight();

    void setCellAt(Vector2 position, ICell cell);
    ICell getCellAt(Vector2 position);
    ICell getCellAt(int x, int y);

    void setInitialisationStrategy(ICellInitialisationStrategy strategy);

    Vector2 computeNewHeadingFrom(Vector2 positionInGrid, Vector2 headingVector);
    void cycleCellAt(Vector2 positionInGrid);
    boolean isValidPosition(Vector2 positionInGrid);
    void reset();
    Vector2 sizeGridUp();

}
