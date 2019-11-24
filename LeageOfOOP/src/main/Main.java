package main;

import Map.Map;
import org.w3c.dom.ls.LSOutput;
import player.Hero;
import player.HeroFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

//        Map.getInstance().getMap().forEach(System.out::println);
//        System.out.println(noPlayers);
//        System.out.println(noRounds);

        HeroFactory heroGenerator = new HeroFactory();

//        De bagat in switch cu HeroFactory + coordonatele necesare
//        gameInput.getmPlayersOrder().forEach(System.out::println);
//        gameInput.getInitialCoordinates().forEach(System.out::println);

        int j = 0;
        for(var x : gameInput.getmPlayersOrder()) {
            heroes.add(heroGenerator.createHero(Hero.convertHeroTypeToEnum(x),
                    gameInput.getInitialCoordinates().get(j),
                    gameInput.getInitialCoordinates().get(j + 1)));
            j+=2;
        }

//        for(Hero hero : heroes) {
//            hero.setCellType();
//        }

//        heroes.forEach((e) -> {
//            System.out.println(e.getX());
//            System.out.println(e.getY());
//            System.out.println(e.returnType());
//        });
//        gameInput.getMoves().forEach(System.out::println);

        int u = 0;
        java.util.Map<String, List<Hero>> toFight = new HashMap<>();
        for(int i = 0; i < noRounds; i++) {
            for(var k : heroes)
                k.setCellType();

            // verificam ce playeri trebuie sa se lupte.

            toFight =  Hero.findPlayersInSameCell(heroes);
            for(var x : heroes) {

                // Aici o metoda pentru a se lupta jucatorii.



                // Apoi isi fac miscarea.

                x.makeMove(Hero.convertMoveToEnum(gameInput.getMoves().get(u)));
                u++;
            }
        }

        for(int i = 0; i < heroes.size(); i++) {
            if(!heroes.get(i).getStatus().equals("dead")) {
                System.out.println(gameInput.getmPlayersOrder().get(i)
                        + " " + heroes.get(i).getLevel() + " " + heroes.get(i).getXP()
                        + " " + heroes.get(i).getHP() + " " + heroes.get(i).getX() + " "
                        + heroes.get(i).getY());
            } else {
                System.out.println(gameInput.getmPlayersOrder().get(i) + " "
                + heroes.get(i).getStatus());
            }

        }
    }
}

