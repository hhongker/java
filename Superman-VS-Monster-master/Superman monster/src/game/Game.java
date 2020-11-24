package game;

public class Game {

    public void PK(Player p1, Player p2){
        while (true){
            p1.attack(p2);
            if (p1.playerOver(p2))
                return;
            p2.attack(p1);
            if (p2.playerOver(p1))
                return;
        }
    }

    public static void main(String[] args) {
        Player p1 = new Player("小明",20,1,5,6);
        Player p2 = new Player("小李",20,1,5,6);
        p2.leanSkill(new WeightAttack());
        new Game().PK(p1,p2);
    }
}
