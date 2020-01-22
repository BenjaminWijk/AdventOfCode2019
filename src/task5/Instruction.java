package task5;

public class Instruction {
    Operation op;
    ValuesAndStoreLocation<Mode> modes;

    public Instruction(Operation op, ValuesAndStoreLocation<Mode> modes) {
        this.op = op;
        this.modes = modes;
    }

    public static Instruction decodeInstruction(Integer intInstruction) {
        String strInstruction = intInstruction.toString();
        Operation op = getOperation(strInstruction);

        if (strInstruction.length() <= 2) {
            return createDefaultInstruction(op, strInstruction);
        } else {

            ValuesAndStoreLocation<Mode> modes = getModes(op, strInstruction);

            return new Instruction(op, modes);
        }
    }

    private static Operation getOperation(String strInstruction) {
        if (strInstruction.length() <= 2) {
            return Operation.of(strInstruction);
        } else {
            String last2Chars = strInstruction.substring(strInstruction.length() - 2);
            return Operation.of(last2Chars);
        }
    }

    private static ValuesAndStoreLocation<Mode> getModes(Operation op, String strInstruction) {
        String modeStr = strInstruction.substring(0, strInstruction.length() - 2);
        ValuesAndStoreLocation<Mode> modes = new ValuesAndStoreLocation<>();

        //Add modes in reverse order
        for (int i = modeStr.length() - 1; i >= 0; i--) {
            modes.add(Mode.of("" + modeStr.charAt(i)));
        }

        //Pad out if "missing values" (for example: 101 represents 0101)
        while (modes.size() < op.stepsToIncrement - 1) {
            modes.add(Mode.POSITION);
        }

        return modes;
    }

    private static Instruction createDefaultInstruction(Operation op, String defaultInstruction) {
        ValuesAndStoreLocation<Mode> modes = new ValuesAndStoreLocation<>();

        for (int i = 0; i < op.stepsToIncrement - 1; i++) {
            modes.add(Mode.POSITION);
        }

        return new Instruction(op, modes);
    }
}
