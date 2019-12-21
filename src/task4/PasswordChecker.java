package task4;

import util.Task;

import java.util.ArrayList;
import java.util.List;

//I guess there's
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

    private boolean isValidPassword(int password) {
        return isOnlyIncrementing(password) &&
                isDuplicateRuleValid(password);
    }

    private boolean isDuplicateRuleValid(int password) {
        String strPass = "" + password;
        List<Integer> adjacentCounters = new ArrayList<>();

        int counter = 1;
        String previous = strPass.substring(0, 1);

        //Increment counter for each occurence in a row of a number.
        //As soon as the next number doesn't match, add the counter to a list and reset.
        //After we've checked the entire length, loop through list to check if the task's condition is met.
        for (int i = 1; i < strPass.length(); i++) {
            String current = strPass.substring(i, i + 1);

            if (current.equals(previous)) {
                counter++;
            } else {
                adjacentCounters.add(counter);
                counter = 1;
            }

            //Last value? add to counterList;
            if (i == strPass.length() - 1) {
                adjacentCounters.add(counter);
            }

            previous = current;
        }

        boolean foundValidAdjacent = false;
        for (Integer integer : adjacentCounters) {
            switch (task) {
                case A:
                    if (integer > 1) foundValidAdjacent = true;
                    break;
                case B:
                    if (integer == 2) foundValidAdjacent = true;
                    break;
            }
        }
        return foundValidAdjacent;
    }

    private boolean isOnlyIncrementing(int password) {
        String strPass = "" + password;
        String previous = strPass.substring(0, 1);

        for (int i = 1; i < strPass.length(); i++) {
            String current = strPass.substring(i, i + 1);
            if (current.compareTo(previous) < 0)
                return false;

            previous = current;
        }

        return true;
    }
}
