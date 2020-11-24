package game;

import java.nio.channels.SelectionKey;
import java.util.ArrayList;

public class Player {
    private String name;
    private int life;
    private int minattack;
    private int maxattack;
    private int define;
    private ArrayList<Skill> arraySkill;


    public Player() {
    }

    public Player(String name, int life, int minattack, int maxattack, int define) {
        this.name = name;
        this.life = life;
        this.minattack = minattack;
        this.maxattack = maxattack;
        this.define = define;
        this.arraySkill = new ArrayList<>();
        this.leanSkill(new NormolSkill());
    }

    public void attack(Player p){
        Skill skill = this.arraySkill.get((int)(this.arraySkill.size()*Math.random()));
        int hurt = skill.hurt(this);
        p.setLife(p.getLife()-hurt);
        System.out.println(this.name+"life:"+this.getLife()+","+this.name+"对"+p.name+"使用了"+skill.name+
                ",造成"+hurt+"点伤害,"+p.name+"life:"+p.getLife());
    }
    public boolean playerOver(Player p){
        if(p.getLife() <= 0){
            System.out.println(p.name+" over");
            return true;
        }
        return false;
    }


    public void leanSkill(Skill skill){
        this.arraySkill.add(skill);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getMinattack() {
        return minattack;
    }

    public void setMinattack(int minattack) {
        this.minattack = minattack;
    }

    public int getMaxattack() {
        return maxattack;
    }

    public void setMaxattack(int maxattack) {
        this.maxattack = maxattack;
    }

    public int getDefine() {
        return define;
    }

    public void setDefine(int define) {
        this.define = define;
    }


}
