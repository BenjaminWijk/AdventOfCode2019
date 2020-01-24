package task3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * This enum exists for 3 very important reasons:
 * 1. Code now defines which directions are allowed, instead of just trusting a comment.
 * 2. Illegal values are handled at conversion stage, no longer the responsibility of other classes.
 * 4. Handles stepper logic for each value, making it clearer what the difference is.
 * 3. I like enums.
 */
public enum Direction {
    UP('U', stepper -> stepper.y++),
    DOWN('D', stepper -> stepper.y--),
    LEFT('L', stepper -> stepper.x--),
    RIGHT('R', stepper -> stepper.x++);

    private char singleLetter;
    private final Consumer<Stepper> stepInstruction;

    private static Map<Character, Direction> charToDirection = new HashMap<>();

    static {
        Arrays.stream(Direction.values()).forEach(direction ->
                charToDirection.put(direction.singleLetter, direction));
    }

    Direction(char singleLetter, Consumer<Stepper> stepInstruction) {
        this.singleLetter = singleLetter;
        this.stepInstruction = stepInstruction;
    }

    public Consumer<Stepper> getStepInstruction() {
        return stepInstruction;
    }

    public static Direction of(char singleLetter) {
        return Optional.of(charToDirection.get(singleLetter))
                .orElseThrow(() -> new NullPointerException("Unrecognized letter: " + singleLetter));
    }
}
