package com.langtonsant.persistancy;

import java.io.IOException;

/**
 * Contract to register the content of a grid into a file
 */
public interface IGridWriter {

    void writeIntoFile(String filename) throws IOException;
     * Register a grid into the file.
     *
     * @param grid the grid to register
     * @param filePath a valid filepath to where to save the result
     * @throws IOException throw and IOException if the file can't be created
     */
}
