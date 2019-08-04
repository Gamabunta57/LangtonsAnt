package com.langtonsant.factories;

import com.langtonsant.application.element.cell.BlackCell;
import com.langtonsant.application.element.cell.CellType;
import com.langtonsant.application.element.cell.ICell;
import com.langtonsant.application.element.cell.WhiteCell;

/**
 * Simple factory to get cells according to their type
 */
public class CellFactory {

    /**
     * Return a cell according to the given cell type
     *
     * @param cellType the cell type on which to create the celle
     * @return a new cell
     */
    public static ICell getCell(CellType cellType) {
        if (cellType == CellType.White) {
            return new WhiteCell();
        }

        return new BlackCell();
    }
}
