package util.intcomputer;

import java.util.Map;
import java.util.StringJoiner;

public class MemoryPrinter {

    Map<Integer, Integer> memory;
    int pointer;
    int pointerPointer; // :D :D :D

    private String memoryString;
    private String pointerString;

    private MemoryPrinter(Map<Integer, Integer> memory, int pointer) {
        this.memory = memory;
        this.pointer = pointer;
    }

    public static void printMemory(Map<Integer, Integer> memory, int pointer) {
        MemoryPrinter printer = new MemoryPrinter(memory, pointer);

        printer.createMemoryString();
        printer.createPointerString();

        printer.printResult();
    }

    private void createMemoryString() {
        StringJoiner sj = new StringJoiner(", ");
        int counter = 0;
        for (Integer value : memory.values()) {
            if (counter == pointer) {
                updatePointerPointer(sj, counter);
            }
            counter++;

            sj.add(value.toString());
        }
        memoryString = sj.toString();
    }

    private void updatePointerPointer(StringJoiner sj, int counter) {
        pointerPointer = sj.toString().length();
        if(counter != 0){
            pointerPointer += 2;
        }
    }

    private void createPointerString() {
        pointerString = "-".repeat(Math.max(0, pointerPointer)) +
                "^ (" + pointer + ")";
    }


    private void printResult() {
        System.out.println();
        System.out.println();
        System.out.println(memoryString);
        System.out.println(pointerString);
    }
}

