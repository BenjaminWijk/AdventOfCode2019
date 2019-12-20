package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileUtil {

    //NOTE: assumes csv does not contain line breaks.
    public static List<Integer> readCsvAsIntegers(int taskNumber, String filename){
        String csv = getScanner(taskNumber, filename).nextLine();

        return Arrays.stream(csv.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    public static List<String> readLinesFromFile(int taskNumber, String filename){
        List<String> list = new ArrayList<>();

            Scanner sc = getScanner(taskNumber, filename);

            while(sc.hasNextLine()){
                list.add(sc.nextLine());
            }

        return list;
    }

    private static String getFilePath(int taskNumber, String filename){
        return "src/task" + taskNumber + "/" + filename;
    }

    public static List<Integer> readLinesFromFileAsIntegers(int taskNumber, String filename){
        List<String> stringList = readLinesFromFile(taskNumber, filename);

        return stringList.stream().map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    private static Scanner getScanner(int taskNumber, String filename){
        String filePath = getFilePath(taskNumber, filename);

        try {
            return new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("could not create scanner");
        }
    }
}
