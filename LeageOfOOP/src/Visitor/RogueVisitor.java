package Visitor;

import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.RogueConstants;

public class RogueVisitor implements Visitor {
    private int damage;
    private Rogue rogue;
    private RogueConstants rogueConstants = new RogueConstants();
    public RogueVisitor(Rogue rogue) {
        this.rogue = rogue;
    }


    /**
     *
     *
     *
     *
     *
     * DE ADAUGAT NEAPARAT RUNDE CU PARALIZIE 3 SAU 6
     * MOMENTAN VF TESTELE CU 1 V 1.
     *
     *
     *
     *
     */



    //Ce damage ii da rogue lui pyromancer
    @Override
    public void visit(Pyromancer pyromancer) {
        float backstabDamage = 0;
        if(rogue.getBackstabApplied() % 3 == 0 && rogue.getCellType().equals(rogue.getFavouritePlace())) {
            backstabDamage = rogueConstants.getBackstabBaseDamage()
                    + rogue.getLevel() * rogueConstants.getBackstabUpPerLevel();
            float backstabDamageAfterTerrainAmpl = backstabDamage * (1 + rogue.getFieldAmplifier(rogue.getCellType()))
                    * rogueConstants.getCriticalPercentage();
            backstabDamage = backstabDamageAfterTerrainAmpl * (1 + rogueConstants.getPyromancerBackstabModifier());
            rogue.incrementBackstabApplied();
        } else {
            backstabDamage = rogueConstants.getBackstabBaseDamage()
                    + rogue.getLevel() * rogueConstants.getBackstabUpPerLevel();
            float backstabDamageAfterTerrainAmpl = backstabDamage * (1 + rogue.getFieldAmplifier(rogue.getCellType()));
            backstabDamage = backstabDamageAfterTerrainAmpl * (1 + rogueConstants.getPyromancerBackstabModifier());
            rogue.incrementBackstabApplied();
        }

        float paralysisDamage = 0;
        pyromancer.setHasParalysis(true);
        paralysisDamage = rogueConstants.getParalysisBaseDamage()
                + rogue.getLevel() * rogueConstants.getParalysisDamageModifier();
        float paralysisDamageAfterTerrainAmpl = paralysisDamage * (1 + rogue.getFieldAmplifier(rogue.getCellType()));
        paralysisDamage = paralysisDamageAfterTerrainAmpl * (1 + rogueConstants.getPyromancerParalysisModifier());
        pyromancer.setParalysisToTake(Math.round(paralysisDamage));
        pyromancer.setRoundsParalysed(rogue.getFavouritePlace().equals(rogue.getCellType()) ? 3 : 6);
        damage = Math.round(backstabDamage) + Math.round(paralysisDamage);
    }

    //Ce damage ii da rogue lui Knight

    @Override
    public void visit(Knight knight) {
        float backstabDamage = 0;
        if(rogue.getBackstabApplied() % 3 == 0 && rogue.getCellType().equals(rogue.getFavouritePlace())) {
            backstabDamage = rogueConstants.getBackstabBaseDamage()
                    + rogue.getLevel() * rogueConstants.getBackstabUpPerLevel();
            float backstabDamageAfterTerrainAmpl = backstabDamage * (1 + rogue.getFieldAmplifier(rogue.getCellType()))
                    * rogueConstants.getCriticalPercentage();
            backstabDamage = backstabDamageAfterTerrainAmpl * (1 + rogueConstants.getKnightBackstabModifier());
            rogue.incrementBackstabApplied();
        } else {
            backstabDamage = rogueConstants.getBackstabBaseDamage()
                    + rogue.getLevel() * rogueConstants.getBackstabUpPerLevel();
            float backstabDamageAfterTerrainAmpl = backstabDamage * (1 + rogue.getFieldAmplifier(rogue.getCellType()));
            backstabDamage = backstabDamageAfterTerrainAmpl * (1 + rogueConstants.getKnightBackstabModifier());
            rogue.incrementBackstabApplied();
        }

        float paralysisDamage = 0;
        knight.setHasParalysis(true);
        paralysisDamage = rogueConstants.getParalysisBaseDamage()
                + rogue.getLevel() * rogueConstants.getParalysisDamageModifier();
        float paralysisDamageAfterTerrainAmpl = paralysisDamage * (1 + rogue.getFieldAmplifier(rogue.getCellType()));
        paralysisDamage = paralysisDamageAfterTerrainAmpl * (1 + rogueConstants.getKnightParalysisModifier());
        knight.setParalysisToTake(Math.round(paralysisDamage));
        knight.setRoundsParalysed(rogue.getFavouritePlace().equals(rogue.getCellType()) ? 3 : 6);
        damage = Math.round(backstabDamage) + Math.round(paralysisDamage);
    }

    @Override
    public int getDamage() {
        return damage;
    }

    //Ce damage ii da rogue lui rogue

    @Override
    public void visit(Rogue rogue1) {
        float backstabDamage = 0;
        if(rogue.getBackstabApplied() % 3 == 0 && rogue.getCellType().equals(rogue.getFavouritePlace())) {
            backstabDamage = rogueConstants.getBackstabBaseDamage()
                    + rogue.getLevel() * rogueConstants.getBackstabUpPerLevel();
            float backstabDamageAfterTerrainAmpl = backstabDamage * (1 + rogue.getFieldAmplifier(rogue.getCellType()))
                    * rogueConstants.getCriticalPercentage();
            backstabDamage = backstabDamageAfterTerrainAmpl * (1 + rogueConstants.getRogueBackstabModifier());
            rogue.incrementBackstabApplied();
        } else {
            backstabDamage = rogueConstants.getBackstabBaseDamage()
                    + rogue.getLevel() * rogueConstants.getBackstabUpPerLevel();
            float backstabDamageAfterTerrainAmpl = backstabDamage * (1 + rogue.getFieldAmplifier(rogue.getCellType()));
            backstabDamage = backstabDamageAfterTerrainAmpl * (1 + rogueConstants.getRogueBackstabModifier());
            rogue.incrementBackstabApplied();
        }

        float paralysisDamage = 0;
        rogue1.setHasParalysis(true);
        paralysisDamage = rogueConstants.getParalysisBaseDamage()
                + rogue.getLevel() * rogueConstants.getParalysisDamageModifier();
        float paralysisDamageAfterTerrainAmpl = paralysisDamage * (1 + rogue.getFieldAmplifier(rogue.getCellType()));
        paralysisDamage = paralysisDamageAfterTerrainAmpl * (1 + rogueConstants.getRogueParalysisModifier());
        rogue1.setParalysisToTake(Math.round(paralysisDamage));
        rogue1.setRoundsParalysed(rogue.getFavouritePlace().equals(rogue.getCellType()) ? 3 : 6);

        damage = Math.round(backstabDamage) + Math.round(paralysisDamage);
    }
}
