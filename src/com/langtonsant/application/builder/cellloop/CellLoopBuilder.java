package com.langtonsant.application.builder.cellloop;

import com.langtonsant.application.element.cell.CellType;
import com.langtonsant.factories.CellFactory;
import com.langtonsant.application.element.cell.ICell;

import java.util.ArrayList;
import java.util.List;

/**
 * This helps to build a cell closed chain to "cycle" the cells in the grid
 */
public final class CellLoopBuilder {

    private List<ICell> cellList;

    public CellLoopBuilder(){
        this.cellList = new ArrayList<>();
    }

    /**
     * Add a cell in the cell chain
     *
     * @param cellType the type of cell to add the chain
     * @return this
     */
    public CellLoopBuilder addCell(CellType cellType){
        ICell cell = CellFactory.getCell(cellType);
        this.cellList.add(cell);
        return this;
    }

    /**
     * Build the closed chain
     *
     * @return the "first" cell of the chain (the first added with addCell).
     */
    public ICell build(){
        if(cellList.isEmpty()) throw new RuntimeException("Can't build a chain without any elements on it");

        ICell firstCell = this.cellList.get(0);

        int size = this.cellList.size();
        for(int i = 0; i < size -1 ; i++){
            this.cellList.get(i).setNextCell(this.cellList.get(i+1));
        }

        this.cellList.get(size-1).setNextCell(firstCell);

        return firstCell;
    }
}
