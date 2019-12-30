package task3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Direction {
    UP('U'),
    DOWN('D'),
    LEFT('L'),
    RIGHT('R');

    private char singleLetter;
    private static Map<Character, Direction> charToDirection = new HashMap<>();

    static {
        Arrays.stream(Direction.values()).forEach(direction ->
                charToDirection.put(direction.singleLetter, direction));
    }

    Direction(char singleLetter) {
        this.singleLetter = singleLetter;
    }

    public static Direction of(char singleLetter) {
        return charToDirection.get(singleLetter);
    }

}
