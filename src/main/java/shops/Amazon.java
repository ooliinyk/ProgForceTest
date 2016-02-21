package shops;


/**
 * An instance for Walmart shop
 */

public class Amazon extends ShopExample {
    private static Amazon ourInstance = new Amazon();

    public static Amazon getInstance() {
        return ourInstance;
    }

    private Amazon() {
    }
}
