package shops;

import connection.ConnectionJDBC;
import interfaces.Shop;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *  realization of interface shop
 */
public class ShopExample implements Shop {


    public List<Integer> getCategoryIds(int shop_id) {


        String getProduct = "select category_id from shop_test.categories  where shop_id=" + shop_id + ";";

        List<Integer> categoryId = new ArrayList<Integer>();
        try {

            ConnectionJDBC connectionJDBC = new ConnectionJDBC();
            Statement statement = connectionJDBC.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getProduct);
            while (resultSet.next()) {
                categoryId.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryId;

    }

    public Product getProduct(int productId) {
        String getProduct = "select * from shop_test.products  where product_id=" + productId + ";";
        Product product = new Product();
        try {

            ConnectionJDBC connectionJDBC = new ConnectionJDBC();
            Statement statement = connectionJDBC.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getProduct);

            while (resultSet.next()) {
                product.setProductID(resultSet.getInt(1));
                product.setTitle(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setStatusId(resultSet.getInt(4));
                product.setCategoryId(resultSet.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getAllProductsFromCategory(int categoryId) {
        String getProduct = "select * from  shop_test.products where category_id=" + categoryId + ";";

        List<Product> temp = new ArrayList<Product>();

        try {

            ConnectionJDBC connectionJDBC = new ConnectionJDBC();
            Statement statement = connectionJDBC.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getProduct);

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductID(resultSet.getInt(1));
                product.setTitle(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setStatusId(resultSet.getInt(4));
                product.setCategoryId(resultSet.getInt(5));
                temp.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public List<Product> getAllProducts() {
        String getProduct = "select * from  shop_test.products;";

        List<Product> temp = new ArrayList<Product>();

        try {

            ConnectionJDBC connectionJDBC = new ConnectionJDBC();
            Statement statement = connectionJDBC.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getProduct);

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductID(resultSet.getInt(1));
                product.setTitle(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setStatusId(resultSet.getInt(4));
                product.setCategoryId(resultSet.getInt(5));
                temp.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    ;

    public List<Product> getAllProductsFromShop(int shopId) {
        String getProduct = "SELECT products.product_id, products.title, products.price," +
                " products.status_id, products.category_id FROM shop_test.products , shop_test.categories where" +
                " shop_test.categories.category_id=shop_test.products.category_id and shop_test.categories.shop_id=" + shopId + ";";

        List<Product> temp = new ArrayList<Product>();

        try {

            ConnectionJDBC connectionJDBC = new ConnectionJDBC();
            Statement statement = connectionJDBC.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getProduct);

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductID(resultSet.getInt(1));
                product.setTitle(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setStatusId(resultSet.getInt(4));
                product.setCategoryId(resultSet.getInt(5));
                temp.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    ;

    public void addProduct(Product product) {
        String addProduct = "INSERT  into shop_test.products  (title, price, status_id, category_id) VALUES ( ?,?,?,?);";

        try {
            ConnectionJDBC connectionJDBC = new ConnectionJDBC();

            PreparedStatement ps = connectionJDBC.getConnection().prepareStatement(addProduct);

            ps.setString(1, product.getTitle());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getStatusId());
            ps.setInt(4, product.getCategoryId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateStatus(int productId, int status) {
        String updateStatus = "Update shop_test.products  set status_id= ? WHERE product_id=" + productId + ";";

        try {
            ConnectionJDBC connectionJDBC = new ConnectionJDBC();
            PreparedStatement ps = connectionJDBC.getConnection().prepareStatement(updateStatus);

            ps.setInt(1, status);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updatePrice(int productId, double price) {
        String updateStatus = "Update shop_test.products  set price= ? WHERE product_id=" + productId + ";";

        try {
            ConnectionJDBC connectionJDBC = new ConnectionJDBC();
            PreparedStatement ps = connectionJDBC.getConnection().prepareStatement(updateStatus);

            ps.setDouble(1, price);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
