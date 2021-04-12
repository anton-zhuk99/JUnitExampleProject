package org.example.sandbox;

import oracle.jdbc.pool.OracleDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

// JDBC API
public class OracleDbTest {

    // Connection => Statement

    // Connection +
    // Statement
    // |- Statement +
    // |- PreparedStatement +
    // |- CallableStatement

    private static Connection connection;

    @BeforeEach
    public void init() {
        try {
            connection = getNewConnection();
        } catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    private Connection getNewConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String login = "SYS as SYSDBA"; // login + " as " + role
        String password = "admin";
        return DriverManager.getConnection(url, login, password);
    }

    @Test
    @Disabled
    public void testConnection() {
        try (Connection connection = getNewConnection()) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testStatements() {
        try {
            Statement statement = connection.createStatement();
            String sql =
                    "INSERT INTO TODOITEMS(subject, message, created_on) " +
                    "VALUES('Simple subject', 'Wash dishes', '8-APR-2021')";
            // INSERT, UPDATE, DELETE - executeUpdate()
            // SELECT, CREATE, ALTER, DROP - executeQuery()
            int result = statement.executeUpdate(sql);
            System.out.println("Result of the query: " + result);
        } catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
    // java.util.Date
    // java.sql.Date
    @Test
    public void testPreparedStatement() {
        try {
            String sql =
                    "INSERT INTO TODOITEMS(subject, message, created_on) " +
                    "VALUES(?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Simple subject 2");
            statement.setString(2, "Wash dishes again.");
            statement.setDate(3, new Date(System.currentTimeMillis()));
            boolean result = statement.execute();
            //assertTrue(result, "Statement failed");
        } catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

}
