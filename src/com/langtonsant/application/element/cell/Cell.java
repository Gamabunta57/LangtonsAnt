package com.langtonsant.application.element.cell;

import com.langtonsant.application.builder.chain.IChainable;

public abstract class Cell implements ICell {

    private ICell nextCell;

    @Override
    public ICell next(){
        return nextCell;
    }

    @Override
    public void setNext(IChainable nextCell){
        this.nextCell = (ICell) nextCell;
    }
}
