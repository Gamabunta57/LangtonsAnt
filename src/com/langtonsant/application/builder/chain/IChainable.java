package com.langtonsant.application.builder.chain;

public interface IChainable {

    /**
     * Defines what should be the next element
     * @param nextElement the element to chain next
     */
    void setNext(IChainable nextElement);
}
