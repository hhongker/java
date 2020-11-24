package domain;

public class HeadTeacher {
    private Integer hid;
    private String hname;
    private String hsex;
    private Class class_;

    public HeadTeacher() {
    }

    public HeadTeacher(String hname, String hsex, Class class_) {
        this.hname = hname;
        this.hsex = hsex;
        this.class_ = class_;
    }

    public HeadTeacher(Integer hid, String hname, String hsex, Class class_) {
        this.hid = hid;
        this.hname = hname;
        this.hsex = hsex;
        this.class_ = class_;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getHsex() {
        return hsex;
    }

    public void setHsex(String hsex) {
        this.hsex = hsex;
    }

    public Class getClass_() {
        return class_;
    }

    public void setClass_(Class class_) {
        this.class_ = class_;
    }

    @Override
    public String toString() {
        return "HeadTeacher{" +
                "hid=" + hid +
                ", hname='" + hname + '\'' +
                ", hsex='" + hsex + '\'' +
                ", class_=" + class_ +
                '}';
    }
}
