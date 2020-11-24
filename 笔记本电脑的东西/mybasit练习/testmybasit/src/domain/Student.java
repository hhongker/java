package domain;

import sun.plugin.dom.core.CoreConstants;

import java.util.List;

public class Student {
    private Integer sid;
    private String sname;
    private String ssex;
    private Class class_;

    private List<Course> list;

    public Student() {
    }

    public Student(String sname, String ssex, Class class_, List<Course> list) {
        this.sname = sname;
        this.ssex = ssex;
        this.class_ = class_;
        this.list = list;
    }

    public Student(Integer sid, String sname, String ssex, Class class_, List<Course> list) {
        this.sid = sid;
        this.sname = sname;
        this.ssex = ssex;
        this.class_ = class_;
        this.list = list;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public Class getClass_() {
        return class_;
    }

    public void setClass_(Class class_) {
        this.class_ = class_;
    }

    public List<Course> getList() {
        return list;
    }

    public void setList(List<Course> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                ", class_=" + class_ +
                ", list=" + list +
                '}';
    }
}
