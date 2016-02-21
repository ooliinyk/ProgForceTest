package factoryClasses;

import shops.Amazon;
import interfaces.Shop;
import interfaces.ShopFactory;

/**
 * realization of interface ShopFactory
 */
public class AmazonFactory implements ShopFactory{

    public Shop createShop() {
        return Amazon.getInstance();
    }
}
