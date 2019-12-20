package task1;

import java.util.List;

class FuelCalculator {

    private int totalFuel;
    private final Task task;
    private final List<Integer> massList;

    FuelCalculator(List<Integer> massList, Task task) {
        this.massList = massList;
        this.task = task;
    }

    int calculateTotal() {
        massList.forEach(this::calculateFuelAndAddToTotal);

        return totalFuel;
    }

    private void calculateFuelAndAddToTotal(int mass) {
        //Integer calculation will be rounded down by default.
        int fuel = mass / 3;
        fuel -= 2;

        if (fuel > 0) {
            totalFuel += fuel;

            //Task B? Add the fuel requirement of our fuel to totalFuel.
            if (task == Task.B) {
                calculateFuelAndAddToTotal(fuel);
            }
        }
    }


}
