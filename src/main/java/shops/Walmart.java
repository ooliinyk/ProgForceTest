package shops;

/**
 * An instance for Walmart shop
 */

public class Walmart extends ShopExample {
    private static Walmart ourInstance = new Walmart();

    public static Walmart getInstance() {
        return ourInstance;
    }

    private Walmart() {
    }
}
