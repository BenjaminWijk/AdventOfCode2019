package task2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Operation {
    ADD(1),
    MULTIPLY(2),
    FINISHED(99);

    int code;

    Operation(int code) {
        this.code = code;
    }

    private static Map<Integer, Operation> intToOperation = new HashMap<>();
    static {
        Arrays.stream(Operation.values()).forEach(operation ->
                intToOperation.put(operation.code, operation));
    }

    public static Operation of(int code) {
        return intToOperation.get(code);
    }
}