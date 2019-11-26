package player;

public class HeroFactory implements PFactory {

    @Override
    public Hero createHero(HeroType heroType, int x, int y) {
        if(heroType == HeroType.K) {
            return new Knight("L", x, y, 900, "K");
        }
        if(heroType == HeroType.R) {
            return new Rogue("W", x, y, 600, "R");
        }
//        if(heroType == HeroType.W) {
//            return new Wizard("D", x, y, 400);
//        }
        if(heroType == HeroType.P) {
            return new Pyromancer("V", x, y, 500, "P");
        }
        return null;
    }
}
