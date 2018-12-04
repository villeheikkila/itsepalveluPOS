package itsepalvelupos.domain;

import itsepalvelupos.database.Database;
import itsepalvelupos.database.ProductDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public boolean addProduct(String name, int price, int inventory) throws SQLException {
        if ((name.length() > 3) && (price >= 0) && (inventory >= 0)) { // name must be at least 3 letters and numbers must be positive.
            Product product = new Product(0000, name, inventory, price); // id must be removed at some point
            productDao.add(product);
            return true;
        } else {
            return false;
        }
    }

    public void listProducts() throws SQLException {
        List<Product> products = productDao.findAll();
        for (Product product : products) {
            System.out.println("name: " + product.getName() + " price: " + product.getPrice() + " inventory: " + product.getInventory());
        }
    }
    public boolean buyProduct(int id) throws SQLException {
        if (productDao.findOne(id) != null) {
            Product product = productDao.findOne(id);
            product.reduceInventory();
            productDao.update(id, product);
            return true;
        } else {
            return false;
        }
    }
}