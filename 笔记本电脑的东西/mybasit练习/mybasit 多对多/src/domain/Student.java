package domain;

import java.util.List;

public class Student {

    private int sid;
    private String sname;

    private List<Teacher> teachers;

    public Student() {
    }

    public Student(String sname, List<Teacher> teachers) {
        this.sname = sname;
        this.teachers = teachers;
    }

    public Student(int sid, String sname, List<Teacher> teachers) {
        this.sid = sid;
        this.sname = sname;
        this.teachers = teachers;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", teachers=" + teachers +
                '}';
    }
}
