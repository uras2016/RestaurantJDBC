package ua.goit.java.model.DAO.objectsDAO;

import ua.goit.java.model.DAO.objects.Ingredient;
import ua.goit.java.model.DAO.objects.WarehousePosition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO implements DAOWarehouse {
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


    @Override
    public void add(int id, int ingredient_id, int quantity, int measure_id) throws ClassNotFoundException, SQLException {
        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement");

        PreparedStatement ps = connection.prepareStatement("insert into warehouse values (?, ?, ?, ?)");
        ps.setInt(1, id);
        ps.setInt(2, ingredient_id);
        ps.setInt(3, quantity);
        ps.setInt(4, measure_id);

        ps.execute();
        System.out.println("Record was added.");
    }

    @Override
    public void remove(int ingredient_id) throws SQLException {
        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement");

        PreparedStatement ps = connection.prepareStatement("DELETE FROM warehouse WHERE ingredient_id=?");
        ps.setInt(1, ingredient_id);
        ps.execute();
        System.out.println("Ingredient " + ingredient_id + " was removed from table");
    }

    @Override
    public void updateQuantityByName(String name, int newRemains) throws SQLException {
        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement");
        PreparedStatement ps = connection.prepareStatement("UPDATE warehouse SET quantity = ? WHERE ingredient_id =\n" +
                "  (SELECT ingredient_id FROM ingredients WHERE ingredients_name = ?)");
        ps.setInt(1, newRemains);
        ps.setString(2, name);
        ps.execute();
        System.out.println("Ingredient " + name + " left" + newRemains);
    }

    @Override
    public WarehousePosition findIngredientByName(String name) throws SQLException {
        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Executing statement");
        PreparedStatement ps = connection.prepareStatement("select ingredients_name, quantity, measure_name from warehouse natural join ingredients NATURAL JOIN measures WHERE warehouse.ingredient_id =\n" +
                "(SELECT ingredient_id FROM ingredients WHERE ingredients_name = ?)");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            WarehousePosition warehousePosition = createWarehousePosition(rs);
            System.out.println(warehousePosition.toString());
            return warehousePosition;
        } else {
            throw new RuntimeException("Can't find class " + name);
        }
    }

    @Override
    public List<WarehousePosition> showAll() throws SQLException {
        List<WarehousePosition> allWarehousePositions = new ArrayList<>();

        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Executing statement");
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery("select ingredients_name, quantity, measure_name from warehouse natural join ingredients NATURAL JOIN measures");

        while (rs.next()){

            WarehousePosition warehousePosition = createWarehousePosition(rs);
            allWarehousePositions.add(warehousePosition);
//            System.out.println(menu.toString());
        }
        allWarehousePositions.forEach(System.out::println);
        return allWarehousePositions;
    }

    @Override
    public List<WarehousePosition> showAllLessThan(int quantity) throws SQLException {
        List<WarehousePosition> allWarehousePositions = new ArrayList<>();

        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Executing statement");
//        Statement s = connection.createStatement();
        PreparedStatement ps = connection.prepareStatement("select ingredients_name, quantity, measure_name from warehouse natural join ingredients NATURAL JOIN measures WHERE quantity < ?");
        ps.setInt(1, quantity);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            WarehousePosition warehousePosition = createWarehousePosition(rs);
            allWarehousePositions.add(warehousePosition);
//            System.out.println(menu.toString());
        }
        allWarehousePositions.forEach(System.out::println);
        return allWarehousePositions;
    }

    private WarehousePosition createWarehousePosition(ResultSet rs) throws SQLException {
        WarehousePosition warehousePosition = new WarehousePosition();
        warehousePosition.setName(rs.getString("ingredients_name"));
        warehousePosition.setQuantity(rs.getInt("quantity"));
        warehousePosition.setMeasure(rs.getString("measure_name"));

        return warehousePosition;
    }

    private Ingredient createIngredient(ResultSet rs) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(rs.getInt("ID"));
        ingredient.setName(rs.getString("INGREDIENT_NAME"));

        return ingredient;
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }
}
