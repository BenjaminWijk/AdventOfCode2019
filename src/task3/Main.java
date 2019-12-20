package task3;

import util.FileUtil;

import java.util.List;

//The one upside to my solution is that it supports more than 2 wires.
//Downside is that it feels somewhat slow (takes 1-2 sec to run on my comp)
//Should probably base initial search of intersections on lines, rather than points. Or something.
public class Main {

    //Task A: 2050
    //Task B: 21666
    public static void main(String[] args) {
        List<String> csvStrings = FileUtil.readLinesFromFile(3, "input.txt");
        List<Wire> wires = StringToWireConverter.getWirePaths(csvStrings);

        CrossedWires cw = new CrossedWires(wires);
        cw.populateIntersectionMap();

        //Task A
        System.out.println(cw.getClosestIntersection());

        //Task B
        System.out.println(cw.getFewestStepsIntersection());
    }
}
