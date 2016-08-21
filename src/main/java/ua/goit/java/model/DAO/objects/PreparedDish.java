package ua.goit.java.model.DAO.objects;

import java.time.LocalDate;

public class PreparedDish {
    private int id;
    private Employee employee;
    private Dish dish;
    private Order order;
    private int dishNumber;
    private LocalDate date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getDishNumber() {
        return dishNumber;
    }

    public void setDishNumber(int dishNumber) {
        this.dishNumber = dishNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PreparedDish{" +
                "id=" + id +
                ", employee=" + employee +
                ", dish=" + dish +
                ", order=" + order +
                ", dishNumber=" + dishNumber +
                ", date=" + date +
                '}';
    }
}
