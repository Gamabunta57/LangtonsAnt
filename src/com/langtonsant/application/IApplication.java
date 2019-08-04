package com.langtonsant.application;

import com.langtonsant.application.element.ant.IMachine;
import com.langtonsant.application.element.grid.IGrid;
import com.langtonsant.persistancy.IGridWriter;

/**
 * Contract to define a standard Application
 * This holds the main algorithm to apply
 */
public interface IApplication {
    /**
     * Run the algorithm and iterate it "iterationCount" times
     *
     * @param iterationCount number of iteration to run the algorithm
     */
    void run(int iterationCount);

    /**
     * Reset the data of the program to be able to run it again
     */
    void reset();

    /**
     * Defines the grid on which to run the algorithm
     *
     * @param grid the grid on which to run the algorithm
     */
    void setGrid(IGrid grid);

    /**
     * Defines the machine that walks the grid
     *
     * @param machine the machine to walk the grid
     */
    void setMachine(IMachine machine);

    /**
     * Defines the grid writer
     *
     * @param gridWriter
     */
    void setGridWriter(IGridWriter gridWriter);
}
