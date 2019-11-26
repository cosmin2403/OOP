package Visitor;

import player.*;

public class WizardVisitor implements Visitor {
    Wizard wizard;
    int damage;
    WizardConstants wizardConstants = new WizardConstants();


    public WizardVisitor(Wizard wizard) {
        this.wizard = wizard;
    }
    public float getPercentage(Hero hero) {
        return (0.2f + (float)hero.getLevel()/100 * 5);
    }

    //ce damage ii da wizard lui pyromancer.
    @Override
    public void visit(Pyromancer pyromancer) {
        float percentage = getPercentage(pyromancer);
        float baseHp = Math.min(0.3f * pyromancer.getMaxHp(), pyromancer.getHP());
        percentage = percentage * wizardConstants.getPyromancerDrainModifier();
        damage = Math.round(percentage * baseHp);


    }

    @Override
    public void visit(Knight knight) {

    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void visit(Rogue rogue) {

    }

    @Override
    public void visit(Wizard wizard1) {
        float percentage = getPercentage(wizard1);
        float baseHp = (float) Math.min(0.3f * (float)wizard1.getMaxHp(), (float)wizard1.getHP());
        percentage = percentage * (1 + wizardConstants.getWizardDrainModifier());
        damage =  Math.round(percentage * baseHp * (1 + wizard.getFieldAmplifier(wizard.getCellType())));
    }
}
