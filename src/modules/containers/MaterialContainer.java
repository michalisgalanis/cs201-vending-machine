package modules.containers;

import behaviour.Consumer;
import recipes.consumables.Consumable;
import tuc.ece.cs201.vm.hw.device.MaterialContainerDevice;

public class MaterialContainer extends Container<MaterialContainerDevice> {

    //Constructors
    public MaterialContainer(String name, int capacity, Consumable consumable, MaterialContainerDevice device) {
        super(name, capacity, consumable, device);
    }

    public MaterialContainer(MaterialContainerDevice device) {
        super(device);
        setName(device.getName().substring(0,(device.getName().length()-6)));
    }

    @Override
    public void provide(Consumer consumer, int quantity) {
        if (isPlugged()) {
            if (quantity <= getConsumable().getQuantity()) {
                consumer.acceptAndLoad(getConsumable().getPart(quantity));
                getDevice().releaseMaterial(getDevice());
            }
        }
    }
}
