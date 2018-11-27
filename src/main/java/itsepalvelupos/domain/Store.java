package itsepalvelupos.domain;

import itsepalvelupos.database.Database;
import itsepalvelupos.database.ProductDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Store {
    int cash;

    public Store(int cash) {
        this.cash = cash;
    }

    public boolean addProduct(Database database, String name, int price, int inventory) throws SQLException {
        if ((name.length() > 3) && (price >= 0) && (inventory >= 0)) { // name must be at least 3 letters and numbers must be positive.
            ProductDao productDB = new ProductDao(database);
            Product product = new Product(0000, name, inventory, price); // id must be removed at some point
            productDB.add(product);
            return true;
        } else {
            return false;
        }
    }

    public void listProducts(Database database) throws SQLException {
        List<Product> products = new ArrayList<>();
        ProductDao productDB = new ProductDao(database);
        products = productDB.findAll();
        for (Product product : products) {
            System.out.println("name: " + product.getName() + " price: " + product.getPrice() + " inventory: " + product.getInventory());
        }
    }
    public boolean buyProduct(Database database, int productId, int accountId) {
        return true;

    }
}
