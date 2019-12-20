package task3;

import java.util.HashMap;
import java.util.Map;

public class Point {

    private int x;
    private int y;

    //Points are used to show which wire has visited a coordinate.
    //Using cache to get easy references to the same points.
    private static Map<String, Point> pointCache = new HashMap<>();

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point get(int x, int y){
        Point cached = pointCache.get(getCacheString(x,y));
        if(cached != null) {
            return cached;
        }

        Point point = new Point(x,y);
        pointCache.put(getCacheString(x,y), point);

        return point;
    }

    private static String getCacheString(int x, int y){
        return "" + x + "," + y;
    }

    private static int getDistance(Point p, Point q) {
        return Math.abs(p.x - q.x) +
                Math.abs(p.y - q.y);
    }

    public int getDistance(Point point){
        return getDistance(this, point);
    }
}