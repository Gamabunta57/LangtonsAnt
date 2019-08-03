package com.langtonsant.application.element.cell;

public abstract class Cell implements ICell {

    private ICell nextCell;

    @Override
    public ICell next(){
        return nextCell;
    }

    @Override
    public void setNextCell(ICell nextCell){
        this.nextCell = nextCell;
    }
}
