package com.langtonsant.persistancy;
import com.langtonsant.application.element.cell.CellType;
import com.langtonsant.application.element.grid.IGrid;
import com.langtonsant.math.Vector2;

import java.io.*;

/**
 * This class manage the output of a given IGrid into a .txt file
 *
 */
public class GridTextWriter implements IGridWriter{

    private String outputPath;

    /**
     * Constructor
     *
     * @param outputPath a valid path where to write the file
     */
    public GridTextWriter(String outputPath){
        this.outputPath = outputPath;
    }

    @Override
    public void writeGrid(IGrid grid) throws IOException {

        OutputStream os = new FileOutputStream(outputPath);

        int gridHeight = grid.getHeight();
        int gridWidth = grid.getWidth();

        Vector2 positionInGrid = new Vector2();
        for(int y = 0; y < gridHeight;y++) {
            positionInGrid.y = y;
            for (int x = 0; x < gridWidth; x++) {
                positionInGrid.x = x;
                os.write(this.getCellMatchingChar(grid.getCellAt(x,y).getCellType()));
            }
            os.write(System.lineSeparator().getBytes());
        }
        os.close();
    }

    /**
     * Get a character to write according to the cell type
     * @param cellType
     * @return an arbitrary char depending on the cell type
     */
    private char getCellMatchingChar(CellType cellType){
        switch(cellType){
            case Black:
                return '+';
            case White:
                return '.';
            default:
                throw new RuntimeException("The cell type used is not implemented here");
        }
    }
}
