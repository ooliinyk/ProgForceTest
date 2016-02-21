package shops;

import enums.Status;

/**
 * realization of table Product
 */
public class Product {
    private int productID;
    private String title;
    private double price;
    private int statusId;
    private int categoryId;
    private Status statusName;

    public Product() {
    }

    public Product(String title, double price, int statusId, int categoryId) {
        this.title = title;
        this.price = price;
        this.statusId = statusId;
        this.categoryId = categoryId;
        this.statusName = convertToStatusName(statusId);

    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Status convertToStatusName(int statusId) {
        switch (statusId) {
            case 1:
                return Status.Available;

            case 2:
                return Status.Absent;

            case 3:
                return Status.Expected;

            default:
                System.out.println("Wrong status");
                return Status.Absent;
        }
    }

    public int convertToStatusId(Status statusName) {

        switch (statusName) {
            case Available:
                return 1;

            case Absent:
                return 2;

            case Expected:
                return 3;

            default:
                System.out.println("Wrong status");
                return 2;
        }
    }

    public Status getStatusName() {
        return statusName;
    }

    public void setStatusName(Status statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(id:" + productID + ", title:" + title + ", price:" + price + "status: " + statusName + ")";

    }
}
