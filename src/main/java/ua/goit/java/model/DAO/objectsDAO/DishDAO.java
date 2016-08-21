package ua.goit.java.model.DAO.objectsDAO;

import ua.goit.java.model.DAO.objects.Dish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishDAO implements DAODish {

    /**
     * JDBC Driver and database url
     */
    final String JDBC_Driver = "org.postgresql.Driver";
    final String DATABASE_URL = "jdbc:postgresql://localhost:5432/RESTAURANT";
    /**
     * User and Password
     */
    static final String USER = "user";
    static final String PASSWORD = "111";
//    Connection connection = null;
//    Statement statement = null;

    public void add(int id, String name, double price, double weight, int measure) throws ClassNotFoundException, SQLException {

        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement");

        PreparedStatement ps = connection.prepareStatement("insert into dishes values (?, ?, ?, ?, ?)");
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setDouble(3, price);
        ps.setDouble(4, weight);
        ps.setInt(5, measure);

        ps.execute();
        System.out.println("Record was added.");
    }

    @Override
    public void remove(String name) throws SQLException {
        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement");

        PreparedStatement ps = connection.prepareStatement("DELETE FROM dishes WHERE dish_name=?");
        ps.setString(1, name);
        ps.execute();
        System.out.println("Dish " + name + " was removed from table");
    }

    @Override
    public Dish findDishByName(String name) throws SQLException {
        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Executing statement");
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM dishes INNER JOIN measures ON(dishes.measure_id=measures.measure_id) WHERE dish_name = ? ");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Dish dish = createDish(rs);
            System.out.println(dish.toString());
            return dish;
        }else {
            throw new RuntimeException("Can't find class " + name);
        }

    }


    public Dish getById(int id) throws SQLException {

        Dish dish = new Dish();

        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Executing statement");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM dishes NATURAL JOIN measures WHERE DISH_ID = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            dish = createDish(resultSet);
            return dish;
        } else {
            throw new RuntimeException("Cannot find dish with id: " + id);
        }
    }

    @Override
    public List<Dish> showAll() throws SQLException {
        List<Dish> allDishes = new ArrayList<>();

        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Executing statement");
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM dishes NATURAL JOIN measures");

        while (rs.next()){

            Dish dish = createDish(rs);
            allDishes.add(dish);
            System.out.println(dish.toString());
        }
        return allDishes;
    }

    private Dish createDish(ResultSet rs) throws SQLException {
        Dish dish = new Dish();
        dish.setDish_id(rs.getInt("DISH_ID"));
        dish.setName(rs.getString("DISH_NAME"));
        dish.setPrice(rs.getDouble("DISH_PRICE"));
        dish.setWeight(rs.getDouble("DISH_WEIGHT"));
        dish.setMeasure(rs.getInt("MEASURE_ID"));

        return dish;
    }



    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }
}
