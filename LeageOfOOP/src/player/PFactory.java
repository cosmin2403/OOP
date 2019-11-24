package player;

public interface PFactory {

    Hero createHero(HeroType heroType, int x, int y);
}
