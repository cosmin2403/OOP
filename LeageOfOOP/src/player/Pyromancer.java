package player;

import Visitor.Visitor;

public class Pyromancer extends Hero {
    Pyromancer(String favouritePlace, int x, int y, int initialHP, String race) {
        super(favouritePlace, x, y, initialHP, race);
        this.setMaxHp();
    }

    @Override
    public float getFieldAmplifier(String type) {
        if(this.getCellType().equals(this.getFavouritePlace()))
            return pyroCt.getLandModifier();
        return 0;
    }

    private PyromancerConstants pyroCt = new PyromancerConstants();

    @Override
    public HeroType returnType() {
        return HeroType.P;
    }


    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
        int damage = visitor.getDamage();
        System.out.println("Damage in Pyromancer = " + damage);
        this.setHP(this.getHP() - damage);
        //System.out.println(this.getHP() + "Pyro");
        if(this.getHP() <= 0) {
            this.setStatus("dead");
        }
    }
    @Override
    public void setMaxHp() {
        this.maxHp = pyroCt.getInitialHp()
                + this.getLevel() * pyroCt.getHpAtLvlUp();
    }
}
