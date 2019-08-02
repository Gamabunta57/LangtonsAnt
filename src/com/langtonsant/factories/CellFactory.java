package com.langtonsant.factories;

import com.langtonsant.application.element.cell.BlackCell;
import com.langtonsant.application.element.cell.WhiteCell;
import com.langtonsant.application.element.cell.CellType;
import com.langtonsant.application.element.cell.ICell;

public class CellFactory {

    public static ICell getCell(CellType cellType){
        if(cellType == CellType.White)
            return new WhiteCell();

        return new BlackCell();
    }
}
