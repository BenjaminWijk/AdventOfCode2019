package task3;

import java.util.ArrayList;
import java.util.List;

public class Wire {

    //TODO replace with the raw strings of direction + distance.
    private List<DirectionAndDistance> directionAndDistances;
    private List<Point> points = new ArrayList<>();

    public Wire(List<DirectionAndDistance> directionAndDistances) {
        this.directionAndDistances = directionAndDistances;
    }

    public Integer getStepsToPoint(Point point){
        if (!points.contains(point)) {
            return null;
        }

        Stepper stepper = new Stepper();

        for (DirectionAndDistance dad : directionAndDistances) {
            for (int i = 0; i < dad.distance; i++) {
                stepper.step(dad.direction);

                Point currentPoint = Point.get(stepper.getX(), stepper.getY());
                if (currentPoint == point) {
                    return stepper.getTotalSteps();
                }
            }
        }

        throw new RuntimeException("Could not retrieve steps to point.");
    }

    public void calculatePoints() {
        Stepper stepper = new Stepper();

        for (DirectionAndDistance dad : directionAndDistances) {
            for (int i = 0; i < dad.distance; i++) {
                stepper.step(dad.direction);

                points.add(Point.get(
                        stepper.getX(),
                        stepper.getY()));
            }
        }
    }

    public List<Point> getPoints() {
        return points;
    }
}