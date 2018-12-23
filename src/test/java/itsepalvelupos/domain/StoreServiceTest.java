package itsepalvelupos.domain;

import itsepalvelupos.database.Database;
import itsepalvelupos.database.StoreDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class StoreServiceTest {

    private Database database;
    private StoreDao storeDao;
    private StoreService storeService;

    @Before
    public void setUp()  {
        database = new Database("test.db");
        database.initDatabase();

        storeDao = new StoreDao(database);
        storeService = new StoreService(storeDao);
    }

    @After
    public void removeDatabase() {
        database.removeDatabase();
    }

    @Test
    public void createStore() throws SQLException {
        storeService.createStore("test", 100);
        assertEquals(storeDao.getStore().getName(), "test");
        assertEquals(storeDao.getStore().getCash(), 100);
    }

    @Test
    public void createStoreFail() throws SQLException {
        storeService.createStore("te", 100);
        assertNull(storeDao.getStore());
    }

    @Test
    public void changeCash() throws SQLException {
        storeService.createStore("test", 100);
        storeService.changeCash(50);
        assertEquals(storeDao.getStore().getCash(), 150);

    }

    @Test
    public void changeCashNegativeValue() throws SQLException {
        storeService.createStore("test", 100);
        storeService.changeCash(-50);
        assertEquals(storeDao.getStore().getCash(), 50);

    }

    @Test
    public void changeCashNotEnoughCash() throws SQLException {
        storeService.createStore("test", 40);
        storeService.changeCash(-50);
        assertEquals(storeDao.getStore().getCash(), 40);

    }
}