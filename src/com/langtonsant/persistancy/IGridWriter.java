package com.langtonsant.persistancy;

import com.langtonsant.application.element.grid.IGrid;

import java.io.IOException;

/**
 * Contract to register the content of a grid into a file
 */
public interface IGridWriter {

    /**
     * Register a grid into the file.
     *
     * @param grid the grid to register
     * @param filePath a valid filepath to where to save the result
     * @throws IOException throw and IOException if the file can't be created
     */
    void writeIntoFile(IGrid grid, String filePath) throws IOException;
}
