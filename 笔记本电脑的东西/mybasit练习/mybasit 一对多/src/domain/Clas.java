package domain;

import java.util.List;

public class Clas {

    private int cid;
    private String cname;
    private List<Stu> listStu;

    public Clas() {
    }

    public Clas(String cname, List<Stu> listStu) {
        this.cname = cname;
        this.listStu = listStu;
    }

    public Clas(int cid, String cname, List<Stu> listStu) {
        this.cid = cid;
        this.cname = cname;
        this.listStu = listStu;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<Stu> getListStu() {
        return listStu;
    }

    public void setListStu(List<Stu> listStu) {
        this.listStu = listStu;
    }

    @Override
    public String toString() {
        return "Clas{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", listStu=" + listStu +
                '}';
    }
}
