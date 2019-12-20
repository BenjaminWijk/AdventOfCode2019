package task3;

import java.util.ArrayList;
import java.util.List;

public class Wire {

    private List<DirectionAndDistance> directionAndDistances;
    private List<Point> points = new ArrayList<>();

    public Wire(List<DirectionAndDistance> directionAndDistances) {
        this.directionAndDistances = directionAndDistances;
    }

    public Integer getStepsToPoint(Point point){
        if(!points.contains(point))
            return null;

        Stepper stepper = new Stepper();
        for (DirectionAndDistance dd : directionAndDistances) {
            for (int i = 0; i < dd.distance; i++) {
                stepper.step(dd.direction);

                if(Point.get(stepper.getX(),stepper.getY()) == point){
                    return stepper.getTotalSteps();
                }
            }
        }

        throw new RuntimeException("Could not retrieve steps to point.");
    }

    public void calculatePoints() {
        Stepper stepper = new Stepper();

        for (DirectionAndDistance dd : directionAndDistances) {
            for (int i = 0; i < dd.distance; i++) {
                stepper.step(dd.direction);
                addPoint(stepper.getX(), stepper. getY());
            }
        }
    }

    private void addPoint(int x, int y) {
        points.add(Point.get(x, y));
    }

    public List<Point> getPoints() {
        return points;
    }
}