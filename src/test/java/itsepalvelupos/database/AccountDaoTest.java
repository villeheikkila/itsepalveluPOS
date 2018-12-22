package itsepalvelupos.database;

import itsepalvelupos.domain.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class AccountDaoTest {

    private Database database;
    private Account account;
    private AccountDao accountDao;

    @Before
    public void setUp() throws SQLException {
        database = new Database("test.db");
        database.initDatabase();

        accountDao = new AccountDao(database);
        account = new Account("account", "pass", true, 50);
        accountDao.add(account);
    }

    @After
    public void removeDatabase() throws SQLException {
        database.removeDatabase();
    }

    @Test
    public void findOne() throws SQLException {
        Account found = accountDao.findOne(1);
        assertEquals(found.getUsername(), "account");
        assertEquals(found.getPassword(), "pass");
        assertEquals(found.getBalance(), 50);
        assertEquals(found.isAdmin(), true);
    }

    @Test
    public void findOneFalse() throws SQLException {
        assertNull(accountDao.findOne(2));
    }

    @Test
    public void findAll() throws SQLException {
        Account newAccount = new Account("newAccount", "pass", true, 50);
        accountDao.add(newAccount);
        List<Account> accounts = accountDao.findAll();
        assertEquals(accounts.get(0).getUsername(), "account");
        assertEquals(accounts.get(0).getPassword(), "pass");
        assertEquals(accounts.get(0).getBalance(), 50);
        assertEquals(accounts.get(0).isAdmin(), true);
        assertEquals(accounts.get(1).getUsername(), "newAccount");
        assertEquals(accounts.get(1).getPassword(), "pass");
        assertEquals(accounts.get(1).getBalance(), 50);
        assertEquals(accounts.get(1).isAdmin(), true);
    }

    @Test
    public void delete() throws SQLException {
        accountDao.delete(1);
        assertNull(accountDao.findOne(1));
    }

    @Test
    public void findName() throws SQLException {
        Account found = accountDao.findName("account");
        assertEquals(found.getUsername(), "account");
        assertEquals(found.getPassword(), "pass");
        assertEquals(found.getBalance(), 50);
        assertEquals(found.isAdmin(), true);
    }

    @Test
    public void findNameFalse() throws SQLException {
        assertNull(accountDao.findName("NoAccount"));
    }

    @Test
    public void add() throws SQLException {
        Account newAccount = new Account("newAccount", "pass", true, 50);
        accountDao.add(newAccount);
        assertEquals(accountDao.findOne(2).getUsername(), "newAccount");
        assertEquals(accountDao.findOne(2).getPassword(), "pass");
        assertEquals(accountDao.findOne(2).getBalance(), 50);
        assertEquals(accountDao.findOne(2).isAdmin(), true);

    }

    @Test
    public void update() throws SQLException {
        account.setUsername("changedUsername");
        account.setBalance(10);
        account.setId(1);
        account.setPassword("password");
        accountDao.update(account);

        Account found = accountDao.findOne(1);
        assertEquals(found.getUsername(), "changedUsername");
        assertEquals(found.getPassword(), "password");
        assertEquals(found.getBalance(), 10);
        assertEquals(found.isAdmin(), true);
    }
}