package ua.goit.java.model.DAO.objectsDAO;

import ua.goit.java.model.DAO.objects.Menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DAOMenu {
    void addMenu(int id, String menu_name) throws ClassNotFoundException, SQLException;
    void remove(String name) throws SQLException;
    void addDishToMenu (String dish_name, String menu_name);

    Menu findMenuByName(String name) throws SQLException;
    List<Menu> showAll() throws SQLException;
    Connection getConnection() throws SQLException;

}
