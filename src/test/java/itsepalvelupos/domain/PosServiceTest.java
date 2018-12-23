package itsepalvelupos.domain;

import itsepalvelupos.database.AccountDao;
import itsepalvelupos.database.Database;
import itsepalvelupos.database.ProductDao;
import itsepalvelupos.database.StoreDao;
import javafx.geometry.Pos;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PosServiceTest {

    private Database database;
    private AccountService accountService;
    private ProductService productService;
    private StoreService storeService;
    private PosService posService;

    private AccountDao accountDao;
    private ProductDao productDao;
    private StoreDao storeDao;

    private ArrayList<Integer> shoppingCart;
    private boolean newDatabase;

    private Product product;
    private Account account;

    @Before
    public void setUp() throws SQLException {
        posService = new PosService("test.db");

        posService.getAccountService().createUser("account", "pass", false, 1000);
        posService.getAccountService().login("account", "pass");

        posService.getProductService().addProduct("product", 10, 3);
        posService.getStoreService().createStore("test", 1000);

    }

    @After
    public void removeDatabase() throws SQLException {
        posService.getDatabase().removeDatabase();
    }

}