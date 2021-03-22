package data;

import java.io.Serializable;

public class Data1 implements Serializable {
    private static final long serialVersionUID = -1L;
    private int id;
    private String name;

    public Data1() {
    }

    public Data1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Data1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
