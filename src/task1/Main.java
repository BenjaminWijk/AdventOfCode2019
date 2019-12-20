package task1;

import util.FileUtil;

import java.io.IOException;
import java.util.List;

public class Main {

    //Value for task A: 3256114
    //Value for task B: 4881302
    public static void main(String[] args) throws IOException {
        List<Integer> fuelInput = FileUtil.readLinesFromFileAsIntegers(1,"input.txt");

        FuelCalculator taskACalculator = new FuelCalculator(fuelInput, Task.A);
        FuelCalculator taskBCalculator = new FuelCalculator(fuelInput, Task.B);

        System.out.println(taskACalculator.calculateTotal());
        System.out.println(taskBCalculator.calculateTotal());
    }
}
