package task7;

import task7.intcomputer.Program;

import java.util.*;

public class AmplifierController {

    private final Map<Integer, Integer> memory;
    private final List<int[]> permutations;


    public AmplifierController(Map<Integer, Integer> memory, List<int[]> permutations) {
        this.memory = memory;
        this.permutations = permutations;
    }

    int findHighestSignal() {
        int highest = 0;
        String highestPerm = "";

        for (int[] permutation : permutations) {
            int output = getOutput(permutation);

            if (output > highest) {
                highest = output;
                highestPerm = permutationToString(permutation);
            }
        }
        System.out.println("PERMUTATION: " + highestPerm);
        return highest;
    }

    private int getOutput(int[] permutation) {
        List<Amplifier> amplifiers = createAmplifiers(permutation);
        int nextInput = 0;

        for(Amplifier amp: amplifiers){
            nextInput = amp.calculateOutput(nextInput);
        }
        return nextInput;
    }

    private String permutationToString(int[] perm){
        StringBuilder sb = new StringBuilder();
        Arrays.stream(perm).forEach(sb::append);

        return sb.toString();
    }

    private Map<Integer, Integer> getMemoryCopy() {
        return new HashMap<>(memory);
    }

    private List<Amplifier> createAmplifiers(int[] permutation) {
        List<Amplifier> amplifiers = new ArrayList<>();

        for (int i: permutation) {
            amplifiers.add(new Amplifier(i));
        }

        return amplifiers;
    }

    private class Amplifier {
        Map<Integer, Integer> memory;
        int setting;
        int output;

        public Amplifier(int setting) {
            this.setting = setting;
            this.memory = getMemoryCopy();
        }

        public int calculateOutput(int input) {
            return new Program(memory, input, setting, false)
                    .performInstructions();
        }
    }
}
