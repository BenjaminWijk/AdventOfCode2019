package task5;

import java.util.Arrays;
import java.util.HashMap;

public enum Mode {
    POSITION(0),
    IMMEDIATE(1);

    int code;

    static HashMap<Integer, Mode> intToMode = new HashMap<>();

    static {
        Arrays.stream(values()).forEach(mode ->
                intToMode.put(mode.code, mode));
    }

    Mode(int code) {
        this.code = code;
    }

    public static Mode of(Integer code) {
        return intToMode.get(code);
    }

    public static Mode of(String code) {
        return of(Integer.parseInt(code));
    }
}
