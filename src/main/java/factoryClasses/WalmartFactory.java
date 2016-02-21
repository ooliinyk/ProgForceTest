package factoryClasses;

import shops.Walmart;
import interfaces.Shop;
import interfaces.ShopFactory;

/**
 * realization of interface ShopFactory
 */
public class WalmartFactory implements ShopFactory {

    public Shop createShop() {
        return Walmart.getInstance();
    }
}
