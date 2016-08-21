package ua.goit.java.model;

import java.sql.SQLException;

public class Main {
    {
        System.out.println("Registering DRIVER..");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        EmployeesDAO employeesDAO = new EmployeesDAO();
//                employeesDAO.add(11, "filo","sga", new Date(2015-01-01), 651311, 1, 65131);
////              employeesDAO.remove("filo");
//                employeesDAO.findEmployeeByName("Petro");
//                employeesDAO.showAll();
//                employeesDAO.getById(11);
//        DishDAO dishDAO = new DishDAO();
//        dishDAO.add(9,"Pepsi", 5, 0.5, 2);
//        dishDAO.remove("Pepsi");
//        dishDAO.findDishByName("Compote");
//        dishDAO.showAll();
//        dishDAO.getById(1);
//              MenuDAO menuDAO = new MenuDAO();
//              menuDAO.addMenu(4, "Banket");
//              menuDAO.remove("Banket");
//              menuDAO.addDishToMenu("Lunch", "Pie");
//              menuDAO.removeDishFromMenu("Lunch", "Pie");
//              menuDAO.findMenuByName("Child_menu");
//              menuDAO.showAll();
//        WarehouseDAO warehouseDAO = new WarehouseDAO();
//        warehouseDAO.add(4, 4, 15, 2);
//        warehouseDAO.remove(4);
//        warehouseDAO.updateQuantityByName("Oil", 50);
//        warehouseDAO.findIngredientByName("water");
//        warehouseDAO.showAll();
//        warehouseDAO.showAllLessThan(20);
//                OrderDAO orderDAO = new OrderDAO();
//                EmployeesDAO employeesDAO = new EmployeesDAO();
//                DishDAO dishDAO = new DishDAO();
//                LocalDate localDate = LocalDate.now();
//                Order order = new Order();
//                order.setId(5);
//                order.setEmployee(employeesDAO.getById(1));
//                order.setDish(dishDAO.getById(1));
//                order.setDate(localDate.now());
//                order.setTableNumber(18);
////
//                orderDAO.addOrder(order);
//////
////
//                Dish dish = new Dish();
//                dish.setDish_id(10);
//                dish.setName("Gorilka");
//                dish.setPrice(100);
//                dish.setWeight(0.5);
//                dish.setMeasure(2);
//        System.out.println(dish.toString());
//                orderDAO.addDish(dish, 5);

//        orderDAO.deleteOrderById(5);
//        orderDAO.deleteDish(dish,5);
//        orderDAO.findAllOpenOrders();
//        orderDAO.findAllClosedOrders();


    }

}
