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
    // ResultSet

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

    // ORM - object-relation mapping
    // Hibernate
    @Test
    @Disabled
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
    @Disabled
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

    @Test
    @Disabled
    public void createTableExample() {
        try {
            String sql = "create table my_worker(\n" +
                    "    id NUMBER GENERATED AS IDENTITY PRIMARY KEY,\n" +
                    "    full_name VARCHAR(100) NOT NULL,\n" +
                    "    department VARCHAR(50) NOT NULL,\n" +
                    "    salary NUMBER(8,2) NOT NULL,\n" +
                    "    work_since DATE NOT NULL\n" +
                    ")";
            Statement statement = connection.createStatement();
            boolean result = statement.execute(sql);
            System.out.println(result);
        } catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testSelectRequest() {
        try {
            String sql = "SELECT full_name, department FROM my_worker WHERE salary > 900.00";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setDouble(1, 900.0);
//            boolean hasResult = preparedStatement.execute();
//            System.out.println(hasResult);
//            assertTrue(hasResult, "Result of the query is empty.");
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                System.out.println(
                                results.getString(2) +
                                ", " + results.getString(3));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}


























