package player;

public class Pyromancer extends Hero {

    public Pyromancer(String favouritePlace, int x, int y, int initialHP) {
        super(favouritePlace, x, y, initialHP);
    }
    int hpAtLvlUp = 50;
    int baseDamageFireblast = 350;

    public void Fireblast() {
        int damage = baseDamageFireblast + 50 * this.getLevel();
    }
}
