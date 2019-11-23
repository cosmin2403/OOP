package Map;

import java.util.ArrayList;

public class Map {

    private static ArrayList<ArrayList<String>> map;
    private static Map instanceOfMap = null;

    private Map() {
        map = new ArrayList<>();
    }

    public static Map getInstance() {
        if(instanceOfMap == null) {
            instanceOfMap = new Map();
        }
        return instanceOfMap;
    }

    public void addCell(int x, int y, String cell) {
        map.get(x).set(y, cell);
    }

    public static ArrayList<ArrayList<String>> getMap() {
        return map;
    }

}
