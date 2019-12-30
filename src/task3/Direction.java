package task3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This enum exists for 3 very important reasons:
 * 1. Code now defines which directions are allowed, instead of just trusting a comment.
 * 2. Illegal values are handled at conversion stage, no longer the responsibility of other classes.
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
        Direction direction = charToDirection.get(singleLetter);

        if (direction == null) {
            throw new NullPointerException("Unrecognized letter: " + singleLetter);
        }

        return direction;
    }
}
