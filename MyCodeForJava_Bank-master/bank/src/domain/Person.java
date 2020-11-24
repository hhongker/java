package domain;

public class Person {
    private String user;
    private String password;
    private Float money;

    public Person(){}
    public Person(String user, String password, Float money) {
        this.user = user;
        this.password = password;
        this.money = money;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }
}
