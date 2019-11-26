package main;

import Map.Map;
import Visitor.PyroVisitor;
import fileio.implementations.FileWriter;
import player.Hero;
import player.HeroFactory;
import player.Pyromancer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
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
            for(var k : heroes) {
                k.setCellType();
            }
            toFight =  Hero.findPlayersInSameCell(heroes);
            Hero.fight(toFight);
            for(var x : heroes) {
                if(x.getStatus().equals("dead")) {
                    u++;
                    continue;
                }
                x.makeMove(Hero.convertMoveToEnum(gameInput.getMoves().get(u)));
                u++;
            }
            Pyromancer.incrementRoundsPlayer();
        }

        FileWriter fileWriter = new FileWriter(args[1]);

        for(int i = 0; i < heroes.size(); i++) {
            if(!heroes.get(i).getStatus().equals("dead")) {
                fileWriter.writeWord(gameInput.getmPlayersOrder().get(i));
                fileWriter.writeWord(" ");
                fileWriter.writeInt(heroes.get(i).getLevel());
                fileWriter.writeWord(" ");
                fileWriter.writeInt(heroes.get(i).getXP());
                fileWriter.writeWord(" ");
                fileWriter.writeInt(heroes.get(i).getHP());
                fileWriter.writeWord(" ");
                fileWriter.writeInt(heroes.get(i).getX());
                fileWriter.writeWord(" ");
                fileWriter.writeInt(heroes.get(i).getY());
                fileWriter.writeWord(" ");
                fileWriter.writeNewLine();
            } else {
                fileWriter.writeWord(gameInput.getmPlayersOrder().get(i));
                fileWriter.writeWord(" ");
                fileWriter.writeWord("dead");
                fileWriter.writeNewLine();
            }

        }
        fileWriter.close();
    }
}

