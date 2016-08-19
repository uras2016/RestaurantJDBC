package ua.goit.java.model.DAO.objectsDAO;

import ua.goit.java.model.DAO.objects.Menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DAOMenu {
    void addMenu(int id, String menu_name) throws ClassNotFoundException, SQLException;
    void remove(String name) throws SQLException;
    void addDishToMenu (String menu_name, String dish_name) throws SQLException;
    void removeDishFromMenu (String menu_name, String dish_name) throws SQLException;
    Menu findMenuByName(String name) throws SQLException;
    List<Menu> showAll() throws SQLException;
    Connection getConnection() throws SQLException;

}
