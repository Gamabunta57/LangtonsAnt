package com.langtonsant.factories;

import com.langtonsant.application.element.ant.IMachine;
import com.langtonsant.application.element.ant.Machine;

/**
 * A simple factory to create machines
 */
public class MachineFactory {

    /**
     * For now it can only output 1 machine type
     * Later it will use a machine type and create a machine according to the type.
     *
     * @return a new machine
     */
    public static IMachine getMachine() {
        return new Machine();
    }
}
