/*
package ua.goit.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDAO {

    */
/**
     * JDBC Driver and database url
     *//*

    static final String JDBC_Driver = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/Restaurant";
    */
/**
     * User and Password
     *//*

    static final String USER = "user";
    static final String PASSWORD = "111";
    Connection connection = null;
    Statement statement = null;

    public void addEmployee() throws ClassNotFoundException, SQLException {
        System.out.println("Registering DRIVER..");

        Class.forName("org.postgresql.Driver");   // loading Driver

        System.out.println("Creating DataBase Connection...");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement");

        statement = connection.createStatement();

        String query = "insert into employee values (10, 'Boroda', 'Dudo', '15-08-2016', +380661235, 555, 800)";
        statement.executeUpdate(query);
        System.out.println("Record was added");
        statement.close();
        connection.close();
    }
}
*/
