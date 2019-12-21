package task3;

class Stepper {
    private int x;
    private int y;
    private int totalSteps;

    void step(String direction) {
        switch (direction) {
            case "U":
                y += 1;
                break;
            case "D":
                y -= 1;
                break;
            case "L":
                x -= 1;
                break;
            case "R":
                x += 1;
                break;
        }

        totalSteps++;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getTotalSteps() {
        return totalSteps;
    }
}
