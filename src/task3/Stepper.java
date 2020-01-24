package task3;

class Stepper {
    public int x;
    public int y;
    private int totalSteps;

    void step(Direction direction) {
        switch (direction) {
            case UP:
                y += 1;
                break;
            case DOWN:
                y -= 1;
                break;
            case LEFT:
                x -= 1;
                break;
            case RIGHT:
                x += 1;
                break;
        }

        totalSteps++;
    }

    int getTotalSteps() {
        return totalSteps;
    }
}
