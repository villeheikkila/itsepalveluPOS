package itsepalvelupos.domain;

import itsepalvelupos.database.AccountDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class AccountService {

    private AccountDao accountDao;
    private Account currentUser;
    private ArrayList<Integer> shoppingCart;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public boolean login(String userName) throws SQLException {
        Account account = accountDao.findName(userName);

        if (userName == null) {
            return false;
        }

        currentUser = account;

        return true;
    }

    public boolean createUser(String userName, String password, boolean admin) throws SQLException  {
        if (accountDao.findName(userName) != null) {
            return false;
        }

        Account account = new Account(0000, userName, password, admin);
        accountDao.add(account);

        return true;
    }

    public boolean makeAdmin(Integer id) throws SQLException {
        if (currentUser.isAdmin() == true) {
            Account account = accountDao.findOne(id);
            account.setAdmin(true);
            accountDao.update(account);
            return true;
        } else {
            return false;
        }
    }

    public void checkOutCart() throws SQLException {

    }

    public void logOut() {
        currentUser = null;
    }

    public Account getCurrentUser() {
        return currentUser;
    }


}
