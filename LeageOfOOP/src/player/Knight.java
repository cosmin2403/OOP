package player;

import Visitor.Visitor;

public class Knight extends Hero{
    Knight(String favouritePlace, int x, int y, int initialHP, String race) {
        super(favouritePlace, x, y, initialHP, race);
    }

    private KnightConstants knightConstants = new KnightConstants();

    @Override
    public float getFieldAmplifier(String type) {
        if(this.getCellType().equals(this.getFavouritePlace()))
            return knightConstants.getLandModifier();
        return 0;
    }

    @Override
    public HeroType returnType() {
        return HeroType.K;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
        int damage = visitor.getDamage();
        System.out.println("Damage in knight = " + damage);
        this.setHP(this.getHP() - damage);
        System.out.println(this.getHP() + "knight");
        if(this.getHP() <= 0) {
            this.setStatus("dead");
        }
    }
    @Override
    public void setMaxHp() {
        this.maxHp = knightConstants.getInitialHp()
                + this.getLevel() * knightConstants.getHpAtLvlUp();
    }
}
