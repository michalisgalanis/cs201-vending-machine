package devices.consoleDevices.internal;

import devices.consoleDevices.ConsoleDevice;
import devices.containers.ContainerDevice;
import devices.dispensers.DispenserDevice;

public class ConsoleDispenserDevice extends ConsoleDevice implements DispenserDevice {


    @Override
    public ContainerDevice[] listContainers() {
        return new ContainerDevice[0];
    }

    @Override
    public void prepareContainer(ContainerDevice containerDevice) {
        System.out.println("Prepared " + containerDevice.getName());
    }

    @Override
    public void addContainer(ContainerDevice containerDevice) {
        System.out.println("Added " + containerDevice.getName());
    }

    @Override
    public void removeContainer(String name) {
        System.out.println("Deleted " + name);
    }
}
