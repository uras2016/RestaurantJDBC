package ua.goit.java.model.DAO.objectsDAO;

import ua.goit.java.model.DAO.objects.Employee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface DAOEmployee {

    void add(int id, String second_name, String name, Date birthday, int telephone, int position_id, int salary) throws ClassNotFoundException, SQLException;
    void remove(String second_name) throws SQLException;
    Employee findEmployeeByName(String name) throws SQLException;
    List<Employee> showAll() throws SQLException;
    Connection getConnection() throws SQLException;

    public Employee getById(int id) throws SQLException;



}
