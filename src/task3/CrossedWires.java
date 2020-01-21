package task3;

import util.SmallestBuffer;

import java.util.*;

/**
 * Should work with any number of wires greater than 1, not just two
 */
class CrossedWires {

    private final List<Wire> wires;
    private final Map<Point, Set<Wire>> pointToWireIntersection = new HashMap<>();
    private List<Point> intersections;

    CrossedWires(List<Wire> wires) {
        this.wires = wires;
    }

    void populateIntersectionMap() {
        wires.forEach(this::addToIntersectionMap);
    }

    Integer getClosestIntersection() {
        Point centralPort = Point.get(0, 0);

        SmallestBuffer closest = new SmallestBuffer();
        getIntersections().forEach(intersection ->
                closest.add(centralPort.getDistance(intersection)));

        return closest.getSmallestValue();
    }

    private List<Point> getIntersections() {
        if (intersections == null) {
            intersections = new ArrayList<>();

            pointToWireIntersection.forEach((point, wires) -> {
                //More than 1 unique wire on same point == intersection
                if (wires.size() > 1) {
                    intersections.add(point);
                }
            });
        }

        return intersections;
    }

    private void addToIntersectionMap(Wire wire) {
        wire.getPoints().forEach(point -> {
            if (pointToWireIntersection.get(point) == null) {
                Set<Wire> wireSet = new HashSet<>();
                wireSet.add(wire);

                pointToWireIntersection.put(point, wireSet);
            } else {
                pointToWireIntersection.get(point).add(wire);
            }
        });
    }

    int getFewestStepsIntersection() {
        List<Point> intersections = getIntersections();

        SmallestBuffer smallestForGrid = new SmallestBuffer();
        for (Point intersection : intersections) {
            SmallestBuffer smallestForIntersection = new SmallestBuffer(2);

            pointToWireIntersection.get(intersection).forEach(wire ->
                    smallestForIntersection.add(wire.getStepsToPoint(intersection)));

            Integer[] values = smallestForIntersection.getValues();
            smallestForGrid.add(values[0] + values[1]);
        }

        return smallestForGrid.getSmallestValue();
    }
}
