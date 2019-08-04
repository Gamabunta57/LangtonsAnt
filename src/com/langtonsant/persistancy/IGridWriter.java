package com.langtonsant.persistancy;

import com.langtonsant.application.element.grid.IGrid;

import java.io.IOException;

/**
 * Contract to register the content of a grid into a file
 */
public interface IGridWriter {

    /**
     * Register a grid.
     *
     * @param grid the grid to register
     * @throws IOException throw and IOException if the file can't be created
     */
    void writeGrid(IGrid grid) throws IOException;
}
