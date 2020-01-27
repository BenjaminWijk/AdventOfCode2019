package task8;

import util.FileUtil;
import util.Task;

public class Main {

    //Task A: 1560
    //Task B: UGCUH
    public static void main(String[] args) {

        //Task A
//        performTask(3,2,"inputTestA.txt", Task.A);
//        performTask(25,6,"input.txt", Task.A);

        //Task B
//        performTask(2,2,"inputTestB.txt", Task.B);
        performTask(25,6,"input.txt", Task.B);
    }

    private static void performTask(int width, int height, String filename, Task task) {
        String exampleInput = FileUtil.readLinesFromFile(8, filename).get(0);
        SIFDecoder decoder = new SIFDecoder(width, height, exampleInput);
        decoder.createLayers();
        switch(task){
            case A:
                System.out.println(decoder.getMultipliedDigitsValue());
                break;
            case B:
                decoder.renderImage();
                break;
        }
    }
}
