package task7;

import util.FileUtil;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Integer> memory = FileUtil.readCsvAsIntegers(7, "inputTest.txt");
        List<int[]> permutations = getPermutationArrays(FileUtil.readLinesFromFile(7, "permutations.txt"));

        AmplifierController controller = new AmplifierController(getMemory(memory), permutations);
        System.out.println(controller.findHighestSignal());
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
