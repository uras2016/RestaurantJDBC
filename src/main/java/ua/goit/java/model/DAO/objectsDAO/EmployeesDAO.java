package ua.goit.java.model.DAO.objectsDAO;

import ua.goit.java.model.DAO.objects.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDAO implements DAOEmployee {

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


    public void add(int id, String second_name, String name, Date birthday, int telephone, int position_id, int salary) throws ClassNotFoundException, SQLException {

        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement");

        PreparedStatement ps = connection.prepareStatement("insert into employee values (?, ?, ?, ?, ?, ?, ?)");
        ps.setInt(1, id);
        ps.setString(2, second_name);
        ps.setString(3, name);
        ps.setDate(4, birthday);
        ps.setInt(5, telephone);
        ps.setInt(6, position_id);
        ps.setInt(7, salary);
        ps.execute();
        System.out.println("Record was added.");
    }

    public void remove(String second_name) throws SQLException {
        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement");

        PreparedStatement ps = connection.prepareStatement("DELETE FROM employee WHERE second_name=?");
        ps.setString(1, second_name);
        ps.execute();
        System.out.println("Employee " + second_name + " was removed from table");
    }

    public Employee findEmployeeByName(String name) throws SQLException {
        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Executing statement");
        PreparedStatement ps = connection.prepareStatement("SELECT second_name, name, birthday, telephone, salary, positions.position_name FROM employee NATURAL JOIN positions WHERE name = ? ");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Employee employee = createEmployee(rs);
            System.out.println(employee.toString());
            return employee;
        }else {
            throw new RuntimeException("Can't find ua.goit.java.jdbc.model.Employee with name " + name);
        }

    }

    public Employee getById(int id) throws SQLException {

        Employee employee = new Employee();

        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Executing statement");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee NATURAL JOIN positions WHERE EMPLOYEE_ID = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            employee = createEmployee(resultSet);
            System.out.println(employee.toString());
            return employee;
        } else {
            throw new RuntimeException("Cannot find employee with id: " + id);
        }
    }

    public List<Employee> showAll() throws SQLException {
        List<Employee> allEmplyees = new ArrayList<>();

        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Executing statement");
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM employee NATURAL JOIN positions");

        while (rs.next()){

            Employee employee = createEmployee(rs);
            allEmplyees.add(employee);
            System.out.println(employee.toString());
        }
        return allEmplyees;
    }

    private Employee createEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt("employee_id"));
        employee.setSecond_name(rs.getString("SECOND_NAME"));
        employee.setName(rs.getString("NAME"));
        employee.setBirthday(rs.getString("BIRTHDAY"));
        employee.setTelephone(rs.getInt("TELEPHONE"));
        employee.setPosition(rs.getString("POSITION_NAME"));
        employee.setSalary(rs.getInt("SALARY"));

        return employee;
    }



    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }

}
