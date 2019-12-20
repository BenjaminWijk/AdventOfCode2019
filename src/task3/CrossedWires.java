package task3;

import util.SmallestBuffer;

import java.util.*;

/**
 * Should work with any number of wires greater than 1, not just two
 */
public class CrossedWires {

    private List<Wire> wires;
    private Map<Point, Set<Wire>> pointToWireIntersection = new HashMap<>();
    private List<Point> intersections;

    public CrossedWires(List<Wire> wires) {
        this.wires = wires;
    }

    public void populateIntersectionMap() {
        for (Wire wire : wires) {
            addToIntersectionMap(wire);
        }
    }

    public Integer getClosestIntersection() {
        Point centralPort = Point.get(0, 0);

        SmallestBuffer closest = new SmallestBuffer();
        for (Point intersection : getIntersections()) {
            closest.add(centralPort.getDistance(intersection));
        }

        return closest.getSmallestValue();
    }

    private List<Point> getIntersections() {
        if (intersections == null) {
            intersections = new ArrayList<>();

            pointToWireIntersection.forEach((k, v) -> {
                //More than 1 unique wire on same point == intersection
                if (v.size() > 1) {
                    intersections.add(k);
                }
            });
        }

        return intersections;
    }

    private void addToIntersectionMap(Wire wire) {
        for (Point point : wire.getPoints()) {
            if (pointToWireIntersection.get(point) == null) {
                Set wireSet = new HashSet();
                wireSet.add(wire);

                pointToWireIntersection.put(point, wireSet);
            } else {
                pointToWireIntersection.get(point).add(wire);
            }
        }
    }

    public int getFewestStepsIntersection() {
        List<Point> intersections = getIntersections();

        SmallestBuffer smallestForGrid = new SmallestBuffer();
        for(Point intersection: intersections){
            SmallestBuffer smallestForIntersection = new SmallestBuffer(2);

            for(Wire wire: pointToWireIntersection.get(intersection)) {
                smallestForIntersection.add(wire.getStepsToPoint(intersection));
            }

            Integer[] values = smallestForIntersection.getValues();
            smallestForGrid.add(values[0] + values[1]);
        }

        return smallestForGrid.getSmallestValue();
    }
}
