package com.tema1.player;

import com.tema1.common.Constants;
import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.goods.GoodsType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BasicPlayer extends Player {

  @Override
  public String getStrategy() {
    return "BASIC";
  }

  @Override
  public void playAsVendor() {
    Map<Goods, Integer> result = new HashMap<>();
    for (Goods good : getGoodsInHand()) {
      if (result.containsKey(good)) {
        result.put(good, result.get(good) + 1);
      } else {
        result.put(good, 1);
      }
    }

    /**
     * Toate bunurile Legale din mana
     */

    List<Map.Entry<Goods, Integer>> filtered =
            result.entrySet()
                    .stream()
                    .filter((goods) -> goods.getKey().getType() == GoodsType.Legal)
                    .collect(Collectors.toList());

    /**
     * Illegal only, se atinge de ele doar daca are numai ilegale.
     */
//    if (this.getTotalCoins() == 0) {
//      return;
//    }

    if (filtered.size() == 0) {
      this.setDeclaredGood(GoodsFactory.getInstance().getGoodsById(0));
      this.addToBag(Constants.getBestIllegal(result));
      this.discard(Constants.getBestIllegal(result));
      return;
    }

    /**
     * Sortare dupa frecventa, profit si ID.
     */

    List<Map.Entry<Goods, Integer>> list = new ArrayList<>(filtered);
    list.sort((e1, e2) -> {
      int c = e1.getValue() - e2.getValue();
      if (c == 0) {
        c = e1.getKey().getProfit() - e2.getKey().getProfit();
        if (c == 0) {
          c = e1.getKey().getId() - e2.getKey().getId();
        }
      }
      return c;
    });

    /**
     * Setam bunul respectiv si il adaugam in sac de cate ori il are.
     */

    this.setDeclaredGood(list.get(list.size() - 1).getKey());
   // System.out.println(getDeclaredGood().getId() + " ID PLR" + this.getId());
    for (int i = 0; i < list.get(list.size() - 1).getValue(); i++) {
      if (getGoodsInBag().size() == getMaxInBag()) {
        break;
      }
//      if(!list.get(list.size() - 1).getKey().equals(this.getDeclaredGood()))
//        continue;
      this.addToBag(list.get(list.size() - 1).getKey());
      this.discard(list.get(list.size() - 1).getKey());
    }
    this.getGoodsInHand().clear();
  }

  @Override
  public void playAsSheriff(final ArrayList<Player> players) {
    for (Player currPlayer : players) {
      if (!currPlayer.equals(this)) {
        if (this.getTotalCoins() < getMINCOINS()) {
            currPlayer.addOnStand();
        } else {
          checkPlayer(currPlayer);
          currPlayer.addOnStand();
        }
      }
    }
  }
}
