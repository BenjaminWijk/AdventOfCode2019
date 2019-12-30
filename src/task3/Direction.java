package task3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This enum exists for a few reasons:
 * 1. Clarifies which directions are possible to receive.
 * 2. Will actively crash if we receive any other value (otherwise Stepper class will just silently ignore issue)
 * 3. I like enums.
 */
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
