package ua.goit.java.model.DAO.objectsDAO;

import ua.goit.java.model.DAO.objects.Dish;
import ua.goit.java.model.DAO.objects.Order;

import java.sql.SQLException;
import java.util.List;

public interface DAOOrder {
//    void addOrder(int id, int id_waiter, int dish_id, int table_number, Date order_date) throws SQLException;
    void addOrder(Order order) throws SQLException;
    Order getById(int id) throws SQLException;
    void addDish(Dish dish, int orderId) throws SQLException;
    void deleteDish(Dish dish, int orderId) throws SQLException;
    void deleteOrderById(int id) throws SQLException;
    void closeOrder(int id);
    List<Order> findAllOpenOrders() throws SQLException;
    List<Order> findAllClosedOrders() throws SQLException;
}
