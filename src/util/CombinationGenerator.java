package util;

/**
 * Taken from https://www.sanfoundry.com/java-program-generate-all-possible-combinations-given-list-numbers/
 */

//This is a java program to perform all permutation of given list of numbers of a specific length
public class CombinationGenerator {

    static void permute(int[] a, int k) {

        if (k == a.length) {
            for (int value : a) {
                System.out.print("" + value + "");
            }

            System.out.println();
        } else {
            for (int i = k; i < a.length; i++) {

                int temp = a[k];
                a[k] = a[i];
                a[i] = temp;

                permute(a, k + 1);

                temp = a[k];
                a[k] = a[i];
                a[i] = temp;

            }
        }
    }

    public static void main(String[] args) {
        int[] sequence = new int[]{5,6,7,8,9};

        permute(sequence, 0);
    }

}

