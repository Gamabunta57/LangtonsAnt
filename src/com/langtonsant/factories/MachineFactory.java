package com.langtonsant.factories;

import com.langtonsant.application.element.ant.Machine;
import com.langtonsant.application.element.ant.IMachine;

public class MachineFactory {
    public static IMachine getMachine(){
        return new Machine();
    }
}
