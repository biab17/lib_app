package databaseLinkTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;
import databaseLink.*;
import static org.junit.jupiter.api.Assertions.*;

public class AuthorDBTest {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3307/library_schema_bun"; // Assuming port 3307 as you mentioned port conflict
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "bubu18";

    private static AuthorDB authorDB;

    @BeforeAll
    public static void setUp() throws SQLException {
        authorDB = new AuthorDB();
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            String deleteQuery = "DELETE FROM authors WHERE name = 'Test Author'";
            statement.executeUpdate(deleteQuery);
        } finally {
            authorDB.closeConnection();
        }
    }

    @Test
    public void testInsertAuthor() {
        try {
            insertTestAuthor();
            Author retrievedAuthor = retrieveTestAuthor();

        } catch (SQLException e) {
            fail("Insertion or retrieval should not throw an exception: " + e.getMessage());
        }
    }

    private void insertTestAuthor() throws SQLException {
        String insertQuery = "INSERT INTO authors () VALUES ()";
        try (PreparedStatement preparedStatement = authorDB.getConnection().prepareStatement(insertQuery)) {
            preparedStatement.executeUpdate();
        }
    }

    private Author retrieveTestAuthor() throws SQLException {
        Author author = null;
        String query = "SELECT * FROM authors WHERE name = 'Test Author'";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                author = new Author();
            }
        }
        return author;
    }

    // Mock Author class for testing purposes
    static class Author {


        public Author() {

        }


    }
}
