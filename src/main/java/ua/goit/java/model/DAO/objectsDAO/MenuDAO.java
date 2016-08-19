package ua.goit.java.model.DAO.objectsDAO;

import ua.goit.java.model.DAO.objects.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO implements DAOMenu{

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
    public void addMenu(int id, String menu_name) throws ClassNotFoundException, SQLException {
        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement");

        PreparedStatement ps = connection.prepareStatement("insert into menu values (?, ?)");
        ps.setInt(1, id);
        ps.setString(2, menu_name);

        ps.execute();
        System.out.println("Record was added.");
    }

    @Override
    public void remove(String name) throws SQLException {
        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement");

        PreparedStatement ps = connection.prepareStatement("DELETE FROM menu WHERE menu_name=?");
        ps.setString(1, name);
        ps.execute();
        System.out.println("Menu " + name + " was removed from table");
    }

    @Override
    public void addDishToMenu(String menu_name, String dish_name) throws SQLException {
        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Executing statement");
        PreparedStatement ps = connection.prepareStatement("INSERT INTO menu_set VALUES ((SELECT menu.id FROM menu WHERE menu_name = ?), (SELECT dishes.id FROM dishes WHERE dish_name = ?))");
        ps.setString(1, menu_name);
        ps.setString(2, dish_name);
        ps.execute();
        System.out.println("Dish " + dish_name + " was added to menu " + menu_name);

    }

    @Override
    public void removeDishFromMenu(String menu_name, String dish_name) throws SQLException {
        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Executing statement");
        PreparedStatement ps = connection.prepareStatement("DELETE FROM menu_set where id_menu = (SELECT menu.id FROM menu WHERE menu_name = ?) and id_dish =(SELECT dishes.id FROM dishes WHERE dish_name = ?)");
        ps.setString(1, menu_name);
        ps.setString(2, dish_name);
        ps.execute();
        System.out.println("Dish " + dish_name + " was removed from menu " + menu_name);
    }

    @Override
    public Menu findMenuByName(String name) throws SQLException {
        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Executing statement");
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM menu WHERE menu_name = ? ");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Menu menu = createMenu(rs);
            System.out.println(menu.toString());
            return menu;
        }else {
            throw new RuntimeException("Can't find class " + name);
        }

    }

    @Override
    public List<Menu> showAll() throws SQLException {
        List<Menu> allMenus = new ArrayList<>();

        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Executing statement");
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM menu");

        while (rs.next()){

            Menu menu = createMenu(rs);
            allMenus.add(menu);
            System.out.println(menu.toString());
        }
        return allMenus;
    }

    private Menu createMenu(ResultSet rs) throws SQLException {
        Menu menu = new Menu();
        menu.setId(rs.getInt("ID"));
        menu.setMenu_name(rs.getString("MENU_NAME"));

        return menu;
    }



    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }
}
