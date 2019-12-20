package task3;

import java.util.ArrayList;
import java.util.List;

class Wire {

    //TODO replace with the raw strings of direction + distance.
    private final List<DirectionAndDistance> directionAndDistances;
    private final List<Point> points = new ArrayList<>();

    Wire(List<DirectionAndDistance> directionAndDistances) {
        this.directionAndDistances = directionAndDistances;
    }

    Integer getStepsToPoint(Point point) {
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

    void calculatePoints() {
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

    List<Point> getPoints() {
        return points;
    }
}