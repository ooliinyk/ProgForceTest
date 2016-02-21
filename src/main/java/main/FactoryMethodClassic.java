package main;

import factoryClasses.AmazonFactory;
import factoryClasses.WalmartFactory;
import shops.Product;
import enums.Status;
import interfaces.Shop;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Factory method to open 2 streams
 */
public class FactoryMethodClassic {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);



        AmazonFactory amazonFactory = new AmazonFactory();
        WalmartFactory walmartFactory = new WalmartFactory();

        final List<Shop> shops = new ArrayList<Shop>();
        shops.add(amazonFactory.createShop());
        shops.add(walmartFactory.createShop());

        shops.get(0);
        shops.get(1);

        Thread myThreadAmazom = new Thread(new Runnable() {
            public void run() {
                long TIMER=10000;
                try {
                    Thread.sleep(TIMER);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final int SHOP_AMAZON_ID = 1;
                final int CATEGORY_TO_UPDATE_AMAZON = 1;

                Shop walmart = shops.get(0);
                Random random = new Random();

                List<Integer> categoriesIds = walmart.getCategoryIds(SHOP_AMAZON_ID);

                /* add 4 random products to each  category from the shop 1*/
                for (Integer iterator : categoriesIds) {
                    for (int j = 1; j < 5; j++) {
                        walmart.addProduct(new Product("title" + j, random.nextInt(1000), (random.nextInt(3) + 1), iterator));
                    }
                }

                List<Product> walmartProducts = walmart.getAllProductsFromShop(SHOP_AMAZON_ID);


                /* set status ABSENT to Category CATEGORY_TO_UPDATE_AMAZON */

                for (Product product : walmartProducts) {
                    if (product.getCategoryId() == CATEGORY_TO_UPDATE_AMAZON) {
                        walmart.updateStatus(product.getProductID(), product.convertToStatusId(Status.Absent));
                    }
                }

                  /*set status Expected to first half of the list*/
                for (Product product : walmartProducts.subList(0, (walmartProducts.size() / 2))) {
                    if (product.getCategoryId() != CATEGORY_TO_UPDATE_AMAZON) {
                        walmart.updateStatus(product.getProductID(), product.convertToStatusId(Status.Expected));
                    }
                }

                /*Update price of products where status=Available*/

                for (Product product : walmartProducts) {
                    if (product.getStatusId() == product.convertToStatusId(Status.Available)) {
                        walmart.updatePrice(product.getProductID(), (product.getPrice() * 0.2));
                    }
                }
                System.out.println("first thread is finished");
            }
        });


        Thread myThreadWalmart = new Thread(new Runnable() {
            public void run() {


                final int SHOP_WALMART_ID = 2;
                final int CATEGORY_TO_UPDATE_WALMART = 6;
                //  add products
                Shop walmart = shops.get(0);
                Random random = new Random();

                List<Integer> categoriesIds = walmart.getCategoryIds(SHOP_WALMART_ID);

                /* add 4 random products to each  category from the shop 1*/

                for (Integer iterator : categoriesIds) {
                    for (int j = 1; j < 5; j++) {
                        walmart.addProduct(new Product("title" + j, random.nextInt(1000), (random.nextInt(3) + 1), iterator));
                    }
                }

                List<Product> walmartProducts = walmart.getAllProductsFromShop(SHOP_WALMART_ID);

                /* set status ABSENT to Category CATEGORY_TO_UPDATE_AMAZON */

                for (Product product : walmartProducts) {
                    if (product.getCategoryId() == CATEGORY_TO_UPDATE_WALMART) {
                        walmart.updateStatus(product.getProductID(), product.convertToStatusId(Status.Absent));
                    }
                }

                /*set status Expected to first half of the list*/

                for (Product product : walmartProducts.subList(0, (walmartProducts.size() / 2))) {
                    if (product.getCategoryId() != CATEGORY_TO_UPDATE_WALMART) {
                        walmart.updateStatus(product.getProductID(), product.convertToStatusId(Status.Expected));
                    }
                }

                /*Update price of products where status=Available*/

                for (Product product : walmartProducts) {
                    if (product.getStatusId() == product.convertToStatusId(Status.Available)) {
                        walmart.updatePrice(product.getProductID(), (product.getPrice() * 0.2));
                    }
                }
                System.out.println("Second thread is finished");
            }
        });
        ScheduledFuture<?>  future = executor.schedule(myThreadWalmart, 10, TimeUnit.SECONDS);
        myThreadAmazom.start();
        myThreadWalmart.start();


        System.out.println("Main is finished");


    }

}
