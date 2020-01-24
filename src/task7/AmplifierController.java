package task7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmplifierController {

    private final Map<Integer, Integer> memory;
    private final List<int[]> permutations;


    public AmplifierController(Map<Integer, Integer> memory, List<int[]> permutations) {
        this.memory = memory;
        this.permutations = permutations;
    }

    int findHighestSignal() {
        int highest = 0;

        for (int[] permutation : permutations) {
            int output = getOutput(permutation);

            if (output > highest) {
                highest = output;
            }
        }

        return highest;
    }

    private int getOutput(int[] permutation) {
        List<Amplifier> amplifiers = createAmplifiers(permutation);

        return -1;
    }

    private Map<Integer, Integer> getMemoryCopy() {
        return new HashMap<>(memory);
    }

    private List<Amplifier> createAmplifiers(int[] permutation) {
        List<Amplifier> amplifiers = new ArrayList<>();

        //First always has 0 as input
        amplifiers.add(new Amplifier(permutation[0], 0));

        for (int i = 1; i < permutation.length; i++) {
            amplifiers.add(new Amplifier(i));
        }

        return amplifiers;
    }

    private class Amplifier {
        Map<Integer, Integer> memory;
        int setting;
        int input = -1;
        int output;

        public Amplifier(int setting) {
            this.setting = setting;
            this.memory = getMemoryCopy();
        }

        public Amplifier(int setting, int input) {
            this.setting = setting;
            this.input = input;
            this.memory = getMemoryCopy();
        }
    }
}
