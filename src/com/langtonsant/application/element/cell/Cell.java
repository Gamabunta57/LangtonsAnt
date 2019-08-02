package com.langtonsant.application.element.cell;

public abstract class Cell implements ICell {

    private ICell nextCell;

    public ICell next(){
        return nextCell;
    }

    public void setNextCell(ICell nextCell){
        this.nextCell = nextCell;
    }
}
