package domain;

import java.util.List;

public class Course {
    private Integer coid;
    private String coname;

    private List<Student> list;

    public Course() {
    }

    public Course(String coname, List<Student> list) {
        this.coname = coname;
        this.list = list;
    }

    public Course(Integer coid, String coname, List<Student> list) {
        this.coid = coid;
        this.coname = coname;
        this.list = list;
    }

    public Integer getCoid() {
        return coid;
    }

    public void setCoid(Integer coid) {
        this.coid = coid;
    }

    public String getConame() {
        return coname;
    }

    public void setConame(String coname) {
        this.coname = coname;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Course{" +
                "coid=" + coid +
                ", coname='" + coname + '\'' +
                ", list=" + list +
                '}';
    }
}
