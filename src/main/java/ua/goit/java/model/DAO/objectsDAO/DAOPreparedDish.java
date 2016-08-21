package ua.goit.java.model.DAO.objectsDAO;

import ua.goit.java.model.DAO.objects.PreparedDish;

import java.sql.SQLException;
import java.util.List;

public interface DAOPreparedDish {
    List<PreparedDish> findAll() throws SQLException;
    void addPreparedDish(PreparedDish preparedDish) throws SQLException;
}
