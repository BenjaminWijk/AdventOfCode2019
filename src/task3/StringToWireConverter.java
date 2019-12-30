package task3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class StringToWireConverter {

    /**
     * create all wires
     *
     * @param csvStrings csv strings with direction and distance.
     * @return wire objects, 1 for each string in list.
     */
    static List<Wire> buildWirePaths(List<String> csvStrings) {
        return csvStrings.stream()
                .map(StringToWireConverter::buildWirePath)
                .collect(Collectors.toList());
    }


    /**
     * Builds wire object, and all the helping DirectionAndDistance objects.
     *
     * @param csvString csv string for a single wire.
     * @return wire object that has been initialized and has calculated all of its coordinates.
     */
    private static Wire buildWirePath(String csvString) {
        Wire wire = new Wire(
                Arrays.stream(csvString.split(","))
                        .map(str -> new DirectionAndDistance(
                                str.charAt(0),
                                Integer.valueOf(str.substring(1))))
                        .collect(Collectors.toList()));

        wire.calculatePoints();

        return wire;
    }

}
