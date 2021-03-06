package task7.intcomputer;

import util.intcomputer.MemoryPrinter;

import java.util.Map;

/**
 * Ye who enter: beware. This implementation is a bit of a monstrosity.
 */
public class Program {

    private final Map<Integer, Integer> memory;
    private int pointer;

    private boolean hasWrittenSetting = false;
    public int input;
    private int setting;
    public int output = -1;
    public boolean finished = false;

    private boolean debugPrint;

    public Program(Map<Integer, Integer> memory, int input, int setting, boolean debugPrint) {
        this.memory = memory;
        this.input = input;
        this.setting = setting;
        this.debugPrint = debugPrint;
    }

    public int performInstructions() {
        while (true) {
            if (debugPrint) {
                MemoryPrinter.printMemory(memory, pointer);
            }

            Instruction instruction = Instruction.decodeInstruction(get(pointer));
            ValuesAndStoreLocation<Integer> vasl = getFinalValues(instruction);

            switch (instruction.op) {
                case ADD:
                    add(vasl);
                    break;
                case MULTIPLY:
                    multiply(vasl);
                    break;
                case SAVE:
                    save();
                    break;
                case OUTPUT:
                    return output(instruction);
                case JUMP_IF_TRUE:
                    jumpIfTrue(instruction);
                    break;
                case JUMP_IF_FALSE:
                    jumpIfFalse(instruction);
                    break;
                case LESS_THAN:
                    lessThan(vasl);
                    break;
                case EQUALS:
                    performEquals(vasl);
                    break;
                case FINISHED:
                    finished = true;
                    return output;
            }
        }
    }


    private void add(ValuesAndStoreLocation<Integer> vasl) {
        int total = 0;
        for (int value : vasl) {
            total += value;
        }

        log("Add: storing " + total + " at location " + vasl.storeLocation);

        write(vasl.storeLocation, total);
        incrementPointer(Operation.ADD.stepsToIncrement);
    }

    private void multiply(ValuesAndStoreLocation<Integer> vasl) {
        int total = vasl.first();
        for (int i = 1; i < vasl.size(); i++) {
            total *= vasl.get(i);
        }

        log("Multiply: storing " + total + " at location " + vasl.storeLocation);

        write(vasl.storeLocation, total);
        incrementPointer(Operation.MULTIPLY.stepsToIncrement);
    }

    private void save() {
        int memLoc = get(pointer + 1);

        //Task7
        int value = hasWrittenSetting ? input : setting;
        hasWrittenSetting = true;

        log("Writing " + value + " to location " + memLoc);

        write(memLoc, value);
        incrementPointer(Operation.SAVE.stepsToIncrement);
    }

    private int output(Instruction instruction) {
        int value = getWithMode(instruction.modes.first(), pointer + 1);

        log("Outputting " + value);

        output = value;
        incrementPointer(Operation.OUTPUT.stepsToIncrement);
        return value;
    }

    private void jumpIfTrue(Instruction instruction) {
        int value = getWithMode(instruction.modes.first(), pointer + 1);
        if (value != 0) {
            pointer = getWithMode(instruction.modes.last(), pointer + 2);
            log("Jump if true: Jumping to " + pointer);
        } else {
            log("Jump if true: False, skip jump");
            incrementPointer(Operation.JUMP_IF_TRUE.stepsToIncrement);
        }
    }


    private void jumpIfFalse(Instruction instruction) {
        int value = getWithMode(instruction.modes.first(), pointer + 1);
        if (value == 0) {
            pointer = getWithMode(instruction.modes.last(), pointer + 2);
            log("Jump if false: Jumping to " + pointer);
        } else {
            log("Jump if false: True, skip jump");
            incrementPointer(Operation.JUMP_IF_TRUE.stepsToIncrement);
        }
    }

    private void lessThan(ValuesAndStoreLocation<Integer> vasl) {
        int value = 0;

        if (vasl.first() < vasl.last()) {
            value = 1;
            log("Less than: Value is less than, storing 1 at " + vasl.storeLocation);
        } else {
            log("Less than: Value is not less than, storing 0 at " + vasl.storeLocation);
        }
        write(vasl.storeLocation, value);

        incrementPointer(Operation.LESS_THAN.stepsToIncrement);
    }

    private void performEquals(ValuesAndStoreLocation<Integer> vasl) {
        int value = 0;
        if (vasl.first().equals(vasl.last())) {
            value = 1;
            log("Equals: Value is equal, storing 1 at " + vasl.storeLocation);
        } else {
            log("Equals: Value is not equal, storing 0 at " + vasl.storeLocation);
        }
        write(vasl.storeLocation, value);

        incrementPointer(Operation.EQUALS.stepsToIncrement);
    }

    private void log(String toLog) {
        if (debugPrint) {
            System.out.println(toLog);
        }
    }

    private void write(Integer address, Integer value) {
        memory.put(address, value);
    }

    //Treats address as a pointer to memory location, i.e. performs get 2 times
    private Integer read(Integer address) {
        return get(get(address));
    }

    //gets value at memory location
    private Integer get(Integer address) {
        return memory.get(address);
    }

    private void incrementPointer(int steps) {
        pointer += steps;
    }

    private int getWithMode(Mode mode, int address) {
        switch (mode) {
            case POSITION:
                return read(address);
            case IMMEDIATE:
                return get(address);
        }
        throw new IllegalArgumentException();
    }

    private ValuesAndStoreLocation<Integer> getFinalValues(Instruction instruction) {
        ValuesAndStoreLocation<Integer> vasl = new ValuesAndStoreLocation<>();

        //Last value is used for storage etc. Handling varies from opcode to opcode
        vasl.setStoreLocation(get(pointer + instruction.modes.size()));

        for (int i = 0; i < instruction.modes.size() - 1; i++) {
            Mode mode = instruction.modes.get(i);

            int localPointer = pointer + 1 + i;
            switch (mode) {
                case POSITION:
                    vasl.add(read(localPointer));
                    break;
                case IMMEDIATE:
                    vasl.add(get(localPointer));
                    break;
            }
        }

        return vasl;
    }
}
