package ua.goit.java.model.DAO.objectsDAO;

import ua.goit.java.model.DAO.objects.WarehousePosition;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DAOWarehouse {
    void add(int id, int ingredient_id, int quantity, int measure_id) throws ClassNotFoundException, SQLException;
    void remove(int ingredient_id) throws SQLException;
    void updateQuantityByName(String name, int newRemains) throws SQLException;
    WarehousePosition findIngredientByName(String name) throws SQLException;
    List<WarehousePosition> showAll() throws SQLException;
    List<WarehousePosition> showAllLessThan(int quantity) throws SQLException;

    Connection getConnection() throws SQLException;
}
