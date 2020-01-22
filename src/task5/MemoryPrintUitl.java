package task5;

import java.util.Map;
import java.util.StringJoiner;

public class MemoryPrintUitl {

    Map<Integer, Integer> memory;
    int pointer;
    int pointerPointer; // :D :D :D

    private String memoryString;
    private String pointerString;

    private MemoryPrintUitl(Map<Integer, Integer> memory, int pointer) {
        this.memory = memory;
        this.pointer = pointer;
    }

    public static void printMemory(Map<Integer, Integer> memory, int pointer) {
        MemoryPrintUitl printer = new MemoryPrintUitl(memory, pointer);

        printer.createMemoryString();
        printer.createPointerString();

        printer.printResult();
    }

    private void createMemoryString() {
        StringJoiner sj = new StringJoiner(", ");
        int counter = 0;
        for (Integer value : memory.values()) {
            if (counter == pointer) {
                pointerPointer = sj.toString().length();
                if(counter != 0){
                    pointerPointer += 2;
                }
            }
            counter++;

            sj.add(value.toString());
        }
        memoryString = sj.toString();
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

