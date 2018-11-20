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
        File file = new File("test.db");
        Database database = new Database("test.db");
        database.databaseInit();
        Store store = new Store(1000);
        store.addProduct(database, "Pepsi", 5, 150);
        ProductDao product = new ProductDao(database);
        Product test = product.findOne(1);
        assertEquals(test.getName(), "Pepsi");
        assertEquals(test.getInventory(), 150);
        assertEquals(test.getPrice(), 5);
        file.delete();
    }

    @Test
    void listProducts() {
    }
}