package task3;

import util.FileUtil;

import java.util.List;

//This takes about 1.2 sec for me to run, which feels iffy.
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
