package task7.intcomputer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Operation {
    ADD(1, 4),
    MULTIPLY(2, 4),
    SAVE(3, 2),
    OUTPUT(4, 2),
    JUMP_IF_TRUE(5,3),
    JUMP_IF_FALSE(6, 3),
    LESS_THAN(7, 4),
    EQUALS(8, 4),
    FINISHED(99, 0);

    int code;
    int stepsToIncrement;

    Operation(int code, int stepsToIncrement) {
        this.code = code;
        this.stepsToIncrement = stepsToIncrement;
    }

    private static final Map<Integer, Operation> intToOperation = new HashMap<>();

    static {
        Arrays.stream(Operation.values()).forEach(operation ->
                intToOperation.put(operation.code, operation));
    }

    public static Operation of(String code){
        if(code.length() > 2){
            throw new IllegalArgumentException("String should be max 2 chars at this point");
        }

        //make into parsable int
        if(code.startsWith("0")){
            code = code.substring(1);
        }

        return of(Integer.parseInt(code));
    }

    public static Operation of(int code) {
        Operation op = intToOperation.get(code);

        if (op == null) {
            throw new IllegalArgumentException("Unrecognized opcode: " + code);
        }

        return op;
    }
}