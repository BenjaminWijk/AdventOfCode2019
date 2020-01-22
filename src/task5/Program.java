package task5;

import java.util.Map;

class Program {

    private final Map<Integer, Integer> memory;
    private int pointer;
    private int input;

    Program(Map<Integer, Integer> memory) {
        this.memory = memory;
    }

    void performInstructions(int input) {
        this.input = input;

        while (true) {
            MemoryPrintUitl.printMemory(memory, pointer);

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
                    jumpIfTrue(vasl);
                    break;
                case JUMP_IF_FALSE:
                    jumpIfFalse(vasl);
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

        System.out.println("Add: storing " + total + " at location " + vasl.storeLocation);
        write(vasl.storeLocation, total);

        incrementPointer(Operation.ADD.stepsToIncrement);
    }

    private void multiply(ValuesAndStoreLocation<Integer> vasl) {
        int total = vasl.first();
        for (int i = 1; i < vasl.size(); i++) {
            total *= vasl.get(i);
        }

        System.out.println("Multiply: storing " + total + " at location " + vasl.storeLocation);
        write(vasl.storeLocation, total);

        incrementPointer(Operation.MULTIPLY.stepsToIncrement);
    }

    private void save() {
        int memLoc = get(pointer + 1);
        System.out.println("Writing " + input + " to location " + memLoc);
        write(memLoc, input);

        incrementPointer(Operation.SAVE.stepsToIncrement);
    }

    private void output(Instruction instruction) {
        int value = getWithMode(instruction.modes.get(0), pointer + 1);

        System.out.println("Outputting " + value);

        incrementPointer(Operation.OUTPUT.stepsToIncrement);
    }

    private int getWithMode(Mode mode, int address) {
        switch (mode) {
            case POSITION:
                return get(address);
            case IMMEDIATE:
                return address;
        }
        throw new IllegalArgumentException();
    }

    private void jumpIfTrue(ValuesAndStoreLocation<Integer> vasl) {
        if (vasl.first() != 0) {
            pointer = vasl.storeLocation;
        } else {
            incrementPointer(Operation.JUMP_IF_TRUE.stepsToIncrement);
        }
    }

    private void jumpIfFalse(ValuesAndStoreLocation<Integer> vasl) {
        if (vasl.first() == 0) {
            pointer = vasl.storeLocation;
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
    }

    private void performEquals(ValuesAndStoreLocation<Integer> vasl) {
        int value = 0;
        if (vasl.first().equals(vasl.last())) {
            value = 1;
        }
        write(vasl.storeLocation, value);
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
