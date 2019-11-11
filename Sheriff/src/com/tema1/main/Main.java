package com.tema1.main;

import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.goods.LegalGoods;
import com.tema1.player.Deck;
import com.tema1.player.Player;
import com.tema1.player.PlayerFactory;
import com.tema1.player.Strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Main {

  private static final int MAXIDFORLEGAL = 9;
  private static int roundsPlayed = 1;

  private static int getMAXIDFORLEGAL() {
    return MAXIDFORLEGAL;
  }

  public static int getRoundsPlayed() {
    return roundsPlayed;
  }

  private static void incrementRoundsPlayed() {
    roundsPlayed++;
  }

  private Main() {
    // just to trick checkstyle
  }

  private static void calculateKingAndQueen(final ArrayList<Player> players) {
    Map<Player, Integer> toFind = new HashMap<>();
    for (int i = 0; i <= getMAXIDFORLEGAL(); i++) {
      Goods actualGood = GoodsFactory.getInstance().getGoodsById(i);

      // Se adauga in toFind toti playerii care au bunul actual good
      // Impreuna cu nr lui de aparitii pe taraba.

      for (Player player : players) {
        if (player.getOnSale().containsKey(actualGood)) {
          toFind.put(player, player.getOnSale().get(actualGood));
        }
      }
      List<Map.Entry<Player, Integer>> list =
          new ArrayList<>(toFind.entrySet());
      list.sort((o1, o2) -> {
        int c = o1.getValue() - o2.getValue();
        if (c == 0) {
          c = o2.getKey().getId() - o1.getKey().getId();
        }
        return c;
      });

      if (!list.isEmpty() && actualGood instanceof LegalGoods) {
        list.get(list.size() - 1)
            .getKey()
            .tradeCoins(-((LegalGoods) actualGood).getKingBonus());

        if (list.size() >= 2) {
          list.get(list.size() - 2)
              .getKey()
              .tradeCoins(-((LegalGoods) actualGood).getQueenBonus());
        }
      } else if (list.isEmpty()) {
        continue;
      }
      toFind.clear();
    }

  }

  public static void main(final String[] args) {
    GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
    GameInput gameInput = gameInputLoader.load();
    // TODO implement homework logic

    // No of players:
    int noPlayers = gameInput.getPlayerNames().size();

    // No of rounds:
    int noRounds = gameInput.getRounds();

    // Array of players:
    final ArrayList<Player> players = new ArrayList<>();

    // Generator of player:
    PlayerFactory generator = PlayerFactory.getInstance();

    Deck.getDeckInstance().make(gameInput.getAssetIds());

    // Populate the array of players:
    for (var name : gameInput.getPlayerNames()) {
      switch (name) {
      case "bribed":
        players.add(generator.createPlayer(Strategy.bribed));
        break;
      case "basic":
        players.add(generator.createPlayer(Strategy.basic));
        break;
      case "greedy":
        players.add(generator.createPlayer(Strategy.greedy));
        break;
      default:
      }
    }

    // Se seteaza ID ul fiecarui jucator dupa cum a fost bagat
    // In vectorul initial.

    for (int i = 0; i < players.size(); i++) {
      players.get(i).setId(i);
    }


    //Mecanismul jocului.

    int j = 0;
    for (int i = 0; i < noPlayers * noRounds; ++i) {
      Player currentSheriff = players.get(i % noPlayers);
      for (var x : players) {
        if (!x.equals(currentSheriff)) {
          x.drawCards();
          x.playAsVendor();
          x.getGoodsInHand().clear();
        }
      }
      currentSheriff.playAsSheriff(players);
      ++j;
        if (j % (noPlayers) == 0) {
            Main.incrementRoundsPlayed();
        }
    }

    //Se acorda banii si bonusurile pentru tot ce are pe taraba.

    Player.addBonusAndAditionalLegalCards(players);
    Main.calculateKingAndQueen(players);

    ArrayList<Player> actualTop = new ArrayList<>(players);

    actualTop.sort((o1, o2) -> {
      int cmp = o1.getTotalCoins() - o2.getTotalCoins();
      if (cmp == 0) {
        cmp = Integer.compare(o2.getId(), o1.getId());
      }
      return cmp;
    });


    for (int i = actualTop.size() - 1; i >= 0; i--) {
      for (int y = 0; y < players.size(); y++) {
        if (players.get(y) == actualTop.get(i)) {
          System.out.println(y + " " + players.get(y).getStrategy() + " "
                  + actualTop.get(i).getTotalCoins());
        }
      }
    }
  }
}
