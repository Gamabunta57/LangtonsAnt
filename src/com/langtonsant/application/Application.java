package com.langtonsant.application;

import com.langtonsant.application.element.grid.IGrid;
import com.langtonsant.application.element.ant.IMachine;
import com.langtonsant.math.Vector2;
import com.langtonsant.persistancy.GridTextWriter;
import com.langtonsant.server.IRequestListener;

import java.io.IOException;

public class Application implements IApplication, IRequestListener {

    private IMachine machine;
    private IGrid grid;
    private String outputFilePath;

    public void setGrid(IGrid grid){
        this.grid = grid;
    }

    public void setMachine(IMachine machine){
        this.machine = machine;
    }

    public void setOutputFilePath(String outputFilePath){
        this.outputFilePath = outputFilePath;
    }

    public void run(int iterationCount){
        try {
            while (iterationCount-- > 0)
                runOneStep();

            registerGrid();
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
    public void OnGoodRequestReceived(int iterationCount) {
        reset();
        run(iterationCount);
    }

    private void registerGrid() {
        try {
            GridTextWriter writer = new GridTextWriter(grid);
            writer.writeIntoFile(outputFilePath);
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }

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
