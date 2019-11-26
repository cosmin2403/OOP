package player;

import Visitor.Visitor;

public class Wizard extends Hero {
    public Wizard(String favouritePlace, int x, int y, int initialHP, String race) {
        super(favouritePlace, x, y, initialHP, race);
        this.setMaxHp();
    }
    WizardConstants wizardConstants = new WizardConstants();

    @Override
    public float getFieldAmplifier(String type) {
        if(this.getCellType().equals(this.getFavouritePlace()))
            return wizardConstants.getLandModifier();
        return 0;
    }

    @Override
    public HeroType returnType() {
        return HeroType.W;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
        int damage = visitor.getDamage();
        System.out.println("Damage in Wizard = " + damage);
        this.setHP(this.getHP() - damage);
        System.out.println(this.getHP() + "Wizard");
        if(this.getHP() <= 0) {
            this.setStatus("dead");
        }
    }

    @Override
    public void setMaxHp() {
        this.maxHp = wizardConstants.getInitialHp()
                + this.getLevel() * wizardConstants.getHpAtLvlUp();
    }
}
