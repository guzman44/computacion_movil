package co.movil.computacion.model;

public class Category {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category(String name, int id) {
        this.name = name;
        this.id = id;
    }

    private String name;
    private int id;
    @Override
    public String toString() {
        return name;
    }
}
