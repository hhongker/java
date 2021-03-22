package data;

import javafx.scene.chart.PieChart;

import java.io.DataInputStream;
import java.io.Serializable;
import java.text.Annotation;

public class Data1 extends Annotation implements Serializable {
    private static final long serialVersionUID = -1L;
    private int id;
    private String name;
    public int idd;

    public Data1() {
        super(1);
    }

    public Data1(int id, String name) {
        super(1);
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
