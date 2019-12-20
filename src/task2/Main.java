package task2;

import util.FileUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    //Task A: 5290681
    //Task B: 5741
    public static void main(String[] args) {
        List<Integer> csvList = FileUtil.readCsvAsIntegers(2, "input.txt");

        //Task A
        System.out.println(new Program(getMemory(csvList)).performInstructions(12, 2));

        //Task B
        for (int noun = 0; noun<100; noun++) {
            for (int verb = 0; verb<100; verb++) {

                int output = new Program(getMemory(csvList)).performInstructions(noun, verb);
                if(output == 19690720){
                    System.out.println(100 * noun + verb);
                    return;
                }
            }
        }
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
