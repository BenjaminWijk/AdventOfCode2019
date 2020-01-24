package task7;

import task7.intcomputer.Program;
import util.CyclicList;
import util.Task;

import java.util.*;

public class AmplifierController {

    private final Map<Integer, Integer> memory;
    private final List<int[]> permutations;
    private final Task task;

    public AmplifierController(Map<Integer, Integer> memory, List<int[]> permutations, Task task) {
        this.memory = memory;
        this.permutations = permutations;
        this.task = task;
    }

    int findHighestSignal() {
        int highest = 0;
        String highestPerm = "";

        for (int[] permutation : permutations) {
            int output;

            switch (task) {
                default:
                case A:
                    output = getOutput(permutation);
                    break;
                case B:
                    output = getFeedbackLoopOutput(permutation);
                    break;
            }

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

        for (Amplifier amp : amplifiers) {
            nextInput = amp.calculateOutput(nextInput);
        }
        return nextInput;
    }

    private int getFeedbackLoopOutput(int[] permutation) {
        CyclicList<Amplifier> amplifiers = createAmplifiers(permutation);
        int nextInput = 0;

        while(true){
            Amplifier amp = amplifiers.getNext();
            amp.calculateOutput(nextInput);
            if(amp.finished()){
                break;
            }
        }

        return nextInput;
    }

    private String permutationToString(int[] perm) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(perm).forEach(sb::append);

        return sb.toString();
    }

    private Map<Integer, Integer> getMemoryCopy() {
        return new HashMap<>(memory);
    }


    private CyclicList<Amplifier> createAmplifiers(int[] permutation) {
        CyclicList<Amplifier> amplifiers = new CyclicList<>();

        for (int i : permutation) {
            amplifiers.add(new Amplifier(i));
        }

        return amplifiers;
    }

    private class Amplifier {
        Map<Integer, Integer> memory;
        int setting;
        Program program;

        public Amplifier(int setting) {
            this.setting = setting;
            this.memory = getMemoryCopy();
        }

        public int calculateOutput(int input) {
            program = new Program(memory, input, setting, false);
            return program.performInstructions();
        }

        public boolean finished(){
            return program != null && program.finished;
        }
    }
}
