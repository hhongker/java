package game;

public class NormolSkill extends Skill{


    public NormolSkill() {
        super("普通攻击");
    }
    public NormolSkill(String name) {
        super("普通攻击");
        this.name = name;
    }


    public int hurt(Player p1) {
       return (int) (Math.random() * (p1.getMaxattack() - p1.getMinattack()));
    }
}
