package task5;

import util.intcomputer.MemoryPrinter;

import java.util.Map;

/**
 * Ye who enter: beware. This implementation is a bit of a monstrosity.
 */
class Program {

    private final Map<Integer, Integer> memory;
    private int pointer;

    private int input;

    private boolean debugPrint;

    Program(Map<Integer, Integer> memory, int input, boolean debugPrint) {
        this.memory = memory;
        this.input = input;
        this.debugPrint = debugPrint;
    }

    void performInstructions() {
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
                    output(instruction);
                    break;
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
                    return;
            }
        }
    }


    private void add(ValuesAndStoreLocation<Integer> vasl) {
        int total = 0;
        for (int value : vasl) {
            total += value;
        }

        if (debugPrint) {
            System.out.println("Add: storing " + total + " at location " + vasl.storeLocation);
        }

        write(vasl.storeLocation, total);
        incrementPointer(Operation.ADD.stepsToIncrement);
    }

    private void multiply(ValuesAndStoreLocation<Integer> vasl) {
        int total = vasl.first();
        for (int i = 1; i < vasl.size(); i++) {
            total *= vasl.get(i);
        }

        if (debugPrint) {
            System.out.println("Multiply: storing " + total + " at location " + vasl.storeLocation);
        }

        write(vasl.storeLocation, total);
        incrementPointer(Operation.MULTIPLY.stepsToIncrement);
    }

    private void save() {
        int memLoc = get(pointer + 1);

        if (debugPrint) {
            System.out.println("Writing " + input + " to location " + memLoc);
        }

        write(memLoc, input);
        incrementPointer(Operation.SAVE.stepsToIncrement);
    }

    private void output(Instruction instruction) {
        int value = getWithMode(instruction.modes.first(), pointer + 1);

        //Not a debug print
        System.out.println("Outputting " + value);

        incrementPointer(Operation.OUTPUT.stepsToIncrement);
    }

    private void jumpIfTrue(Instruction instruction) {
        int value = getWithMode(instruction.modes.first(), pointer + 1);
        if (value != 0) {
            pointer = getWithMode(instruction.modes.last(), pointer + 2);
        } else {
            incrementPointer(Operation.JUMP_IF_TRUE.stepsToIncrement);
        }
    }

    private void jumpIfFalse(Instruction instruction) {
        int value = getWithMode(instruction.modes.first(), pointer + 1);
        if (value == 0) {
            pointer = getWithMode(instruction.modes.last(), pointer + 2);
        } else {
            incrementPointer(Operation.JUMP_IF_TRUE.stepsToIncrement);
        }
    }

    private void lessThan(ValuesAndStoreLocation<Integer> vasl) {
        int value = 0;

        if (vasl.first() < vasl.last()) {
            value = 1;
        }
        write(vasl.storeLocation, value);

        incrementPointer(Operation.LESS_THAN.stepsToIncrement);
    }

    private void performEquals(ValuesAndStoreLocation<Integer> vasl) {
        int value = 0;
        if (vasl.first().equals(vasl.last())) {
            value = 1;
        }
        write(vasl.storeLocation, value);

        incrementPointer(Operation.EQUALS.stepsToIncrement);
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
        //TODO lookup if this is the case at end of Task B
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
