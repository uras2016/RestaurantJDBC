package ua.goit.java.model.DAO.objectsDAO;

import ua.goit.java.model.DAO.objects.PreparedDish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreparedDishDAO implements DAOPreparedDish{

    private EmployeesDAO employeeDao;
    private DishDAO dishDao;
    private OrderDAO orderDao;



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
    public List<PreparedDish> findAll() throws SQLException {
        List<PreparedDish> result = new ArrayList<>();

        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Executing statement");
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM prepared_dishes");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            PreparedDish preparedDish = createPreparedDish(resultSet);
            result.add(preparedDish);
        }
        return result;
    }

    @Override
    public void addPreparedDish(PreparedDish preparedDish) throws SQLException {

        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Executing statement");
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Prepared_dishes (ID, dish_number, DATE, employee_id, dish_id, order_id)" +
                "VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, preparedDish.getId());
            ps.setInt(2, preparedDish.getDishNumber());
            ps.setDate(3, Date.valueOf(preparedDish.getDate()));
            ps.setInt(4, preparedDish.getEmployee().getId());
            ps.setInt(5, preparedDish.getDish().getDish_id());
            ps.setInt(6, preparedDish.getOrder().getId());

            ps.execute();


    }
    private PreparedDish createPreparedDish(ResultSet resultSet) throws SQLException {
        PreparedDish preparedDish = new PreparedDish();
        preparedDish.setId(resultSet.getInt("ID"));
        preparedDish.setEmployee(employeeDao.getById(resultSet.getInt("EMPLOYEE_ID")));
        preparedDish.setDish(dishDao.getById(resultSet.getInt("DISH_ID")));
        preparedDish.setOrder(orderDao.getById(resultSet.getInt("ORDER_ID")));
        preparedDish.setDishNumber(resultSet.getInt("DISH_NUMBER"));
        preparedDish.setDate(resultSet.getDate("DATE").toLocalDate());
        return preparedDish;
    }


    public void setEmployeeDao(EmployeesDAO employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setDishDao(DishDAO dishDao) {
        this.dishDao = dishDao;
    }

    public void setOrderDao(OrderDAO orderDao) {
        this.orderDao = orderDao;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }
}
