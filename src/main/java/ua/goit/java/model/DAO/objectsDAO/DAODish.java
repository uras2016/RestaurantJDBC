package ua.goit.java.model.DAO.objectsDAO;

import ua.goit.java.model.DAO.objects.Dish;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DAODish  {

    void add(int id, String name, double price, double weight, int measure) throws ClassNotFoundException, SQLException;
    void remove(String name) throws SQLException;
    Dish findDishByName(String name) throws SQLException;
    List<Dish> showAll() throws SQLException;
    Connection getConnection() throws SQLException;

    public Dish getById(int id) throws SQLException;
}
