package itsepalvelupos.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import itsepalvelupos.domain.Account;


public class AccountDao implements Dao<Account, Integer> {

    private Database database;

    public AccountDao(Database database) {
        this.database = database;
    }

    @Override
    public Account findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Accounts WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet resultSet = stmt.executeQuery();
        boolean hasOne = resultSet.next();
        if (!hasOne) {
            return null;
        }

        Integer id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        Boolean admin = resultSet.getBoolean("admin");

        Account account = new Account(id, username, password, admin);

        resultSet.close();
        stmt.close();
        connection.close();

        return account;
    }

    @Override
    public List<Account> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Accounts");

        ResultSet resultSet = stmt.executeQuery();
        List<Account> accounts = new ArrayList<>();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            System.out.println(id);
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            Boolean admin = resultSet.getBoolean("admin");
        }

        resultSet.close();
        stmt.close();
        connection.close();

        return accounts;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Accounts WHERE id = (?)");
        stmt.setObject(1, key);

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    public Account findName(String userName) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Accounts WHERE username = ?");
        stmt.setString(1, userName);

        ResultSet resultSet = stmt.executeQuery();

        boolean hasOne = resultSet.next();
        if (!hasOne) {
            return null;
        }

        Integer id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        Boolean admin = resultSet.getBoolean("admin");

        Account account = new Account(id, username, password, admin);

        resultSet.close();
        stmt.close();
        connection.close();

        return account;
    }

    public void add(Account account) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Accounts (username, password, admin) VALUES (?, ?, ?)");
        stmt.setString(1, account.getUsername());
        stmt.setString(2, account.getPassword());
        stmt.setBoolean(3, account.isAdmin());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    public void update(Account account) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE Accounts SET username = ?, password = ?, admin = ? WHERE ?");
        stmt.setString(1, account.getUsername());
        stmt.setString(2, account.getPassword());
        stmt.setBoolean(3, account.isAdmin());
        stmt.setInt(4, account.getId());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }
}