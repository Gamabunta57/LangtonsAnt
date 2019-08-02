package com.langtonsant.application;

import com.langtonsant.application.element.ant.IMachine;
import com.langtonsant.application.element.grid.IGrid;

public interface IApplication {
    void run(int iterationCount);
    void reset();
    void setGrid(IGrid grid);
    void setMachine(IMachine machine);
    void setOutputFilePath(String filePath);
}
