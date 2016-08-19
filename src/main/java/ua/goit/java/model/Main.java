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
//                employeesDAO.remove("filo");
//                employeesDAO.findEmployeeByName("Petro");
//                employeesDAO.showAll();
//        DishDAO dishDAO = new DishDAO();
//        dishDAO.add(9,"Pepsi", 5, 0.5, 2);
//        dishDAO.remove("Pepsi");
//        dishDAO.findDishByName("Compote");
//        dishDAO.showAll();
//          MenuDAO menuDAO = new MenuDAO();
//          menuDAO.addMenu(4, "Banket");
//          menuDAO.remove("Banket");
//          menuDAO.findMenuByName("Child_menu");
//          menuDAO.showAll();
//        WarehouseDAO warehouseDAO = new WarehouseDAO();
//        warehouseDAO.add(4, 4, 15, 2);
//        warehouseDAO.remove(4);
//        warehouseDAO.updateQuantityByName("Oil", 50);
//        warehouseDAO.findIngredientByName("water");
//        warehouseDAO.showAll();
//        warehouseDAO.showAllLessThan(20);

    }

}
