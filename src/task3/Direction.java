package task3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Incredibly pointless enum in this case, as the shorthand is already quite obvious.
//Buuut I like my enums so.. here it is. : )
//TODO: remove this
public enum Direction {
    UP("U"),
    DOWN("D"),
    LEFT("L"),
    RIGHT("R");

    private String shorthand;

    private static Map<String, Direction> intToDirection = new HashMap<>();

    static {
        Arrays.stream(values()).forEach(direction ->
                intToDirection.put(direction.shorthand, direction));
    }

    Direction(String shorthand) {
        this.shorthand = shorthand;
    }

    public static Direction of(String shortHand){
        return intToDirection.get(shortHand);
    }
}