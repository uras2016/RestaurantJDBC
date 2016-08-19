/*
package ua.goit.java;

import java.sql.*;

public class JDBCDemo {
    */
/**
     * JDBC Driver and database url
     *//*

    static final String JDBC_Driver = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/RESTAURANT";
    */
/**
     * User and Password
     *//*

    static final String USER = "user";
    static final String PASSWORD = "111";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        System.out.println("Registering DRIVER..");

        Class.forName("org.postgresql.Driver");   // loading Driver

        System.out.println("Creating DataBase Connection...");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement");

        statement = connection.createStatement();

        String query = "SELECT * FROM EMPLOYEE ORDER BY id";

        ResultSet resultSet = statement.executeQuery(query);

        System.out.println("Retrieving data from database...");
        System.out.println("\nEmployees:");


        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int salary = resultSet.getInt("salary");

            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Salary: $" + salary);
        }


        System.out.println("Closing connection and releasing resources...");
        resultSet.close();
        statement.close();
        connection.close();

    }

}
*/
