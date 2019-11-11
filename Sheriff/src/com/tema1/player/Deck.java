package com.tema1.player;
import com.tema1.goods.Goods;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.tema1.goods.GoodsFactory;

public final class Deck {
  // Pachetul va fi reprezentat cu o coada.
  private Queue<Goods> deck;
  private static Deck instanceOfDeck = null;

  private Deck() {
      deck = new LinkedList<Goods>();
  }

  public static Deck getDeckInstance() {
    if (instanceOfDeck == null) {
      instanceOfDeck = new Deck();
    }
    return instanceOfDeck;
  }

  public void make(final List<Integer> ids) {
    for (Integer i : ids) {
      deck.add(GoodsFactory.getInstance().getGoodsById(i));
    }
  }

  ArrayList<Goods> spreadCards(final int number) {
    ArrayList<Goods> drawnCards = new ArrayList<>();
    for (int i = 0; i < number; i++) {
      drawnCards.add(deck.remove());
    }

    return drawnCards;
  }

  void addGood(final Goods good) {
      deck.add(good);
  }
}
