package com.langtonsant.application;

import com.langtonsant.application.element.ant.IMachine;
import com.langtonsant.application.element.grid.IGrid;
import com.langtonsant.math.Vector2;
import com.langtonsant.persistancy.IGridWriter;
import com.langtonsant.server.IRequestListener;

import java.io.IOException;

/**
 * This handles the standard Langton's ant algorithm
 */
public class Application implements IApplication, IRequestListener {

    private IMachine machine;
    private IGrid grid;
    private IGridWriter gridWriter;

    @Override
    public void setGrid(IGrid grid) {
        this.grid = grid;
    }

    @Override
    public void setMachine(IMachine machine) {
        this.machine = machine;
    }

    @Override
    public void setGridWriter(IGridWriter gridWriter) {
        this.gridWriter = gridWriter;
    }

    @Override
    public void run(int iterationCount) {
        if (null == machine) throw new RuntimeException("The machine is not defined, can't run the process");
        if (null == grid) throw new RuntimeException("The grid is not defined, can't run the process");

        try {
            while (iterationCount-- > 0) {
                runOneStep();
            }

            tryRegisterGrid();
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
    }

    @Override
    public void reset() {
        grid.reset();
        machine.resetOnGrid(grid.getWidth(), grid.getHeight());
    }

    @Override
    public void onValidRequestReceived(int iterationCount) {
        reset();
        run(iterationCount);
    }


    /**
     * Try to save the grid into the file
     */
    private void tryRegisterGrid() {
        if (null == gridWriter) return;

        try {
            gridWriter.writeGrid(grid);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Run one step of the Langton's algorithm
     * It the machine get "outside" the grid, it expands the grid and replace the machine onto the matching new grid coordinates.
     */
    private void runOneStep() {
        Vector2 currentMachinePosition = machine.getPosition();
        Vector2 machineHeading = machine.getHeading();

        machine.setHeading(grid.computeNewHeadingFrom(currentMachinePosition, machineHeading));
        grid.cycleCellAt(currentMachinePosition);
        machine.move();

        if (grid.isValidPosition(machine.getPosition())) {
            Vector2 resizeOffset = grid.sizeGridUp();
            machine.getPosition().add(resizeOffset);
        }
    }
}
