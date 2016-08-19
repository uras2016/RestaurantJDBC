package ua.goit.java.model.DAO.objects;

public class WarehousePosition {

    private String name;
    private int quantity;
    private String measure;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", measure=" + measure +
                '}';
    }



    /*private String ingredients_name;
    private int quantity;
    private String measure_name;


    public String getIngredients_name() {
        return ingredients_name;
    }

    public void setIngredients_name(String ingredients_name) {
        this.ingredients_name = ingredients_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasure_name() {
        return measure_name;
    }

    public void setMeasure_name(String measure_name) {
        this.measure_name = measure_name;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "ingredients_name='" + ingredients_name + '\'' +
                ", quantity=" + quantity +
                ", measure_name='" + measure_name + '\'' +
                '}';
    }*/
}
