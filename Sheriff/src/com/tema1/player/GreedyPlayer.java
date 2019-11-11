package com.tema1.player;

import com.tema1.goods.Goods;
import com.tema1.main.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public final class GreedyPlayer extends BasicPlayer {

  @Override
  public String getStrategy() {
    return "GREEDY";
  }

  @Override
  public void playAsVendor() {
    List<Goods> auxiliar = new LinkedList<Goods>(getGoodsInHand());
    super.playAsVendor();
    Collections.sort(auxiliar, (final Goods o1, final Goods o2) -> o1.getProfit() - o2.getProfit());

    //Daca are mana plina de ilegale o scoatem pe cea pe care a bagat o deja.

    if (auxiliar.get(0).getProfit() > getMAXPROFITLEGAL()) {
        this.discard(auxiliar.get(auxiliar.size() - 1));
        auxiliar.remove(auxiliar.get(auxiliar.size() - 1));
    }

    if (auxiliar.get(auxiliar.size() - 1).getProfit() > getMAXPROFITLEGAL()
            && getGoodsInBag().size() < Player.getMaxInBag()
            && Main.getRoundsPlayed() % 2 == 0) {
      this.addToBag(auxiliar.get(auxiliar.size() - 1));
      this.discard(auxiliar.get(auxiliar.size() - 1));
    }
    this.getGoodsInHand().clear();
  }

  @Override
  public void playAsSheriff(final ArrayList<Player> players) {
    for (Player thisPlayer : players) {
      if (!thisPlayer.equals(this)) {
        if (this.getTotalCoins() < getMINCOINS()) {
          for (var x : players) {
            if (!x.equals(this)) {
              x.addOnStand();
            }
          }
          return;
        }

        if (thisPlayer.hasBribe()) {
          thisPlayer.tradeCoins(thisPlayer.getBribe());
          this.tradeCoins(-thisPlayer.getBribe());
          thisPlayer.addOnStand();
        } else {
          checkPlayer(thisPlayer);
          thisPlayer.addOnStand();
        }
      }
    }
  }
}
