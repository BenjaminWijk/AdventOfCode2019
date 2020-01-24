package task5;

import util.FileUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    //Task A: 14522484
    //Task B: 4655956
    public static void main(String[] args) {
        List<Integer> csvList = FileUtil.readCsvAsIntegers(5, "input.txt");

        //Task A
        System.out.println("Task A:");
        new Program(getMemory(csvList), 1, 1, false).performInstructions();

        //Task B
        System.out.println("\nTask B:");
        new Program(getMemory(csvList), 5,1, false).performInstructions();
    }

    //Need a fresh map for each attempt
    private static Map<Integer, Integer> getMemory(List<Integer> csvList) {
        Map<Integer, Integer> memory = new HashMap<>();
        for (int i = 0; i < csvList.size(); i++) {
            memory.put(i, csvList.get(i));
        }

        return memory;
    }


}
