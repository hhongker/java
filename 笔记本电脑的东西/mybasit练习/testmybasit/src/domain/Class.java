package domain;

import java.util.List;

public class Class {

    private Integer cid;
    private String cname;
    private Integer gid;
    private List<Student> students;

    private HeadTeacher headTeacher;

    public Class() {
    }

    public Class(String cname, Integer gid, List<Student> students, HeadTeacher headTeacher) {
        this.cname = cname;
        this.gid = gid;
        this.students = students;
        this.headTeacher = headTeacher;
    }

    public Class(Integer cid, String cname, Integer gid, List<Student> students, HeadTeacher headTeacher) {
        this.cid = cid;
        this.cname = cname;
        this.gid = gid;
        this.students = students;
        this.headTeacher = headTeacher;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getSname() {
        return cname;
    }

    public void setSname(String sname) {
        this.cname = sname;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public HeadTeacher getHeadTeacher() {
        return headTeacher;
    }

    public void setHeadTeacher(HeadTeacher headTeacher) {
        this.headTeacher = headTeacher;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Class{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", gid=" + gid +
                ", students=" + students +
                ", headTeacher=" + headTeacher +
                '}';
    }
}
