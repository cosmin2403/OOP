package com.tema1.player;

import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.goods.GoodsType;
import com.tema1.goods.IllegalGoods;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class BribedPlayer extends BasicPlayer {
  private Player left = null;
  private Player right = null;
  private final int lessBribe = 5;
  private final int bigBribe = 10;

  public int getMinToPlayLegal() {
    return minToPlayLegal;
  }

  private final int minToPlayLegal = 5;

  public Player getLeft() {
    return left;
  }

  public Player getRight() {
    return right;
  }

  @Override
  public String getStrategy() {
    return "BRIBED";
  }

  private int getBribe(final int numberOfIllegal) {
    if (numberOfIllegal == 1 || numberOfIllegal == 2) {
      return lessBribe;
    }
    if (numberOfIllegal > 2) {
      return bigBribe;
    }
    return 0;
  }


  //Cautam playerii stanga si dreapta

  private void findWhoToCheck(final ArrayList<Player> players) {
    int index = 0;
    for (int i = 0; i < players.size(); i++) {
      if (players.get(i).equals(this)) {
        index = i;
      }
    }
    if (index - 1 < 0) {
      left = players.get(players.size() - 1);
    } else {
      left = players.get(index - 1);
    }

    right = players.get(((index + 1)) % players.size());
  }

  @Override
  public void playAsVendor() {
    int cnt = 0;
    List<Goods> list = new LinkedList<>(getGoodsInHand());
    list.sort((o1, o2) -> Integer.compare(o2.getProfit(), o1.getProfit()));

    // Cazul full ilegale sau full legale sau e obligat sa joace basic.

    if (list.get(0).getProfit() <= getMAXPROFITLEGAL()
            || this.getTotalCoins() < getMinToPlayLegal()) {
      super.playAsVendor();
      return;
    } else if (list.get(list.size() - 1).getProfit() > getMAXPROFITLEGAL()) {
      int counter = 0;
      int moneyCopy = getTotalCoins();
      for (var x : list) {
        if (moneyCopy - x.getPenalty() < 1
                || getGoodsInBag().size() == getMaxInBag()) {
          break;
        }
        this.addToBag(x);
        this.discard(x);
        counter++;
        moneyCopy -= x.getPenalty();
      }
      this.setDeclaredGood(GoodsFactory.getInstance().getGoodsById(0));
      this.setBribe(getBribe(counter));
      this.getGoodsInHand().clear();
      return;
    }

    // Cazul in care are si ilegale si legale

    int moneyCopy = getTotalCoins();
    List<Goods> list1 = new ArrayList<>(getGoodsInHand());
    list1.sort((o1, o2) -> {
      int c = o2.getProfit() - o1.getProfit();
      if (c == 0) {
        c = o2.getId() - o1.getId();
      }
      return c;
    });

    this.setDeclaredGood(GoodsFactory.getInstance().getGoodsById(0));
    for (int i = 0; i < list1.size() - 1; i++) {
      if (this.getGoodsInBag().size() == getMaxInBag() || moneyCopy <= 1) {
        break;
      }
      if (moneyCopy - list1.get(i).getPenalty() <= 1
              && list1.get(i).getType() == GoodsType.Illegal) {
        for (int j = i; j < list1.size() - 1; j++) {
          if (list1.get(j).getType().equals(GoodsType.Legal)
                  && moneyCopy - list1.get(j).getPenalty() <= 1) {
            break;
          } else if (list1.get(j).getType().equals(GoodsType.Legal)
                  && moneyCopy - list1.get(j).getPenalty() > 0) {
            this.addToBag(list1.get(j));
            this.discard(list1.get(j));
            moneyCopy -= list1.get(j).getPenalty();
          }
        }
        if (moneyCopy - list.get(i).getPenalty() <= 0) {
          break;
        }
      }
      if (moneyCopy - list.get(i).getPenalty() <= 0) {
        break;
      }
      this.addToBag(list1.get(i));
      this.discard(list1.get(i));
      if (list1.get(i) instanceof IllegalGoods) {
        cnt++;
      }
      moneyCopy -= list1.get(i).getPenalty();
    }
    this.getGoodsInHand().clear();
    this.setBribe(getBribe(cnt));
  }

  @Override
  public void playAsSheriff(final ArrayList<Player> players) {
    if (this.getTotalCoins() < getMINCOINS()) {
      for (var x : players) {
        if (!x.equals(this)) {
          x.addOnStand();
        }
      }
      return;
    }
    findWhoToCheck(players);

    if (left != this) {
      checkPlayer(left);
      left.addOnStand();
    }

    if (right != left) {
      checkPlayer(right);
      right.addOnStand();
    }

    //Va lua mita celorlalti

    for (var x : players) {
      if (!x.equals(this) && !x.equals(left) && !x.equals(right)) {
        if (x.hasBribe()) {
          x.tradeCoins(x.getBribe());
          this.tradeCoins(-x.getBribe());
        }
        x.addOnStand();
      }
    }
  }
}
