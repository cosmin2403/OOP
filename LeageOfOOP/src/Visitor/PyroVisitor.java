package Visitor;

import player.*;

public class PyroVisitor implements Visitor {
    private PyromancerConstants pyroCt = new PyromancerConstants();
    private int damage;
    private Pyromancer pyro;
    public PyroVisitor(Pyromancer pyro) {
        this.pyro = pyro;
    }

    @Override
    public int getDamage() {
        return damage;
    }


    //Ce damage ii da pyro lui Rogue
    @Override
    public void visit(Rogue rogue) {
        float damageAfterTerrainAmplificationA1 = (pyroCt.getFireblastBaseDamage()
                + pyro.getLevel() * pyroCt.getFireblastUpPerLevel())
                *(1 + pyro.getFieldAmplifier(rogue.getCellType()));
        //System.out.println("DD" + damageAfterTerrainAmplificationA1 + " " + knight.getLevel() + " " + knight.getFieldAmplifier(knight.getCellType()));
        float damageAfterRaceAmplificationA1 = damageAfterTerrainAmplificationA1
                * (1 + pyroCt.getRogueFireblastModifier());
        int totalDamageFireblast = Math.round(damageAfterRaceAmplificationA1);


        float damageAfterTerrainAmplificationA2 = 0;
        float damageAfterTerrainAmplificationA2BURN = 0;
        damageAfterTerrainAmplificationA2 = (pyroCt.getIgniteBaseDamage()
                + pyro.getLevel() * pyroCt.getIgniteDamageModifier())
                *(1 + pyro.getFieldAmplifier(rogue.getCellType()));

        damageAfterTerrainAmplificationA2BURN = (pyroCt.getIgniteLowDamage()
                + (pyro.getLevel() * pyroCt.getIgniteLowDamageModifier()))
                * (1 + pyro.getFieldAmplifier(pyro.getCellType()));
        float damageAfterRaceAmplificationA2 = damageAfterTerrainAmplificationA2
                *(1 + pyroCt.getRogueFireblastModifier());
        float BURN = damageAfterTerrainAmplificationA2BURN * (1 + pyroCt.getRogueFireblastModifier());
        int totalDamageIgnite = Math.round(damageAfterRaceAmplificationA2);
        this.setBrn(rogue, Math.round(BURN));
        damage = totalDamageFireblast + totalDamageIgnite ;
    }

    @Override
    public void visit(Wizard wizard) {
        float damageAfterTerrainAmplificationA1 = (pyroCt.getFireblastBaseDamage()
                + pyro.getLevel() * pyroCt.getFireblastUpPerLevel())
                *(1 + pyro.getFieldAmplifier(wizard.getCellType()));
        //System.out.println("DD" + damageAfterTerrainAmplificationA1 + " " + knight.getLevel() + " " + knight.getFieldAmplifier(knight.getCellType()));
        float damageAfterRaceAmplificationA1 = damageAfterTerrainAmplificationA1
                * (1 + pyroCt.getWizardFireblastModifier());
        int totalDamageFireblast = Math.round(damageAfterRaceAmplificationA1);


        float damageAfterTerrainAmplificationA2 = 0;
        float damageAfterTerrainAmplificationA2BURN = 0;
        damageAfterTerrainAmplificationA2 = (pyroCt.getIgniteBaseDamage()
                + pyro.getLevel() * pyroCt.getIgniteDamageModifier())
                *(1 + pyro.getFieldAmplifier(wizard.getCellType()));

        damageAfterTerrainAmplificationA2BURN = (pyroCt.getIgniteLowDamage()
                + (pyro.getLevel() * pyroCt.getIgniteLowDamageModifier()))
                * (1 + pyro.getFieldAmplifier(pyro.getCellType()));
        float damageAfterRaceAmplificationA2 = damageAfterTerrainAmplificationA2
                *(1 + pyroCt.getWizardFireblastModifier());
        float BURN = damageAfterTerrainAmplificationA2BURN * (1 + pyroCt.getWizardFireblastModifier());
        int totalDamageIgnite = Math.round(damageAfterRaceAmplificationA2);
        this.setBrn(wizard, Math.round(BURN));
        damage = totalDamageFireblast + totalDamageIgnite ;
    }

    @Override
    public void visit(Pyromancer pyromancer) {
        /**
         * initialDamage = ...;
         * damageAfterTerrainAmplification = initialDamage * terrainAmplification;
         * damageAfterRaceAmplification = damageAfterTerrainAmplification * raceAmplification;
         * totalDamage = round(damageAfterRaceAmplification);
         */
        //Ce damage ii da pyro(cel declarat) altui pyro.


        float damageAfterTerrainAmplificationA1 = (pyroCt.getFireblastBaseDamage()
                + pyromancer.getLevel() * pyroCt.getFireblastUpPerLevel())
                *(1 + pyromancer.getFieldAmplifier(pyromancer.getCellType()));
       // System.out.println("DD" + damageAfterTerrainAmplificationA1 + " " + pyromancer.getLevel() + " " + pyromancer.getFieldAmplifier(pyromancer.getCellType()));
        float damageAfterRaceAmplificationA1 = damageAfterTerrainAmplificationA1
                * (1 + pyroCt.getPyromancerFireblastModifier());
        int totalDamageFireblast = Math.round(damageAfterRaceAmplificationA1);


        float damageAfterTerrainAmplificationA2 = 0;
        float damageAfterTerrainAmplificationA2BURN = 0;
        damageAfterTerrainAmplificationA2 = (pyroCt.getIgniteBaseDamage()
                    + pyromancer.getLevel() * pyroCt.getIgniteDamageModifier())
                    *(1 + pyro.getFieldAmplifier(pyromancer.getCellType()));

        damageAfterTerrainAmplificationA2BURN = (pyroCt.getIgniteLowDamage()
                    + (pyro.getLevel() * pyroCt.getIgniteLowDamageModifier()))
                    * (1 + pyro.getFieldAmplifier(pyromancer.getCellType()));
        float damageAfterRaceAmplificationA2 = damageAfterTerrainAmplificationA2
                *(1 + pyroCt.getPyromancerFireblastModifier());
        float BURN = damageAfterTerrainAmplificationA2BURN * (1 + pyroCt.getPyromancerFireblastModifier());
        int totalDamageIgnite = Math.round(damageAfterRaceAmplificationA2);
        this.setBrn(pyromancer, Math.round(BURN));
        damage = totalDamageFireblast + totalDamageIgnite;

    }


    //Ce damage ii da pyro lui knight.

    @Override
    public void visit(Knight knight) {
        float damageAfterTerrainAmplificationA1 = (pyroCt.getFireblastBaseDamage()
                + pyro.getLevel() * pyroCt.getFireblastUpPerLevel())
                *(1 + knight.getFieldAmplifier(knight.getCellType()));
        //System.out.println("DD" + damageAfterTerrainAmplificationA1 + " " + knight.getLevel() + " " + knight.getFieldAmplifier(knight.getCellType()));
        float damageAfterRaceAmplificationA1 = damageAfterTerrainAmplificationA1
                * (1 + pyroCt.getKnightFireblastModifier());
        int totalDamageFireblast = Math.round(damageAfterRaceAmplificationA1);


        float damageAfterTerrainAmplificationA2 = 0;
        float damageAfterTerrainAmplificationA2BURN = 0;
        damageAfterTerrainAmplificationA2 = (pyroCt.getIgniteBaseDamage()
                + pyro.getLevel() * pyroCt.getIgniteDamageModifier())
                *(1 + pyro.getFieldAmplifier(knight.getCellType()));

        damageAfterTerrainAmplificationA2BURN = (pyroCt.getIgniteLowDamage()
                + (pyro.getLevel() * pyroCt.getIgniteLowDamageModifier()))
                * (1 + pyro.getFieldAmplifier(pyro.getCellType()));
        float damageAfterRaceAmplificationA2 = damageAfterTerrainAmplificationA2
                *(1 + pyroCt.getKnightFireblastModifier());
        float BURN = damageAfterTerrainAmplificationA2BURN * (1 + pyroCt.getKnightFireblastModifier());
        int totalDamageIgnite = Math.round(damageAfterRaceAmplificationA2);
        this.setBrn(knight, Math.round(BURN));
        damage = totalDamageFireblast + totalDamageIgnite;
    }

    public void setBrn(Hero hero, int brn) {
        hero.setWhenGotBurn(Hero.getRoundsPlayed());
        hero.setBurnToTake(brn);
        hero.setHasBurn(true);
    }
}
