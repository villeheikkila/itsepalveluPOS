package itsepalvelupos.database;

import itsepalvelupos.domain.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ProductDaoTest {

    private Database database;
    private Product product;
    private ProductDao productDao;

    @Before
    public void setUp() throws SQLException {
        database = new Database("test.db");
        database.initDatabase();

        productDao = new ProductDao(database);
        product = new Product("product", 1, 10);
        productDao.add(product);

    }

    @After
    public void removeDatabase() throws SQLException {
        database.removeDatabase();
    }

    @Test
    public void findOne() throws SQLException {
        Product found = productDao.findOne(4);
        assertEquals(found.getName(), "product");
        assertEquals(found.getInventory(), 1);
        assertEquals(found.getPrice(), 10);
    }

    @Test
    public void findAll() throws SQLException {
        productDao.add(new Product("renamedProduct", 10, 5));
        List<Product> products = productDao.findAll();
        assertEquals(products.get(3).getName(), "product");
        assertEquals(products.get(3).getInventory(), 1);
        assertEquals(products.get(3).getPrice(), 10);
        assertEquals(products.get(4).getName(), "renamedProduct");
        assertEquals(products.get(4).getInventory(), 10);
        assertEquals(products.get(4).getPrice(), 5);
    }

    @Test
    public void delete() throws SQLException {
        productDao.delete(1);
        assertNull(productDao.findOne(1));
    }

    @Test
    public void add() throws SQLException {
        productDao.add(new Product("newProduct", 2, 50));
        Product found = productDao.findOne(5);
        assertEquals(found.getName(), "newProduct");
        assertEquals(found.getInventory(), 2);
        assertEquals(found.getPrice(), 50);

    }

    @Test
    public void update() throws SQLException {
        product.setId(1);
        product.setName("renamedProduct");
        product.setInventory(10);
        product.setPrice(5);
        productDao.update(product);

        Product found = productDao.findOne(1);
        assertEquals(found.getName(), "renamedProduct");
        assertEquals(found.getInventory(), 10);
        assertEquals(found.getPrice(), 5);

    }
}