package itsepalvelupos.domain;

import itsepalvelupos.database.Database;
import itsepalvelupos.database.ProductDao;
import itsepalvelupos.domain.Product;
import itsepalvelupos.domain.Store;
import java.io.File;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {


    public StoreTest() {
    }

    @Test
    void addProductActuallyAddsProduct()  throws Exception{
        Database database = new Database("test.db");
        database.initDatabase();
        ProductDao product = new ProductDao(database);
        Product test = product.findOne(1);
        assertEquals(test.getName(), "Pepsi");
        assertEquals(test.getInventory(), 150);
        assertEquals(test.getPrice(), 5);
        database.removeDatabase();
    }

    @Test
    void listProducts() {
    }
}