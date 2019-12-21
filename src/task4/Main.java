package task4;

import util.Task;

public class Main {

    //Task A: 1079
    //Task B: 699
    public static void main(String[] args) {
        PasswordChecker pwChecker = new PasswordChecker();

        System.out.println(pwChecker.countValidPasswords(Task.A));
        System.out.println(pwChecker.countValidPasswords(Task.B));

    }
}
