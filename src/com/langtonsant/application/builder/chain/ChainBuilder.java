package com.langtonsant.application.builder.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * This helps to build a closed chain
 */
public class ChainBuilder<T extends IChainable> {

    private List<T> chainableElementList;

    public ChainBuilder(){
        this.chainableElementList = new ArrayList<>();
    }

    /**
     * Add an element in the chain
     *
     * @param chainableElement the element to add in the chain
     * @return this
     */
    public ChainBuilder add(T chainableElement){
        this.chainableElementList.add(chainableElement);
        return this;
    }

    /**
     * Build the closed chain
     *
     * @return the "first" element of the chain (the first added with adds).
     */
    public T build(){
        if(chainableElementList.isEmpty()) throw new RuntimeException("Can't build a chain without any elements on it");

        T firstCell = this.chainableElementList.get(0);

        int size = this.chainableElementList.size();
        for(int i = 0; i < size -1 ; i++){
            this.chainableElementList.get(i).setNext(this.chainableElementList.get(i+1));
        }

        this.chainableElementList.get(size-1).setNext(firstCell);

        return firstCell;
    }
}
