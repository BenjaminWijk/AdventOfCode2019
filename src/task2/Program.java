package task2;

import java.util.Map;

class Program {

    private final Map<Integer, Integer> memory;
    private int pointer;

    Program(Map<Integer, Integer> memory) {
        this.memory = memory;
    }

    int performInstructions(int noun, int verb) {
        memory.put(1, noun);
        memory.put(2, verb);

        while (true) {
            Operation op = Operation.of(get(pointer));

            switch (op) {
                case ADD:
                    add(getValuesAndResultPointer());
                    break;
                case MULTIPLY:
                    multiply(getValuesAndResultPointer());
                    break;
                case FINISHED:
                    return get(0);
            }

            incrementPointer();
        }
    }

    private void add(ValuesAndPointer vap) {
        int sum = vap.first + vap.second;
        memory.put(vap.toPosition, sum);
    }

    private void multiply(ValuesAndPointer vap) {
        int product = vap.first * vap.second;
        memory.put(vap.toPosition, product);
    }

    private Integer get(Integer address) {
        return memory.get(address);
    }

    private void incrementPointer() {
        pointer += 4;
    }

    private ValuesAndPointer getValuesAndResultPointer() {
        //Double "get" because we first retrieve address to look at,
        //and then we retrieve the value of the address.
        return new ValuesAndPointer(
                get(get(pointer + 1)),
                get(get(pointer + 2)),
                get(pointer + 3)
        );
    }




}
