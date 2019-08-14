package com;

import com.langtonsant.application.Application;
import com.langtonsant.application.IApplication;
import com.langtonsant.application.element.grid.IGrid;
import com.langtonsant.application.element.grid.InfiniteGrid;
import com.langtonsant.factories.GridWriterFactory;
import com.langtonsant.factories.MachineFactory;
import com.langtonsant.server.LangtonServer;

import java.io.IOException;

/**
 * Class entry point
 */
class Main {

    /**
     * Here's the entry point of the entire program.
     * This method initialize every elements needed to run the program and then start it.
     * By default a server is launched and listen the port 8080.
     * Request to the server has to use the PUT verb and have a "n" parameter to define the number of iteration.
     * Otherwise a "Fail" response is given and nothing happens.
     *
     * @param args program arguments (not used yet)
     * @throws IOException can throw an IOException if the server can't be launched
     */
    public static void main(String[] args) throws IOException {

        IGrid grid = new InfiniteGrid();

        String outputPath = System.getProperty("user.home") + "/langton_ant_grid_result.png";

        IApplication app = new Application(grid, MachineFactory.getMachine());
        app.setGridWriter(GridWriterFactory.getGridWriter(outputPath));

        LangtonServer server = new LangtonServer(8080);
        server.addListener(app);

        server.start();
    }
}
