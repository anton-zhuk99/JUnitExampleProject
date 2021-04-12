package org.example.sandbox;

import java.sql.*;

public class Main {

    private static Connection getNewConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String login = "SYS as SYSDBA"; // login + " as " + role
        String password = "admin";
        return DriverManager.getConnection(url, login, password);
    }

    // JDBC API
    // Java EE - Enterprise Edition
    public static void main(String[] args) throws SQLException {
        // STEP 1 создать подключение
        Connection connection = getNewConnection();

        // STEP 2 запрос
        String sql = "SELECT full_name, department FROM my_worker WHERE salary > ?";
        // SELECT * FROM my_worker

        // STEP 3 обернуть запрос в понятный для JDBC объект
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, 900.0);

        // STEP 4 выполнить запрос
        boolean hasResult = preparedStatement.execute();
        System.out.println("Has Result: " + hasResult);

        // STEP 5 разбор результата
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            String fullName = resultSet.getString("full_name");
            String department = resultSet.getString("department");

            System.out.println("fullName: " + fullName);
            System.out.println("department: " + department);
        }

        // STEP 6 закрыть ресурсы
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

}
