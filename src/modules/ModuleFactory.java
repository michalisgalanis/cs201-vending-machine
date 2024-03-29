package modules;

import modules.containers.DosingContainer;
import modules.containers.FlowContainer;
import modules.containers.MaterialContainer;
import modules.containers.processor.IngredientProcessor;
import modules.dispensers.ConsumableDispenser;
import modules.external.*;
import tuc.ece.cs201.vm.hw.device.*;

public class ModuleFactory {

    public static Module createModule(Device device) {

        switch (device.getType()) {
            case FlowContainer:
                return new FlowContainer((FlowContainerDevice) device);
            case DosingContainer:
                return new DosingContainer((DosingContainerDevice) device);
            case MaterialContainer:
                return new MaterialContainer((MaterialContainerDevice) device);
            case Processor:
                return new IngredientProcessor((ProcessorDevice) device);
            case FlowDispenser:
                ConsumableDispenser dispenser = new ConsumableDispenser((DispenserDevice) device);
                for (Object containerDevice : ((DispenserDevice) device).listContainers()) {
                    dispenser.addContainer((FlowContainer) createModule((FlowContainerDevice) containerDevice));
                }
                return dispenser;
            case DosingDispenser:
                dispenser = new ConsumableDispenser((DispenserDevice) device);
                for (Object containerDevice : ((DispenserDevice) device).listContainers()) {
                    dispenser.addContainer((DosingContainer) createModule((DosingContainerDevice) containerDevice));
                }
                return dispenser;
            case MaterialDispenser:
                dispenser = new ConsumableDispenser((DispenserDevice) device);
                for (Object containerDevice : ((DispenserDevice) device).listContainers()) {
                    dispenser.addContainer((MaterialContainer) createModule((MaterialContainerDevice) containerDevice));
                }
                return dispenser;
            case ProductCase:
                return new ProductCase((ProductCaseDevice) device);
            case Display:
                return new DisplayPanel((DisplayDevice) device);
            case NumPad:
                return new NumPad((NumPadDevice) device);
            case CoinReader:
                return new CoinReader((CoinAcceptorDevice) device);
            case ChangeCase:
                return new ChangeCase((ChangeCaseDevice) device);
        }
        return null;
    }
}
