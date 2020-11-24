package com.exp.bean;

/**
 * @author hhr
 * @date 2020-06-14 09:07
 * @description:
 */
public class People {

    private Integer id;
    private String name;
    private String birthday;
    private String createTime;

    public People() {
    }

    public People(Integer id, String name, String birthday, String createTime) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
