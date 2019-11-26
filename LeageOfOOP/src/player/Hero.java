package player;


import Map.Map;

import Visitor.*;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Hero {
    private int HP;
    private int damageToDeal;
    private int XP;
    private int level;
    private String favouritePlace;
    private String cellType;
    private int x;
    private int y;
    private String cell;
    private String status = " ";
    private String race;
    private Visitor visitor;
    private boolean posibilityToMove = true;
    int maxHp;
    private boolean hasParalysis = false;
    private int paralysisToTake = 0;
    private int roundsParalysed;
    private int roundWhenTookStun;
    private int burnToTake;
    private int whenGotBurn;
    private int roundsBurnt;

    public int getRoundsBurnt() {
        return roundsBurnt;
    }

    public void setRoundsBurnt(int roundsBurnt) {
        this.roundsBurnt = roundsBurnt;
    }

    private boolean hasBurn = false;

    public boolean isHasBurn() {
        return hasBurn;
    }

    public void setHasBurn(boolean hasBurn) {
        this.hasBurn = hasBurn;
    }

    public int getBurnToTake() {
        return burnToTake;
    }

    public void setBurnToTake(int burnToTake) {
        this.burnToTake = burnToTake;
    }

    public int getWhenGotBurn() {
        return whenGotBurn;
    }

    public void setWhenGotBurn(int whenGotBurn) {
        this.whenGotBurn = whenGotBurn;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParalysisToTake() {
        return paralysisToTake;
    }

    public void setParalysisToTake(int paralysisToTake) {
        this.paralysisToTake = paralysisToTake;
    }

    public int getRoundsParalysed() {
        return roundsParalysed;
    }

    public void setRoundsParalysed(int roundsParalysed) {
        this.roundsParalysed = roundsParalysed;
    }

    public int getRoundWhenTookStun() {
        return roundWhenTookStun;
    }

    public void setRoundWhenTookStun(int roundWhenTookStun) {
        this.roundWhenTookStun = roundWhenTookStun;
    }

    public boolean isHasParalysis() {
        return hasParalysis;
    }

    public static int getRoundsPlayed() {
        return roundsPlayed;
    }

    public static void incrementRoundsPlayed() {
        roundsPlayed++;
    }

    static int roundsPlayed = 1;


    public void setHasParalysis(boolean hasParalysis) {
        this.hasParalysis = hasParalysis;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public String getCell() {
        return cell;
    }

    public void setCellString() {
        this.cell = Integer.toString(this.getX()) + Integer.toString(this.getY());
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Hero(String favouritePlace, int x, int y, int initialHP, String race) {
        this.XP = 0;
        this.HP = initialHP;
        this.level = 0;
        this.favouritePlace = favouritePlace;
        this.x = x;
        this.y = y;
        this.race = race;
    }

    public int getHP() {
        return HP;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHP(int HP) {
        this.HP = HP;
        if(HP <= 0) {
            this.setStatus("dead");
        }
    }

    public int getDamageToDeal() {
        return damageToDeal;
    }

    public void setDamageToDeal(int damageToDeal) {
        this.damageToDeal = damageToDeal;
    }

    public int getXP() {
        return XP;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void makeMove(MoveType move) {
        if(move == MoveType.D)
            this.setY(this.getX() - 1);
        if(move == MoveType.L)
            this.setY(this.getY() - 1);
        if(move == MoveType.U)
            this.setX(this.getX() + 1);
        if(move == MoveType.R) {
            this.setY(this.getY() + 1);
        }
    }

    public void setCellType() {
        this.cellType = Map.getInstance().getMap().get(this.x).get(this.y);
    }

    public boolean hasPosibilityToMove() {
        return posibilityToMove;
    }

    public void setPosibilityToMove(boolean posibilityToMove) {
        this.posibilityToMove = posibilityToMove;
    }

    public int expNeeded() {
        return (250 + this.level * 50);
    }

    public String getFavouritePlace() {
        return favouritePlace;
    }

    public String getCellType() {
        return cellType;
    }

    public int getWinnerXp(Hero hero) {
        int experience = this.XP + Integer.max(0, 200 - (this.level - hero.level) *40);
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public static HeroType convertHeroTypeToEnum(String x) {
        for(var type : HeroType.values()) {
            if(x.equals("P")) {
                return HeroType.P;
            }
            if(x.equals("W")) {
                return HeroType.W;
            }
            if(x.equals("R")) {
                return HeroType.R;
            }
            if(x.equals("K")) {
                return HeroType.K;
            }
        }
        return null;
    }

    public static MoveType convertMoveToEnum(String x) {
        if(x.equals("D")) {
            return MoveType.D;
        }
        if(x.equals("U")) {
            return MoveType.U;
        }
        if(x.equals("R")) {
            return MoveType.R;
        }
        if(x.equals("L")) {
            return MoveType.L;
        }
        return null;
    }

    public static java.util.Map<String, List<Hero>> findPlayersInSameCell(ArrayList<Hero> heroes) {
        for(Hero hero : heroes) {
            hero.setCellString();
        }
        return heroes.stream().collect(Collectors.groupingBy(Hero::getCell));
    }

    public abstract float getFieldAmplifier(String type);
    public abstract HeroType returnType();
    public abstract void acceptVisitor(Visitor visitor);

    public String getRace() {
        return race;
    }

    public Visitor getVisitor(Hero hero) {
        if(hero.getRace().equals("P")) {
            return new PyroVisitor((Pyromancer) hero);
        }
        if(hero.getRace().equals("K")) {
            return new KnightVisitor((Knight) hero);
        }
        if(hero.getRace().equals("R")) {
            return new RogueVisitor((Rogue) hero);
        }
        if(hero.getRace().equals("W")) {
            return new WizardVisitor((Wizard) hero);
        }
        return null;
    }
    public static void fight(java.util.Map<String, List<Hero>> toFight) {
        for(var x : toFight.entrySet()) {
            var value = toFight.get(x.getKey());
            for(int i = 0; i < value.size(); i++) {
                if(value.get(i).getHP() <= 0 || value.get(i + 1).getHP() <= 0) {
                    break;
                }
                if(value.get(i).hasParalysis) {
                    dealParalysisDamage(value.get(i));
                }
                if(value.get(i + 1).hasParalysis) {
                    dealParalysisDamage(value.get(i + 1));
                }
                if(value.get(i).hasBurn) {
                    dealBurnDamage(value.get(i));
                }
                if(value.get(i + 1).hasBurn) {
                    dealBurnDamage(value.get(i + 1));
                }
                if(value.get(i).getHP() <= 0 || value.get(i + 1).getHP() <= 0) {
                    break;
                }
                value.get(i).setVisitor(value.get(i).getVisitor(value.get(i + 1)));
                value.get(i + 1).setVisitor(value.get(i + 1).getVisitor(value.get(i)));
                value.get(i).acceptVisitor(value.get(i).getVisitor());
                value.get(i + 1).acceptVisitor(value.get(i + 1).getVisitor());
                if(!value.get(i).getStatus().equals("dead") && value.get(i + 1).getStatus().equals("dead")) {
                    value.get(i).setXP(value.get(i).getWinnerXp(value.get(i + 1)));
                } else if(value.get(i).getStatus().equals("dead") && !value.get(i + 1).getStatus().equals("dead")) {
                    value.get(i + 1).setXP(value.get(i + 1).getWinnerXp(value.get(i)));
                }
                break;
            }
        }
    }

    public static void dealParalysisDamage(Hero hero) {
        hero.setHP(hero.getHP() - hero.getParalysisToTake());
        hero.setRoundsParalysed(hero.getRoundsParalysed() - 1);
        if(hero.roundsParalysed == 0) {
            hero.setHasParalysis(false);
        }
    }

    public static void dealBurnDamage(Hero hero) {
        hero.setHP(hero.getHP() - hero.getBurnToTake());
        hero.setRoundsBurnt(hero.getRoundsParalysed() - 1);
        if(hero.roundsBurnt == 0) {
            hero.setHasBurn(false);
        }
    }

    public int getMaxHp() {
        return this.maxHp;
    }

    public abstract void setMaxHp();
}
