package itsepalvelupos.domain;

import itsepalvelupos.database.AccountDao;
import java.sql.SQLException;

public class AccountService {

    private AccountDao accountDao;
    private Account currentUser;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public boolean login(String userName, String password) throws SQLException {
        Account account = accountDao.findName(userName);

        if (userName == null) {
            return false;
        }

        if (account.getPassword().equals(password)) {
            currentUser = account;
            return true;
        }

        return false;
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

    public void logOut() {
        currentUser = null;
    }

    public Account getCurrentUser() {
        return currentUser;
    }


}
