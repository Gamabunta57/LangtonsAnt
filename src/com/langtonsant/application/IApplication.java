package com.langtonsant.application;

import com.langtonsant.persistancy.IGridWriter;
import com.langtonsant.server.IRequestListener;

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
     * Defines the grid writer
     *
     * @param gridWriter The gridWriter to use for saving the resulting grid
     */
    void setGridWriter(IGridWriter gridWriter);
}
