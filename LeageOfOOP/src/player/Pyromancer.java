package player;

import Visitor.HeroVisitor;
import Visitor.Visitable;
import Visitor.Visitor;

public class Pyromancer extends Hero implements Visitable {
    Pyromancer(String favouritePlace, int x, int y, int initialHP) {
        super(favouritePlace, x, y, initialHP);
    }

    @Override
    public float getFieldAmplifier(String type) {
        if(this.getCellType().equals(this.getFavouritePlace()))
            return pyroCt.getLandModifier();
        return 0;
    }

    public HeroVisitor theVisitor = new HeroVisitor();

    public int getRoundsPlayed() {
        return roundsPlayed;
    }

    public void incrementRoundsPlayer() {
        this.roundsPlayed++;
    }

    private PyromancerConstants pyroCt = new PyromancerConstants();

    @Override
    public HeroType returnType() {
        return HeroType.P;
    }

    int roundsPlayed = 0;

    @Override
    public void accept(Visitor visitor) {
        //De venit si completat cu verificarea pt overtime!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        int damageToTake = visitor.visit(this);
        this.setHP(damageToTake);
        if(this.getHP() <= 0) {
            this.setStatus("dead");
        }
    }
}
