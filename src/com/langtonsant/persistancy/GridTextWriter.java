package com.langtonsant.persistancy;
import com.langtonsant.application.element.cell.ICell;
import com.langtonsant.application.element.grid.IGrid;
import com.langtonsant.math.Vector2;

import java.io.*;

public final class GridTextWriter extends GridWriter{

    public GridTextWriter(IGrid grid){
        super(grid);
    }

    @Override
    public void writeIntoFile(String fileName) throws IOException {
        File file = new File(fileName);
        if(!file.exists() && !file.createNewFile()) throw new IOException("The output file couldn't be created.");

        FileWriter writer = new FileWriter(file);

        int gridHeight = grid.getHeight();
        int gridWidth = grid.getWidth();

        Vector2 positionInGrid = new Vector2();
        for(int y = 0; y < gridHeight;y++) {
            positionInGrid.y = y;
            for (int x = 0; x < gridWidth; x++) {
                positionInGrid.x = x;
                writer.write(this.getCellMatchingChar(grid.getCellAt(x,y)));
            }
            writer.write(System.lineSeparator());
        }
        writer.close();
    }

    /**
     * Get a character to write according to the cell type
     * @param cellType
     * @return an arbitrary char depending on the cell type
     */
            case Black:
                return '+';
            case White:
                return '.';
            default:
                throw new RuntimeException("The cell type used is not implemented here");
        }
    }
}
