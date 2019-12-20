package task3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//TODO: remove DirectionAndDistance POJO, just let the Wire class split the direction and distance.
public class StringToWireConverter {

    public static List<Wire> getWirePaths(List<String> csvStrings){
        return csvStrings.stream()
                .map(StringToWireConverter::getWirePath)
                .collect(Collectors.toList());
    }

    private static Wire getWirePath(String csvString) {
        List<DirectionAndDistance> ddList =
                Arrays.stream(csvString.split(","))
                        .map(StringToWireConverter::stringToDirectionAndDistance)
                        .collect(Collectors.toList());

        Wire wire = new Wire(ddList);
        wire.calculatePoints();

        return wire;
    }

    private static DirectionAndDistance stringToDirectionAndDistance(String str) {
        return new DirectionAndDistance(
                Direction.of(str.substring(0, 1)),
                Integer.valueOf(str.substring(1)));
    }


}
