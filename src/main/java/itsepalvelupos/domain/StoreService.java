package itsepalvelupos.domain;

import itsepalvelupos.database.AccountDao;
import itsepalvelupos.database.Database;
import itsepalvelupos.database.ProductDao;
import itsepalvelupos.database.StoreDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class StoreService {

    private Database database;
    private AccountService accountService;
    private ProductService productService;

    private AccountDao accountDao;
    private ProductDao productDao;
    private StoreDao storeDao;

    private Store store;
    private ArrayList<Integer> shoppingCart;

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

    /**
     * Metodi ostaa tuotteen eli vähentää yhden tuotteen varastosta ja siirtää tuotteen hinnan käyttäjän tililtä kassaan
     *
     * @param   id   Tuotteen id (positiivinen kokonaisluku)
     *
     * @return palauttaa true, jos ostaminen on mahdollista
     */

    public boolean buy(int id) throws SQLException {
        if ((productDao.findOne(id) != null) && (accountService.getCurrentUser().getBalance() >= productDao.findOne(id).getPrice())) {
            int price = productDao.findOne(id).getPrice();
            productService.buyProduct(id);
            accountService.reduceBalance(price);
            store.addCash(price);
            storeDao.update(store);
            return true;
        }

        return false;
    }
    
    /**
     * Metodi ostaa kaikki tuotteet ostoskorissa ja tyhjentää lopuksi ostoskorin
     *
     */

    public void buyCart() throws SQLException {
        for (Integer id : shoppingCart) {
            buy(id);
        }
        emptyCart();
    }

    /**
     * Metodi lisää tuotteen ostoskoriin. Saman tuotteen voi lisätä vain kerran.
     *
     * @param   id   Tuotteen id (positiivinen kokonaisluku)
     *
     *
     * @return palauttaa true, jos lisääminen on mahdollista
     */

    public boolean addToCart(int id) throws SQLException {
        if (productDao.findOne(id).getInventory() > 0) {
            for (Integer cart : shoppingCart) {
                if (cart == id) {
                    return false;
                }
            }
            shoppingCart.add(id);
            return true;
        }
        return false;
    }

    /**
     * Tyhjentää ostoskorin
     *
     */

    public void emptyCart() {
        this.shoppingCart = null;
    }

    /**
     * Metodi mahdollistaa productServicen käsittelyn
     *
     * @return palauttaa productService olion
     */

    public ProductService getProductService() {
        return productService;
    }

    /**
     * Metodi mahdollistaa accountServicen käsittelyn
     *
     * @return palauttaa accountService olion
     */

    public AccountService getAccountService() {
        return accountService;
    }
}
