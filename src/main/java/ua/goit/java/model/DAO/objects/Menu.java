package ua.goit.java.model.DAO.objects;

public class Menu {
    private int id;
    private String menu_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menu_name='" + menu_name + '\'' +
                '}';
    }
}
