package itsepalvelupos.database;

import itsepalvelupos.domain.Product;
import itsepalvelupos.domain.Store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreDao {

    private Database database;
    public StoreDao(Database database) {
        this.database = database;
    }

    /**
     * Metodi hakee kaupan tiedot tietokantataulusta
     *
     * @param   key   Kaupan id tietokantataulussa
     *
     *
     * @return palauttaa Store olion.
     */

    public Store getStore(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Store WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet resultSet = stmt.executeQuery();
        boolean hasOne = resultSet.next();
        if (!hasOne) {
            return null;
        }

        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Integer cash = resultSet.getInt("cash");

        Store store = new Store(id, name, cash);

        resultSet.close();
        stmt.close();
        connection.close();

        return store;
    }
}
