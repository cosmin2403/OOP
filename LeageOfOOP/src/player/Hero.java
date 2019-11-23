package player;


import Map.Map;

public abstract class Hero {
    private int HP;
    private int damageToDeal;
    private int XP;
    private int level;
    private String favouritePlace;
    private String cellType;
    private int x;
    private int y;


    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    public Hero(String favouritePlace, int x, int y, int initialHP) {
        this.XP = 0;
        this.HP = initialHP;
        this.level = 0;
        this.favouritePlace = favouritePlace;
        this.x = x;
        this.y = y;
    }

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


    private int getX() {
        return x;
    }

    private int getY() {
        return y;
    }

    public void makeMove(MoveType move) {
        if(move == MoveType.D)
            this.setY(this.getX() - 1);
        if(move == MoveType.L)
            this.setY(this.getY() - 1);
        if(move == MoveType.U)
            this.setX(this.getX() + 1);
        if(move == MoveType.R) {
            this.setY(this.getY() + 1);
        }
    }
    public void setCellType() {
        this.cellType = Map.getInstance().getMap().get(this.x).get(this.y);
    }

    public int expNeeded() {
        return (250 + this.level * 50);
    }

    public int getWinnerXp(Hero hero) {
        int experience = this.XP + Integer.max(0, 200 - (this.level - hero.level) *40);
        return experience;
    }

    public int getLevel() {
        return level;
    }
}
