package itsepalvelupos.database;

import itsepalvelupos.domain.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDao implements Dao<Product, Integer> {

    private Database database;

    public ProductDao(Database database) {
        this.database = database;
    }

    /**
     * Metodi hakee tuotteen tiedot tietokantataulusta
     *
     * @param   key   Tuotteen id tietokantataulussa
     *
     * @return palauttaa Tuote olion.
     *
     * @throws SQLException mikäli tapahtuu virhe
     *
     */

    @Override
    public Product findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Products WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet resultSet = stmt.executeQuery();
        boolean hasOne = resultSet.next();
        if (!hasOne) {
            resultSet.close();
            stmt.close();
            connection.close();
            return null;
        }

        Product product = new Product(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("inventory"), resultSet.getInt("price"));

        resultSet.close();
        stmt.close();
        connection.close();

        return product;
    }

    /**
     * Metodi hakee kaikki tuotteet tietokantataulusta
     *
     * @return palauttaa listan Product olioita.
     *
     * @throws SQLException mikäli tapahtuu virhe
     *
     */

    @Override
    public List<Product> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Products");

        ResultSet resultSet = stmt.executeQuery();
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Integer inventory = resultSet.getInt("inventory");
            Integer price = resultSet.getInt("price");

            products.add(new Product(id, name, inventory, price));
        }

        resultSet.close();
        stmt.close();
        connection.close();

        return products;
    }

    /**
     * Metodi poistaa tuotteen tietokantataulusta
     *
     * @param   key   Tuotteen id tietokantataulussa
     *
     * @throws SQLException mikäli tapahtuu virhe
     *
     */

    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Products WHERE id = (?)");
        stmt.setInt(1, key);

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    /**
     * Metodi hakee kaupan tiedot tietokantataulusta
     *
     * @param  product  Lisättävä Product olio
     *
     * @throws SQLException mikäli tapahtuu virhe
     *
     */

    public void add(Product product) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Products (name, inventory, price) VALUES (?, ?, ?)");
        stmt.setString(1, product.getName());
        stmt.setInt(2, product.getInventory());
        stmt.setInt(3, product.getPrice());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    /**
     * Metodi hakee tuotteen tiedot tietokantataulusta
     *
     * @param  product Product olio, jonka tiedoilla korvataan vanhat tiedot
     *
     * @throws SQLException mikäli tapahtuu virhe
     */

    public void update(Product product) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE Products SET name = ?, inventory = ?, price = ? WHERE id = ?");
        stmt.setString(1, product.getName());
        stmt.setInt(2, product.getInventory());
        stmt.setInt(3, product.getPrice());
        stmt.setInt(4, product.getId());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }
}