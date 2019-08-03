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

/**
 * Class entry point
 */
class Main{

    /**
     * Here's the entry point of the entire program.
     * This method initialize every elements needed to run the program and then start it.
     * By default a server is launched and listen the port 8080.
     * Request to the server has to use the PUT verb and have a "n" parameter to define the number of iteration.
     * Otherwise a "Fail" response is given and nothing happens.
     *
     * @param args program arguments (not used yet)
     */
    public static void main(String[] args) throws IOException {

        CellLoopBuilder cellLoopBuilder = new CellLoopBuilder();

        ICell firstCellInChain = cellLoopBuilder
                .addCell(CellType.White)
                .addCell(CellType.Black)
                .build();

        IGrid grid = new Grid();
        grid.setInitialisationStrategy(new DefaultCellInitialisationStrategy(10,10, firstCellInChain));

        String outputFilePath = System.getProperty("user.home")+"/langton_ant_grid_result.txt";
        Application app = new Application();
        app.setGrid(grid);
        app.setMachine(MachineFactory.getMachine());
        app.setOutputFilePath(outputFilePath);

        LangtonServer server = new LangtonServer( 8080);
        server.addListener(app);

        server.start();
    }
}
