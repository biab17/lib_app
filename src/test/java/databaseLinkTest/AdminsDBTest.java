package databaseLinkTest;

import org.junit.jupiter.api.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import databaseLink.*;

public class AdminsDBTest {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/library_schema_bun";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "bubu18";

    private static AdminsDB adminsDB;
    private static Connection connection;

    @BeforeAll
    public static void setUp() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        adminsDB = new AdminsDB();
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    public void testInsertAdmin() {
        assertDoesNotThrow(() -> adminsDB.insertAdmin(), "Insert should not throw an exception.");

        // You can add more assertions here to verify the insertion if needed
    }
}
