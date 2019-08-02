package com.langtonsant.application.builder.grid;

import com.langtonsant.application.element.cell.ICell;
import com.langtonsant.math.Vector2;

public class DefaultCellInitialisationStrategy implements ICellInitialisationStrategy {

    private int width;
    private int height;
    private int baseWidth;
    private int baseHeight;
    private ICell defaultCell;
    private Vector2 lastOffset;

    public DefaultCellInitialisationStrategy(int width, int height, ICell defaultCell){
        if(width < 1) throw new IllegalArgumentException("The width of the grid must be a strict positive integer");
        if(height < 1) throw new IllegalArgumentException("The height of the grid must be a strict positive integer");
        if(defaultCell == null) throw new IllegalArgumentException("The default cell can't be null");

        this.width = width;
        this.height = height;
        this.baseWidth = width;
        this.baseHeight = height;
        this.defaultCell = defaultCell;
        lastOffset = new Vector2();
    }

    @Override
    public void reset() {
        this.width = baseWidth;
        this.height = baseHeight;
    }

    @Override
    public ICell[] getNewGrid() {
        int count = width * height;
        ICell[] cells = new ICell[count];

        for(int i = 0 ; i < count; i++)
            cells[i] = defaultCell;

        return cells;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public ICell[] doubleGridSize(ICell[] baseGrid) {
        int oldWidth = width;
        int oldHeight = height;
        lastOffset = new Vector2(oldWidth / 2,oldHeight / 2);

        width *= 2;
        height *= 2;

        ICell[] newCells = getNewGrid();

        for(int y = 0; y < oldHeight; y++){
            for(int x = 0; x < oldWidth;x++){
                newCells[(y + lastOffset.y) * width + x + lastOffset.x ] = baseGrid[y *oldWidth + x];
            }
        }

        return newCells;
    }

    public Vector2 getLastOffset(){
        return this.lastOffset;
    }
}
