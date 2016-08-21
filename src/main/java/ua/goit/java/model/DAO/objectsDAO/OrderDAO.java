package ua.goit.java.model.DAO.objectsDAO;

import ua.goit.java.model.DAO.objects.Dish;
import ua.goit.java.model.DAO.objects.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDAO implements DAOOrder {

    private EmployeesDAO employeesDAO;
    private DishDAO dishDAO;

    private Map<Integer, Boolean> ordersStatus = new HashMap<>();
    ;

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


//

    @Override
    public void addOrder(Order order) throws SQLException {
        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement");

        PreparedStatement ps = connection.prepareStatement("insert into orders(order_id, id_waiter, dish_id, table_number, order_date) values (?, ?, ?, ?, ?)");
        ps.setInt(1, order.getId());
        ps.setInt(2, order.getEmployee().getId());
        ps.setInt(3, order.getDish().getDish_id());
        ps.setInt(4, order.getTableNumber());
        ps.setDate(5, Date.valueOf(order.getDate()));

        ps.execute();
        System.out.println("Record was added.");

        if (!ordersStatus.containsKey(order.getId())) {
            ordersStatus.put(order.getId(), true);
            System.out.println("Order was added to list ");
        }
    }



    @Override
    public Order getById(int id) throws SQLException {

        Order order = new Order();

        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Executing statement");
        PreparedStatement statement = connection.prepareStatement("select * from orders inner join employee on (id_waiter=employee_id) inner join dishes on (orders.dish_id=dishes.dish_id) where order_id=?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            order = createOrder(resultSet);
            System.out.println(order.toString());
        }
            return order;

    }

    @Override
    public void addDish(Dish dish, int order_Id) throws SQLException {

        System.out.println(ordersStatus.size());

        if (ordersStatus.get(order_Id)) {
            Order order = new Order();
            System.out.println("Creating DataBase Connection...");
            Connection connection = getConnection();
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            System.out.println("Executing statement");
            PreparedStatement statement = connection.prepareStatement("select * from orders where order_id= ?");
            statement.setInt(1, order_Id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                order = createOrder(resultSet);
                System.out.println(order.toString());
            }
            order.setDish(dish);

            addOrder(order);
        } else if (ordersStatus.get(order_Id) == null) {
            throw new RuntimeException("Can't get order status");
        }
    }
    @Override
    public void deleteDish(Dish dish, int orderId) throws SQLException {
        if(ordersStatus.get(orderId)) {

            System.out.println("Creating DataBase Connection...");
            Connection connection = getConnection();
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            System.out.println("Executing statement");

            PreparedStatement ps = connection.prepareStatement("DELETE FROM orders WHERE order_ID = ? AND dish_id = ?");

                ps.setInt(1, orderId);
                ps.setInt(2, dish.getDish_id());
                ps.execute();

        } else if (ordersStatus.get(orderId) == null) {
            System.out.println("Can't get order status for order with id: " + orderId);
            throw new RuntimeException("Can't get order status");
        } else {
            System.out.println("Order with id " + orderId + " is closed");
        }
    }

    @Override
    public void deleteOrderById(int id) throws SQLException {
        if (ordersStatus.get(id)) {
            System.out.println("Creating DataBase Connection...");
            Connection connection = getConnection();
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            System.out.println("Executing statement");

            PreparedStatement ps = connection.prepareStatement("DELETE FROM orders WHERE ORDER_ID = ?");
                ps.setInt(1, id);
                ps.execute();

        } else if (ordersStatus.get(id) == null) {
            System.out.println("Can't get order status for order with id: " + id);
            throw new RuntimeException("Can't get order status");
        } else {
            System.out.println("Order with id " + id + " is closed");
        }
    }

    @Override
    public void closeOrder(int id) {
        ordersStatus.put(id, false);
    }

    @Override
    public List<Order> findAllOpenOrders() throws SQLException {
        List<Order> allOrders = findAll();
        List<Order> result = new ArrayList<>();

        for (Order order: allOrders) {
            if (ordersStatus.get(order.getId())) {
                result.add(order);
            }
        }

        return result;
    }

    @Override
    public List<Order> findAllClosedOrders() throws SQLException {
        List<Order> allOrders = findAll();
        List<Order> result = new ArrayList<>();

        for (Order order: allOrders) {
            if (!ordersStatus.get(order.getId())) {
                result.add(order);
            }
        }

        return result;
    }
    private List<Order> findAll() throws SQLException {
        List<Order> result = new ArrayList<>();

        System.out.println("Creating DataBase Connection...");
        Connection connection = getConnection();
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement");

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders");
        ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = createOrder(rs);
                result.add(order);
            }

        return result;
    }

    public EmployeesDAO getEmployeesDAO() {
        return employeesDAO;
    }

    public void setEmployeesDAO(EmployeesDAO employeesDAO) {
        this.employeesDAO = employeesDAO;
    }

    public DishDAO getDishDAO() {
        return dishDAO;
    }

    public void setDishDAO(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    private Order createOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("order_id"));
        order.setEmployee(employeesDAO.getById(rs.getInt("employee_id")));
        order.setDish(dishDAO.findDishByName(rs.getString("dish_name")));
        order.setTableNumber(rs.getInt("TABLE_NUMBER"));
        order.setDate(rs.getDate("ORDER_DATE").toLocalDate());

        return order;
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }
}
