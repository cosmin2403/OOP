package com.tema1.player;

public class PlayerFactory implements PFactory {
  private static PlayerFactory instance = new PlayerFactory();

  public PlayerFactory() {

  }

  @Override
  public final Player createPlayer(final Strategy strategy) {
    if (strategy == Strategy.greedy) {
      return new GreedyPlayer();
    }
    if (strategy == Strategy.basic) {
      return new BasicPlayer();
    }
    if (strategy == Strategy.bribed) {
      return new BribedPlayer();
    }

    return null;
  }

  public static PlayerFactory getInstance() {
    if (instance == null) {
      instance = new PlayerFactory();
    }
    return instance;
  }
}
