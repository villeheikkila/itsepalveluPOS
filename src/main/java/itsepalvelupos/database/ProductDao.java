package itsepalvelupos.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import itsepalvelupos.domain.Product;


public class ProductDao implements Dao<Product, Integer> {

    private Database database;

    public ProductDao(Database database) {
        this.database = database;
    }

    @Override
    public Product findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Products WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet resultSet = stmt.executeQuery();
        boolean hasOne = resultSet.next();
        if (!hasOne) {
            return null;
        }

        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Integer inventory = resultSet.getInt("inventory");
        Integer price = resultSet.getInt("price");

        Product product = new Product(id, name, inventory, price);

        resultSet.close();
        stmt.close();
        connection.close();

        return product;
    }

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

    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Products WHERE id = (?)");
        stmt.setInt(1, key);

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

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
}