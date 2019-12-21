package task4;

import util.Task;

import java.util.Date;

public class Main {

    //Task A: 1079
    //Task B: 699
    public static void main(String[] args) {
        PasswordChecker pwChecker = new PasswordChecker();

        long before = new Date().getTime();
        System.out.println(pwChecker.countValidPasswords(Task.A));
        System.out.println(pwChecker.countValidPasswords(Task.B));
        long after = new Date().getTime();

        //~100 ms
        System.out.println("Milliseconds to perform: " + (after - before));
    }
}
