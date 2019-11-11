package com.tema1.player;

import com.tema1.goods.Goods;
import com.tema1.goods.GoodsType;
import com.tema1.goods.IllegalGoods;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.stream.Collectors;


public abstract class Player {
  private static final int MAXINBAG = 8;
  private static final int MAXCARDS = 10;
  private static final int STARTCARDS = 80;
  private static final int MINCOINS = 16;
  private static final int MAXPROFITLEGAL = 5;


  private HashMap<Goods, Integer> onSale;
  private LinkedList<Goods> goodsInHand;
  private ArrayList<Goods> goodsInBag;
  private Goods declaredGood = null;

  private int totalCoins;
  private int bribe;
  private int id;

  public final void setId(final int id) {
    this.id = id;
  }

  public final int getId() {
    return this.id;
  }

  abstract public String getStrategy();

  final void setDeclaredGood(final Goods declaredGood) {
    this.declaredGood = declaredGood;
  }

  final Goods getDeclaredGood() {
    return declaredGood;
  }

  public static int getMAXPROFITLEGAL() {
    return MAXPROFITLEGAL;
  }

  public static int getMINCOINS() {
    return MINCOINS;
  }

  static int getMaxInBag() {
    return MAXINBAG;
  }

  final void setBribe(final int bribe) {
    this.bribe = bribe;
  }

  public final int getTotalCoins() {
    return totalCoins;
  }

  public final LinkedList<Goods> getGoodsInHand() {
    return goodsInHand;
  }

  final int getBribe() {
    return bribe;
  }

  public final ArrayList<Goods> getGoodsInBag() {
    return goodsInBag;
  }

  public final HashMap<Goods, Integer> getOnSale() {
    return onSale;
  }

  Player() {
    this.totalCoins = STARTCARDS;
    this.bribe = -1;
    this.onSale = new HashMap<>();
    this.goodsInHand = new LinkedList<Goods>();
    this.goodsInBag = new ArrayList<Goods>();
  }

  public static void addBonusAndAditionalLegalCards(final ArrayList<Player> players) {
      for (Player player : players) {

          //Lista de perechi cu bunurile ilegale.

          List<Map.Entry<Goods, Integer>> filtered =
                  player.getOnSale().entrySet()
                          .stream()
                          .filter((goods) -> goods.getKey().getType() == GoodsType.Illegal)
                          .collect(Collectors.toList());

          //Ii acordam bani pt ce are in sac deja.
          var wrapper = new Object() {
            private Integer value = 0;

            Integer getValue() {
              return value;
            }

            void setValue(final Integer value) {
              this.value = value;
            }
          };

          player
                  .getOnSale()
                  .entrySet()
                  .forEach((e) -> wrapper.setValue(wrapper.getValue()
                          + e.getValue() * e.getKey().getProfit()));
          player.tradeCoins(-wrapper.value);
          int toChange = 0;

          //Ii acordam bani pt ce ii aduc bunurile ilegale.

          var wrapper2 = new Object() {
            private Integer value = 0;

            Integer getValue() {
              return value;
            }

            void setValue(final Integer value) {
              this.value = value;
            }
          };

          for (int i = 0; i < filtered.size(); i++) {
              IllegalGoods good = (IllegalGoods) filtered.get(i).getKey();
              for (int pairNumber = 0; pairNumber < filtered.get(i).getValue(); pairNumber++) {
                  var bonus = new LinkedList<>(good.getIllegalBonus().entrySet());
                  bonus.stream()
                          .forEach((e) -> wrapper2.setValue(wrapper2.getValue()
                                  + e.getValue() * e.getKey().getProfit()));
                  for (int m = 0; m < bonus.size(); m++) {
                      for (int c = 0; c < bonus.get(m).getValue(); c++) {
                          player.addItemToStand(bonus.get(m).getKey());
                      }
                  }
              }
          }
          player.tradeCoins(-wrapper2.value);
      }
  }

  public final void drawCards() {
    this.goodsInHand.addAll(
        Deck.getDeckInstance().spreadCards(MAXCARDS - goodsInHand.size()));
  }

  final void addToBag(final Goods good) {
    this.goodsInBag.add(good);
  }

  //Calculam suma ce trebuie schimbata

  private int calculatePenalty() {
    int sumToPay = 0;
    int flag = 0;
    ArrayList<Goods> removedCards = new ArrayList<>();
    for (var x : getGoodsInBag()) {
      if ((x.getType() == GoodsType.Illegal) || (x != getDeclaredGood())) {
        flag = 1;
        sumToPay = sumToPay + x.getPenalty();
        removedCards.add(x);
        Deck.getDeckInstance().addGood(x);
      }
    }

    if (flag == 0) {
      for (var x : getGoodsInBag()) {
        sumToPay = sumToPay - x.getPenalty();
      }
    }
    goodsInBag.removeAll(removedCards);
    for (var good : removedCards) {
      Deck.getDeckInstance().addGood(good);
    }

    return sumToPay;
  }

  public final void tradeCoins(final int coins) {
    this.totalCoins -= coins;
  }

  final void checkPlayer(final Player player) {
    int exchangedSum = player.calculatePenalty();
    totalCoins += exchangedSum;
    player.tradeCoins(exchangedSum);
  }

  final boolean hasBribe() {
    return bribe > 0;
  }

  final void discard(final Goods good) {
    this.getGoodsInHand().remove(good);
  }

  private void addItemToStand(final Goods good) {
    try {
      this.onSale.put(good, this.onSale.get(good) + 1);
    } catch (NullPointerException e) {
      this.onSale.put(good, 1);
    }
  }

  final void addOnStand() {
    for (var x : getGoodsInBag()) {
      addItemToStand(x);
    }
    goodsInBag.clear();
  }

  public abstract void playAsSheriff(ArrayList<Player> players);
  public abstract void playAsVendor();

  public final void addPointsBasedOnGoodsInBag() {
    Map<Goods, Integer> bonuss = new HashMap<>();
  for (var x : goodsInBag) {
      this.totalCoins += x.getProfit();
    }
  }
}
