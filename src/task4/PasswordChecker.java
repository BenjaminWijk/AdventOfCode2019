package task4;

class PasswordChecker {

    private static final int PASSWORD_LENGTH = 6;
    private int lowerRange;
    private int upperRange;

    public PasswordChecker() {
        lowerRange = 245318;
        upperRange = 765747;
    }

    //Task A: 1079
    public static void main(String[] args) {
        //Task A
        System.out.println(new PasswordChecker().countValidPasswords());
    }

    private int countValidPasswords() {
        int validPasswords = 0;

        for (int i = lowerRange; i <= upperRange; i++) {

            if (isValidPassword(i)) {
                validPasswords++;
            }
        }

        return validPasswords;
    }


    private boolean isValidPassword(int password) {
        //Easiest to check, run first.
        if (notWithinRange(password)) {
            return false;
        }
        String strPass = "" + password;
        String previous = strPass.substring(0, 1);

        boolean duplicateAdjacent = false;
        boolean alwaysIncrementing = true;

        for (int i = 1; i < PASSWORD_LENGTH; i++) {
            if (strPass.substring(i, i + 1).equals(previous)) {
                duplicateAdjacent = true;
            }
            //Comparing strings, but should be fine since it's single digit
            if (strPass.substring(i, i + 1).compareTo(previous) < 0) {
                alwaysIncrementing = false;
            }

            previous = strPass.substring(i, i + 1);
        }

        return duplicateAdjacent && alwaysIncrementing;
    }

    private boolean notWithinRange(int password) {
        return !(password < upperRange
                && password > lowerRange);
    }


}
