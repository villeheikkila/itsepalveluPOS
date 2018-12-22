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
     * @return palauttaa Store olion.
     *
     * @throws SQLException mikäli tapahtuu virhe
     *
     */

    public Store getStore(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Store WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet resultSet = stmt.executeQuery();
        boolean hasOne = resultSet.next();
        if (!hasOne) {
            resultSet.close();
            stmt.close();
            connection.close();
            return null;
        }

        Store store = new Store(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("cash"));

        resultSet.close();
        stmt.close();
        connection.close();

        return store;
    }

    /**
     * Metodi lisää kaupan tietokantatauluun
     *
     * @param  store  Lisättävä Store olio
     *
     * @throws SQLException mikäli tapahtuu virhe
     *
     */

    public void add(Store store) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Store (name, cash) VALUES (?, ?)");
        stmt.setString(1, store.getName());
        stmt.setInt(2, store.getCash());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    /**
     * Metodi päivittää kaupan tiedot tietokantataulusta
     *
     * @param  store Store olio, jonka tiedoilla korvataan vanhat tiedot
     *
     * @throws SQLException mikäli tapahtuu virhe
     *
     */

    public void update(Store store) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE Store SET name = ?, cash = ? WHERE ?");
        stmt.setString(1, store.getName());
        stmt.setInt(2, store.getCash());
        stmt.setInt(3, store.getId());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }
}
