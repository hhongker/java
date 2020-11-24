package game;

public class WeightAttack extends Skill{

    public WeightAttack() {
        super("重击");
    }
    public WeightAttack(String name) {
        super("重击");
        this.name = name;
    }

    @Override
    public int hurt(Player p1) {
        return (int) (Math.random() * (p1.getMaxattack() - p1.getMinattack())*1.5);
    }
}
