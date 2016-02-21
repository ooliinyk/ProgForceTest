package interfaces;

import shops.Product;

import java.util.List;

/**
 * Created by user on 20.02.2016.
 */
public interface Shop {

    public Product getProduct(int productId);

    public List<Product> getAllProductsFromCategory(int categoryId);

    public List<Product> getAllProducts();

    public List<Product> getAllProductsFromShop(int shopId);

    public void addProduct(Product product);

    public void updateStatus(int productId, int status);

    public void updatePrice(int productId, double price);

    public List<Integer> getCategoryIds(int shop_id);

}
