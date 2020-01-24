package task3;

class Stepper {
    public int x;
    public int y;
    private int totalSteps;

    void step(Direction direction) {
        direction.getStepInstruction().accept(this);
        totalSteps++;
    }

    int getTotalSteps() {
        return totalSteps;
    }
}
