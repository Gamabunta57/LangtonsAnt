package com.langtonsant.persistancy;

import com.langtonsant.application.element.grid.IGrid;
import com.langtonsant.math.Rectangle;
import com.langtonsant.math.Vector2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * This class manage the output of a given IGrid into a .txt file
 */
public final class GridTextWriter implements IGridWriter {

    private final String outputPath;

    /**
     * Constructor
     *
     * @param outputPath a valid path where to write the TXT file
     */
    public GridTextWriter(String outputPath) {
        this.outputPath = outputPath;
    }

    @Override
    public void writeGrid(IGrid grid) throws IOException {

        OutputStream os = new FileOutputStream(outputPath);

        int gridHeight = grid.getHeight();
        int gridWidth = grid.getWidth();
        Rectangle croppedArea = grid.GetArea();

        Vector2 offsetStart = new Vector2(croppedArea.getX(),croppedArea.getY());
        Vector2 positionInGrid = new Vector2();
        for (int y = 0; y < gridHeight; y++) {
            positionInGrid.y = y + offsetStart.y;
            for (int x = 0; x < gridWidth; x++) {
                positionInGrid.x = x + offsetStart.x;
                os.write(grid.hasCellAt(positionInGrid) ? '+' : '.');
            }
            os.write(System.lineSeparator().getBytes());
        }
        os.close();
    }
}
