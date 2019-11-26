package main;

import java.util.ArrayList;
import java.util.List;

public class GameInput {
    // Jucatorii
    private final List<String> mPlayersOrder;

    // Ce miscari se vor face
    private final List<String> moves;

    // Coordonatele initiale distribuite 2 cate 2.
    private final ArrayList<Integer> initialCoordinates;

    // Mapa din litere
    private String[][] map;

    // Nr. de runde.
    private int rounds;


    public ArrayList<Integer> getInitialCoordinates() {
        return initialCoordinates;
    }

    public String[][] getMap() {
        return map;
    }

    public GameInput(final int rounds, final List<String> moves,
                     final List<String> mPlayersOrder,
                     final String[][] map,
                     final ArrayList<Integer> initialCoordinates) {
        this.mPlayersOrder = mPlayersOrder;
        this.moves = moves;
        this.rounds = rounds;
        this.map = map;
        this.initialCoordinates = initialCoordinates;
    }

    public final List<String> getmPlayersOrder() {
        return mPlayersOrder;
    }

    public final List<String> getMoves() {
        return moves;
    }

    public final int getRounds() {
        return rounds;
    }
    public final boolean isValidInput() {
        boolean membersInstantiated = moves != null && mPlayersOrder != null;
        boolean membersNotEmpty =
                moves.size() > 0 && mPlayersOrder.size() > 0 && rounds > 0;
        return membersInstantiated && membersNotEmpty;
    }
}
