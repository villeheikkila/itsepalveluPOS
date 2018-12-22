package itsepalvelupos.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class DatabaseTest {

    private String databaseName;
    private Database database;

    @Before
    public void setUp() {
        databaseName = "testDatabase.db";
        database = new Database(databaseName);
        database.initDatabase();

    }

    @After
    public void removeDB() {
        File file = new File(databaseName);
        file.delete();
    }

    @Test
    public void getConnection() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Store");
        ResultSet resultSet = stmt.executeQuery();
        connection.close();
        assertNotNull(resultSet);
    }

    @Test
    public void initDatabase() {
        File file = new File(databaseName);
        assertTrue(file.exists());
    }

    @Test
    public void removeDatabase()  {
        database.removeDatabase();
        File file = new File(databaseName);
        assertFalse(file.exists());
    }
}