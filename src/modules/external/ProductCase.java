package modules.external;

import behaviour.Consumer;
import modules.Module;
import recipes.consumables.Consumable;
import recipes.consumables.Cup;
import recipes.consumables.ingredients.ProcessedIngredient;
import recipes.product.Product;
import recipes.product.ProductBuilder;
import tuc.ece.cs201.vm.hw.device.ProductCaseDevice;

public class ProductCase extends Module<ProductCaseDevice> implements Consumer {

    //Class variables
    private boolean plugged;
    private boolean prepared;
    private boolean loaded;
    private final ProductBuilder builder;
    private Consumable consumable;
    private Cup cup;


    //Constructor
    public ProductCase(ProductCaseDevice device) {
        super(device);
        setName(getClass().getSimpleName());
        plugged = false;
        prepared = false;
        loaded = false;
        builder = new ProductBuilder();
    }


    //Other Methods
    @Override
    public void acceptAndLoad(Consumable consumable) {
        assert plugged;
        assert consumable != null;
        if (consumable instanceof Cup) {
            cup = (Cup) consumable;
        } else {
            assert consumable instanceof ProcessedIngredient;
            this.consumable = consumable;
        }
        getDevice().loadIngredient(consumable.getName());
        loaded = true;
    }

    @Override
    public void plug(Consumer consumer) {
        if (!isPlugged()) {

            getDevice().connect(((Module) consumer).getDevice());
            setPlugged(true);
            consumer.setPlugged(true);
        }
    }

    @Override
    public void unPlug(Consumer consumer) {
        if (isPlugged()) {
            setPlugged(false);
            getDevice().disconnect(((Module) consumer).getDevice());
            consumer.setPlugged(false);
        }
    }

    @Override
    public void unPlugAll() {
        getDevice().disconnectAll();
        //TODO figure out what we're supposed to do here!
    }

    @Override
    public boolean isPlugged() {
        return plugged;
    }

    @Override
    public void setPlugged(boolean plugged) {
        this.plugged = plugged;
    }

    //Product Methods
    public Product getProduct() {
        assert prepared;
        //getDevice().getProcuct() Missing method in ProductCaseDevice - Displays info about ready product
        System.out.println("Please take your " + builder.getProduct().getProductName() + "."); //Just because above
        // method is missing
        getDevice().unLock();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Assuming Product is taken...");
        getDevice().lock();
        Product product = builder.getProduct();

        //Clear Things up
        setLoaded(false);
        setConsumable(null);
        setCup(null);

        return product;
    }

    public void prepareProduct(String productName, String material) {
        assert loaded;
        builder.createProduct(productName);
        getDevice().putMaterial(material);
        builder.addConsumable(consumable);
        prepared = true;
    }

    public void setPrepared(boolean prepared) {
        this.prepared = prepared;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public void setConsumable(Consumable consumable) {
        this.consumable = consumable;
    }

    public void setCup(Cup cup) {
        this.cup = cup;
    }
}
