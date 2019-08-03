package com.langtonsant.application;

import com.langtonsant.application.element.grid.IGrid;
import com.langtonsant.application.element.ant.IMachine;
import com.langtonsant.math.Vector2;
import com.langtonsant.persistancy.GridTextWriter;
import com.langtonsant.server.IRequestListener;

import java.io.IOException;

/**
 * This handles the standard Langton's ant algorithm
 */
public class Application implements IApplication, IRequestListener {

    private IMachine machine;
    private IGrid grid;
    private String outputFilePath;

    @Override
    public void setGrid(IGrid grid){
        this.grid = grid;
    }

    @Override
    public void setMachine(IMachine machine){
        this.machine = machine;
    }

    @Override
    public void setOutputFilePath(String outputFilePath){
        this.outputFilePath = outputFilePath;
    }

    @Override
    public void run(int iterationCount){
        try {
            while (iterationCount-- > 0)
                runOneStep();

            tryRegisterGrid();
        }catch (Exception exception){
            System.out.println(exception);
        }
    }

    @Override
    public void reset() {
        grid.reset();
        machine.resetOnGrid(grid.getWidth(), grid.getHeight());
    }

    @Override
    public void OnValidRequestReceived(int iterationCount) {
        reset();
        run(iterationCount);
    }


    /**
     * Try to save the grid into the file
     */
    private void tryRegisterGrid() {
        try {
            GridTextWriter writer = new GridTextWriter();
            writer.writeIntoFile(grid, outputFilePath);
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Run one step of the Langton's algorithm
     *
     * It the machine get "outside" the grid, it expands the grid and replace the machine onto the matching new grid coordinates.
     */
    private void runOneStep(){
        Vector2 currentMachinePosition = this.machine.getPosition();
        Vector2 machineHeading = this.machine.getHeading();

        this.machine.setHeading(this.grid.computeNewHeadingFrom(currentMachinePosition, machineHeading));
        this.grid.cycleCellAt(currentMachinePosition);
        this.machine.move();

        if(!this.grid.isValidPosition(this.machine.getPosition())) {
            Vector2 resizeOffset = grid.sizeGridUp();
            machine.getPosition().add(resizeOffset);
        }
    }
}
