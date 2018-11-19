package itsepalvelupos.database;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private String databaseName;

    public Database(String databaseName) throws ClassNotFoundException {
        this.databaseName = databaseName;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + databaseName);
    }

    public void databaseInit() {
        File file = new File(databaseName);
        if (file.exists()) {
            System.out.println("Error: Database already exists");
        } else {
            List<String> statements = Statements();
            try (Connection conn = getConnection()) {
                Statement stmt = conn.createStatement();

                for (String statement : statements) {
                    System.out.println("Running command: " + statement);
                    stmt.executeUpdate(statement);
                }

            } catch (Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        }
    }

    private List<String> Statements() {
        ArrayList<String> db = new ArrayList<>();

        db.add("CREATE TABLE Products (id integer PRIMARY KEY, name varchar(100), inventory INT, price INT);");
        db.add("CREATE TABLE Accounts (id integer PRIMARY KEY, username varchar(100), password varchar(20), admin boolean);");

        return db;
    }
}