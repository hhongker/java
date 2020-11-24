package game;


public abstract class Skill implements Output {

    String name;

    public Skill(){}
    public Skill(String name){
        this.name = name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
