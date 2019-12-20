package task3;

public class Stepper {
    private int x;
    private int y;
    private int totalSteps;

    public void step(Direction direction){
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTotalSteps() {
        return totalSteps;
    }
}
