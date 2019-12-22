package task4;

import util.Task;

import java.util.ArrayList;
import java.util.List;

import static util.Task.A;
import static util.Task.B;

class PasswordChecker {

    private final int lowerRange = 245318;
    private final int upperRange = 765747;
    private Task task;

    int countValidPasswords(Task task) {
        this.task = task;

        int validPasswordCounter = 0;

        for (int i = lowerRange; i <= upperRange; i++) {
            if (isValidPassword(i)) {
                validPasswordCounter++;
            }
        }

        return validPasswordCounter;
    }

    //Naming of variables can probably be improved here
    private boolean isValidPassword(int intPassword) {
        char[] password = Integer.toString(intPassword).toCharArray();
        List<Integer> adjacentCounters = new ArrayList<>();

        int counter = 1;

        for (int i = 1; i < password.length; i++) {
            char previous = password[i - 1];
            char current = password[i];

            //"Only increment" rule:
            if (current < previous) {
                return false;
            }

            //"Adjacent duplicates" rule:
            //Keep track of how many times in a row a number appears.
            if (current == previous) {
                counter++;
            } else {
                adjacentCounters.add(counter);
                counter = 1;
            }
        }

        //Add last number series to list as well.
        adjacentCounters.add(counter);

        //"Adjacent duplicates" rule cont.:
        //After all numbers have been iterated, match against the specific task's condition:
        //Task A requires at least 2 of the same number to be adjacent
        //Task B requires exactly 2 of the same number to be adjacent
        for (Integer numberAdjacentCount : adjacentCounters) {
            if (task == A && numberAdjacentCount > 1 ||
                    task == B && numberAdjacentCount == 2) {
                return true;
            }
        }

        return false;
    }


}
