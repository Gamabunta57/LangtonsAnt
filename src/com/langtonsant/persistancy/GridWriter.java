package com.langtonsant.persistancy;

import com.langtonsant.application.element.grid.IGrid;

public abstract class GridWriter implements IGridWriter {

    protected IGrid grid;

    public GridWriter(IGrid grid){
        this.grid = grid;
    }
}
