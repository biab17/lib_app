package databaseLinkTest;

import databaseLink.*;
import User.*;

import Exceptions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserDBTest {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/library_schema_bun";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "bubu18";

    private static UserDB UserDB;

    @BeforeAll
    public static void setUp() throws SQLException
    {
        UserDB = new UserDB();
    }

    @AfterAll
    public static void tearDown() throws SQLException
    {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement())
        {
            String deleteQuery = "DELETE FROM users WHERE name = 'Test User'";
            statement.executeUpdate(deleteQuery);
        }
    }

    @Test
    public void testInsert()
    {
        User testUser = new User("Test User", "test@example.com","Test full name",  "1234567890");

        //assertDoesNotThrow(() ->testUser.insertUser(testUser), "Insert should not throw an exception.");

        try {
            User retrievedUser = retrieveTestUser();
            assertNotNull(retrievedUser, "user should have been inserted into the database.");
            assertEquals(testUser.getFull_name(), retrievedUser.getFull_name(), "Inserted hotel name should match.");
            assertEquals(testUser.getEmail(), retrievedUser.getEmail(), "Inserted hotel address should match.");
            assertEquals(testUser.getPhone_number(), retrievedUser.getPhone_number(), "Inserted hotel city should match.");
            assertEquals(testUser.getPassword(), retrievedUser.getPassword(), "Inserted hotel state should match.");
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private User retrieveTestUser() throws SQLException
    {
        User user = null;
        String query = "SELECT * FROM users WHERE full_name = 'Test User'";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                String fullName = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String phoneNumber = resultSet.getString("number");
                user = new User( email, password,fullName, phoneNumber);
            }
        }
        return user;
    }
}