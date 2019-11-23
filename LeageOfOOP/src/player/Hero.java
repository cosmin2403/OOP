package player;


public abstract class Hero {
    private int HP;
    private int damageToDeal;
    private int XP;
    private int level;
    private String favouritePlace;
    private String cellType;
    private int x;
    private int y;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getDamageToDeal() {
        return damageToDeal;
    }

    public void setDamageToDeal(int damageToDeal) {
        this.damageToDeal = damageToDeal;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }
}
