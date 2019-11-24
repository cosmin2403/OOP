package Visitor;

import player.Pyromancer;
import player.PyromancerConstants;

public class HeroVisitor implements Visitor {
    PyromancerConstants pyroCt = new PyromancerConstants();

    @Override
    public int visit(Pyromancer pyromancer) {
        /**
         * initialDamage = ...;
         * damageAfterTerrainAmplification = initialDamage * terrainAmplification;
         * damageAfterRaceAmplification = damageAfterTerrainAmplification * raceAmplification;
         * totalDamage = round(damageAfterRaceAmplification);
         */
        float damageAfterTerrainAmplificationA1 = pyroCt.getBaseDamageFireblast()
                + pyromancer.getLevel() * pyroCt.getFireblastDamageModifier()
                * pyromancer.getFieldAmplifier(pyromancer.getCellType());
        float damageAfterRaceAmplificationA1 = damageAfterTerrainAmplificationA1
                * pyroCt.getPyromancerFireblastModifier();
        int totalDamageFireblast = Math.round(damageAfterRaceAmplificationA1);

        if(pyromancer.getRoundsPlayed() == 1) {
            float damageAfterTerrainAmplificationA2 = pyroCt.getIgniteBaseDamage()
                    + pyromancer.getLevel() * pyroCt.getIgniteUpPerLevel()
                    * pyromancer.getFieldAmplifier(pyromancer.getCellType());
        } else {
            float damageAfterTerrainAmplificationA2 = pyroCt.getIgniteLowDamage()
                    + pyromancer.getLevel() * pyroCt.getIgniteLowDamageModifier()
                    * pyromancer.getFieldAmplifier(pyromancer.getCellType());
        }
        float damageAfterRaceAmplificationA2 = damageAfterTerrainAmplificationA1
                * pyroCt.getPyromancerFireblastModifier();
        int totalDamageIgnite = Math.round(damageAfterRaceAmplificationA2);


        return totalDamageFireblast + totalDamageIgnite;
    }
}
