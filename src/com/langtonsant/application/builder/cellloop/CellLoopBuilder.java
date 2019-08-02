package com.langtonsant.application.builder.cellloop;

import com.langtonsant.application.element.cell.CellType;
import com.langtonsant.factories.CellFactory;
import com.langtonsant.application.element.cell.ICell;

import java.util.ArrayList;
import java.util.List;

public final class CellLoopBuilder {

    private List<ICell> cellList;

    public CellLoopBuilder(){
        this.cellList = new ArrayList<>();
    }

    public CellLoopBuilder addCell(CellType cellType){
        ICell cell = CellFactory.getCell(cellType);
        this.cellList.add(cell);
        return this;
    }

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
