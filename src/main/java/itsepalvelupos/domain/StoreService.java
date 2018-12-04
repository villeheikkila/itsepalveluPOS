package itsepalvelupos.domain;

import itsepalvelupos.database.AccountDao;
import itsepalvelupos.database.Database;
import itsepalvelupos.database.ProductDao;
import itsepalvelupos.database.StoreDao;

import java.sql.SQLException;

public class StoreService {

    private Database database;
    private AccountService accountService;
    private ProductService productService;

    private AccountDao accountDao;
    private ProductDao productDao;
    private StoreDao storeDao;

    private Store store;

    public StoreService(String db, Integer id) throws SQLException {
        database = new Database(db);
        database.initDatabase();

        accountDao = new AccountDao(database);
        productDao = new ProductDao(database);
        storeDao = new StoreDao(database);

        accountService = new AccountService(accountDao);
        productService = new ProductService(productDao);

        store = storeDao.getStore(id);
    }

    public ProductService getProductService() {
        return productService;
    }
}
