package com.tema1.player;

public interface PFactory {
  /**
   * Creates a Player which implements the strategy given as a parameter.
   *
   * @param strategy The required Strategy.
   * @return a Player which respects the strategy given as a parameter.
   */
  Player createPlayer(Strategy strategy);
}
