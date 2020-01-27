package task7;

import util.FileUtil;
import util.Task;

import java.util.*;

public class Main {

    //Task A: 24625
    //Task B: 36497698
    public static void main(String[] args) {
        List<Integer> memory = FileUtil.readCsvAsIntegers(7, "input.txt");
        List<int[]> permutationsA = getPermutationArrays(FileUtil.readLinesFromFile(7, "permutationsA.txt"));
        List<int[]> permutationsB = getPermutationArrays(FileUtil.readLinesFromFile(7, "permutationsB.txt"));

        //Task A
        AmplifierController controllerA = new AmplifierController(getMemory(memory), permutationsA, Task.A);
        System.out.println("Task A");
        System.out.println("Thruster signal: " + controllerA.findHighestSignal());
        System.out.println();

        //Task B
        AmplifierController controllerB = new AmplifierController(getMemory(memory), permutationsB, Task.B);
        System.out.println("Task B");
        System.out.println("Thruster signal: " + controllerB.findHighestSignal());

    }

    private static List<int[]> getPermutationArrays(List<String> permutationStrings) {
        List<int[]> pArr = new ArrayList<>();

        for (String s : permutationStrings) {
            char[] chars = s.toCharArray();
            int[] numbers = new int[s.length()];
            for (int i = 0; i < s.length(); i++) {
                numbers[i] = Character.getNumericValue(chars[i]);
            }
            pArr.add(numbers);
        }

        return pArr;
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
