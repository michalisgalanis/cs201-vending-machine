package devices.consoleDevices.external;

import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.NumPadDevice;
import utilities.Reader;

public class ConsoleNumPadDevice extends ConsoleLockableExternalDevice implements NumPadDevice {

    private final Reader reader;

    public ConsoleNumPadDevice() {
        super("NumPadDevice", DeviceType.NumPad);
        reader = new Reader();
    }

    @Override
    public int readDigit(String s) {
        return reader.readInt(s);
    }
}
