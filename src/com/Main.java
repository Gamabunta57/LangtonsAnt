package com;
import com.langtonsant.application.Application;
import com.langtonsant.application.builder.cellloop.CellLoopBuilder;
import com.langtonsant.application.builder.grid.DefaultCellInitialisationStrategy;
import com.langtonsant.application.element.cell.CellType;
import com.langtonsant.application.element.cell.ICell;
import com.langtonsant.application.element.grid.Grid;
import com.langtonsant.application.element.grid.IGrid;
import com.langtonsant.factories.MachineFactory;
import com.langtonsant.server.LangtonServer;

import java.io.IOException;

class Main{

    public static void main(String[] args) throws IOException {

        CellLoopBuilder cellLoopBuilder = new CellLoopBuilder();

        ICell firstCellInChain = cellLoopBuilder
                .addCell(CellType.White)
                .addCell(CellType.Black)
                .build();

        IGrid grid = new Grid();
        grid.setInitialisationStrategy(new DefaultCellInitialisationStrategy(10,10, firstCellInChain));

        Application app = new Application();
        app.setGrid(grid);
        app.setMachine(MachineFactory.getMachine());
        app.setOutputFilePath("/Users/slo/test.txt");

        LangtonServer server = new LangtonServer( 8080);
        server.addListener(app);

        server.start();
    }
}
