package task3;

class Stepper {
    private int x;
    private int y;
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
