package task3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Direction {
    UP("U"),
    DOWN("D"),
    LEFT("L"),
    RIGHT("R");

    private String shortHand;

    private static Map<String, Direction> intToDirection = new HashMap<>();

    static {
        Arrays.stream(values()).forEach(direction ->
                intToDirection.put(direction.shortHand, direction));
    }

    Direction(String shortHand) {
        this.shortHand = shortHand;
    }

    public static Direction of(String shortHand){
        return intToDirection.get(shortHand);
    }
}