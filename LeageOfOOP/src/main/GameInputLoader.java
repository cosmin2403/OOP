package main;

import fileio.FileSystem;
import player.Hero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GameInputLoader {
    String[][] map;
    private final String mInputPath;
    private final String mOutputPath;

    public String getmOutputPath() {
        return mOutputPath;
    }

    public GameInputLoader(String mInputPath, String mOutputPath) {
        this.mInputPath = mInputPath;
        this.mOutputPath = mOutputPath;
    }

    public GameInput load() {
        ArrayList<String> playerOrder = new ArrayList<>();
        ArrayList<Integer> coordinates = new ArrayList<>();
        List<String> moves = new ArrayList<>();
        int rounds = 0;
        int noPlayers = 0;
        int xDim = 0;
        int yDim = 0;
        try {
            FileSystem fs = new FileSystem(mInputPath, mOutputPath);
            xDim = fs.nextInt();
            yDim = fs.nextInt();
            map = new String[xDim][yDim];
            for(int i = 0; i < xDim; i++) {
                String line = fs.nextWord();
                for(int j = 0; j < yDim; j++) {
                    map[i][j] = Character.toString(line.charAt(j));
                }
            }
            noPlayers = fs.nextInt();
            for(int i = 0; i < noPlayers; i++) {
                String line = fs.nextWord();
                playerOrder.add(Character.toString(line.charAt(0)));
                coordinates.add(fs.nextInt());
                coordinates.add(fs.nextInt());
            }

            rounds = fs.nextInt();

            for(int i = 0; i < rounds; i++) {
                String line = fs.nextWord();
                for(int k = 0; k < line.length(); k++) {
                    moves.add(Character.toString(line.charAt(k)));
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return new GameInput(rounds,moves,playerOrder,map,coordinates);
    }
}
