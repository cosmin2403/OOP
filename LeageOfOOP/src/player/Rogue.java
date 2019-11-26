package player;

import Visitor.Visitor;

public class Rogue extends Hero {

    private RogueConstants rogueConstants = new RogueConstants();

    private int backstabApplied = 0;

    public void incrementBackstabApplied() {
        this.backstabApplied++;
    }

    public int getBackstabApplied() {
        return this.backstabApplied;
    }

    public Rogue(String favouritePlace, int x, int y, int initialHP, String race) {
        super(favouritePlace, x, y, initialHP, race);
        this.setMaxHp();
    }

    @Override
    public float getFieldAmplifier(String type) {
        if(this.getCellType().equals(this.getFavouritePlace()))
            return rogueConstants.getLandModifier();
        return 0;
    }

    @Override
    public HeroType returnType() {
        return HeroType.R;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
        int damage = visitor.getDamage();
        System.out.println("Damage in Rogue = " + damage);
        this.setHP(this.getHP() - damage);
        System.out.println(this.getHP() + "Rogue");
        if(this.getHP() <= 0) {
            this.setStatus("dead");
        }
    }

    @Override
    public void setMaxHp() {
        this.maxHp = rogueConstants.getInitialHp()
                + this.getLevel() * rogueConstants.getHpAtLvlUp();
    }
}
