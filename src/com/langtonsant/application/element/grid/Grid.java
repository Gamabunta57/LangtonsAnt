package com.langtonsant.application.element.grid;

import com.langtonsant.application.builder.grid.ICellInitialisationStrategy;
import com.langtonsant.application.element.cell.ICell;
import com.langtonsant.math.Vector2;

public class Grid implements IGrid {

    private int width;
    private int height;
    private ICell[] cells;
    private ICellInitialisationStrategy initialisationStrategy;

    @Override
    public Vector2 computeNewHeadingFrom(Vector2 positionInGrid, Vector2 headingVector) {
        ICell cell = getCellAt(positionInGrid);
        return cell.rotateVector(headingVector);
    }

    @Override
    public void cycleCellAt(Vector2 positionInGrid) {
        ICell cell = getCellAt(positionInGrid);
        setCellAt(positionInGrid, cell.next());
    }

    @Override
    public boolean isValidPosition(Vector2 positionInGrid) {
        return positionInGrid.x > -1
            && positionInGrid.y > -1
            && positionInGrid.x < width
            && positionInGrid.y < height;
    }

    @Override
    public void reset() {
        initialisationStrategy.reset();
        cells = initialisationStrategy.getNewGrid();
        width = initialisationStrategy.getWidth();
        height = initialisationStrategy.getHeight();
    }

    @Override
    public Vector2 sizeGridUp() {
        cells = initialisationStrategy.doubleGridSize(cells);
        width *=2;
        height *=2;
        return initialisationStrategy.getLastOffset();
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void setCellAt(Vector2 position, ICell cell){
        this.cells[position.y * this.width + position.x] = cell;
    }

    @Override
    public ICell getCellAt(Vector2 position){
        return this.getCellAt(position.x, position.y);
    }

    @Override
    public ICell getCellAt(int x, int y) {
        return this.cells[x + y * this.width];
    }

    @Override
    public void setInitialisationStrategy(ICellInitialisationStrategy strategy) {
        this.initialisationStrategy = strategy;
    }
}
