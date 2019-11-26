package Visitor;

import player.*;

public class KnightVisitor implements Visitor {

    private int damage;
    private Knight knight;
    private KnightConstants knightConstants = new KnightConstants();
    public KnightVisitor(Knight knight) {
        this.knight = knight;
    }

    private float testLimit(Hero hero) {
        float percentage = 0;
        if(hero.getLevel() >= 20) {
            percentage = 0.2f;
        } else {
            percentage = (float)hero.getLevel()/100;
        }
        return (percentage + 0.2f) * hero.getMaxHp();
    }

    /**
     * initialDamage = ...;
     * damageAfterTerrainAmplification = initialDamage * terrainAmplification;
     * damageAfterRaceAmplification = damageAfterTerrainAmplification * raceAmplification;
     * totalDamage = round(damageAfterRaceAmplification);
     */

    @Override
    //Ce damage ii da knight cel de sus lui pyromancer.
    public void visit(Pyromancer pyromancer) {
        float executeDamage = 0;
        float slamDamage = 0;
        float limit = testLimit(pyromancer);
        if(pyromancer.getHP() <= limit) {
            pyromancer.setStatus("dead");
            pyromancer.setHP(0);
            return;
        } else {
            executeDamage = knightConstants.getExecuteBaseDamage() + knight.getLevel() * knightConstants.getExecuteUpPerLevel();
            float executeDamageAfterTerrainAmpl = executeDamage * (1 + knight.getFieldAmplifier(knight.getCellType()));
            executeDamage = executeDamageAfterTerrainAmpl * (1 + knightConstants.getPyromancerExecuteModifier());
        }
        slamDamage = knightConstants.getSlamDamage() + knight.getLevel() * knightConstants.getSlamDamageModifier();
        float slamDamageAfterTerrainAmpl = slamDamage * (1 + knight.getFieldAmplifier(knight.getCellType()));
        slamDamage = slamDamageAfterTerrainAmpl * (1 + knightConstants.getPyromancerSlamModifier());
        pyromancer.setPosibilityToMove(false);

        damage = Math.round(executeDamage) + Math.round(slamDamage);
    }

    //Ce damage ii da knight lui knight1
    @Override
    public void visit(Knight knight1) {
        float executeDamage = 0;
        float slamDamage = 0;
        float limit = testLimit(knight1);
        if(knight1.getHP() <= limit) {
            knight1.setStatus("dead");
            knight1.setHP(0);
            return;
        } else {
            executeDamage = knightConstants.getExecuteBaseDamage() + knight.getLevel() * knightConstants.getExecuteUpPerLevel();
            float executeDamageAfterTerrainAmpl = executeDamage * (1 + knight.getFieldAmplifier(knight.getCellType()));
            executeDamage = executeDamageAfterTerrainAmpl * (1 + knightConstants.getKnightExecuteModifier());
        }
        slamDamage = knightConstants.getSlamDamage() + knight.getLevel() * knightConstants.getSlamDamageModifier();
        float slamDamageAfterTerrainAmpl = slamDamage * (1 + knight.getFieldAmplifier(knight.getCellType()));
        slamDamage = slamDamageAfterTerrainAmpl * (1 + knightConstants.getKnightSlamModifier());
        knight1.setPosibilityToMove(false);

        damage = Math.round(executeDamage) + Math.round(slamDamage);
    }

    @Override
    public int getDamage() {
        return damage;
    }

    //Ce damage ii da knight lui rogue
    @Override
    public void visit(Rogue rogue) {
        float executeDamage = 0;
        float slamDamage = 0;
        float limit = testLimit(rogue);
        if(rogue.getHP() <= limit) {
            rogue.setStatus("dead");
            rogue.setHP(0);
            return;
        } else {
            executeDamage = knightConstants.getExecuteBaseDamage() + knight.getLevel() * knightConstants.getExecuteUpPerLevel();
            float executeDamageAfterTerrainAmpl = executeDamage * (1 + knight.getFieldAmplifier(knight.getCellType()));
            executeDamage = executeDamageAfterTerrainAmpl * (1 + knightConstants.getRogueExecuteModifier());
        }
        slamDamage = knightConstants.getSlamDamage() + knight.getLevel() * knightConstants.getSlamDamageModifier();
        float slamDamageAfterTerrainAmpl = slamDamage * (1 + knight.getFieldAmplifier(knight.getCellType()));
        slamDamage = slamDamageAfterTerrainAmpl * (1 + knightConstants.getRogueSlamModifier());
        rogue.setPosibilityToMove(false);

        damage = Math.round(executeDamage) + Math.round(slamDamage);
    }
}
