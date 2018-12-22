package itsepalvelupos.domain;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import itsepalvelupos.database.AccountDao;
import itsepalvelupos.database.Database;
import itsepalvelupos.database.ProductDao;
import itsepalvelupos.database.StoreDao;
import static org.junit.Assert.*;

import java.sql.SQLException;

public class AccountServiceTest {

    private Database database;
    private AccountService accountService;
    private ProductService productService;

    private AccountDao accountDao;
    private ProductDao productDao;
    private StoreDao storeDao;

    private Account currentUser;


    @Before
    public void setUp() throws SQLException {
        database = new Database("test.db");
        database.initDatabase();

        accountDao = new AccountDao(database);
        accountService = new AccountService(accountDao);
        Account account = new Account("username", "password", false, 100);
        Account currentUser = new Account("current", "pass", true, 50);

        accountDao.add(account);
        accountDao.add(currentUser);
        accountService.login("current", "pass");
    }



    @Test
    public void loginSucceed() throws SQLException {
        assertTrue(accountService.login("username", "password"));
    }

    @Test
    public void loginNoAccount() throws SQLException {
        assertFalse(accountService.login("wrongUsername", "password"));
    }

    @Test
    public void loginWrongPassword() throws SQLException {
        assertFalse(accountService.login("username", "wrongPassword"));
    }

    @Test
    public void findAccount() throws SQLException {
        assertEquals(accountService.find("username").getUsername(), "username");
    }

    @Test
    public void createUser() throws SQLException {
        accountService.createUser("user", "pass", false, 0);
        assertEquals(accountDao.findName("user").getUsername(), "user");
    }

    @Test
    public void createUserExists() throws SQLException {
        assertFalse(accountService.createUser("username", "pass", false, 0));
    }

    @Test
    public void makeAdmin() throws SQLException{
        accountService.makeAdmin(1);
        assertTrue(accountDao.findOne(1).isAdmin());
    }

    @Test
    public void makeAdminFail() throws SQLException{
        accountService.getCurrentUser().setAdmin(false);
        accountService.makeAdmin(1);
        assertFalse(accountDao.findOne(1).isAdmin());
    }

    @Test
    public void changeBalance() throws SQLException {
        accountService.changeBalance(-10);
        assertEquals(accountService.getCurrentUser().getBalance(), 40);
    }

    @Test
    public void logOut() {
        accountService.logOut();
        assertNull(accountService.getCurrentUser());
    }

    @Test
    public void getCurrentUser() {
        assertEquals(accountService.getCurrentUser().getUsername(), "current");
    }

    @After
    public void removeDatabase() throws SQLException {
        database.removeDatabase();
    }
}
