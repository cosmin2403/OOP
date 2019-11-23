package main;

import Map.Map;
import org.w3c.dom.ls.LSOutput;
import player.Hero;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();

        int noPlayers = gameInput.getmPlayersOrder().size();
        int noRounds = gameInput.getRounds();
        final ArrayList<Hero> heroes = new ArrayList<>();

        //Facem mapa

        for(int i = 0; i < gameInput.getMap().length; i++) {
            ArrayList<String> aux = new ArrayList<>();
            for(int j = 0; j < gameInput.getMap()[i].length; j++) {
                aux.add(gameInput.getMap()[i][j]);
            }
            Map.getInstance().addCell(i, aux);
        }

        //Verificari citire

        Map.getInstance().getMap().forEach(System.out::println);
        System.out.println(noPlayers);
        System.out.println(noRounds);

        //De bagat in switch cu HeroFactory + coordonatele necesare

        gameInput.getmPlayersOrder().forEach(System.out::println);
        gameInput.getInitialCoordinates().forEach(System.out::println);

        gameInput.getMoves().forEach(System.out::println);
    }
}

