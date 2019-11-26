package Visitor;

import player.Knight;
import player.Pyromancer;
import player.Rogue;

public interface Visitor {
    public void visit(Pyromancer pyromancer);
    public void visit(Knight knight);
    public abstract int getDamage();

    public void visit(Rogue rogue);
}
