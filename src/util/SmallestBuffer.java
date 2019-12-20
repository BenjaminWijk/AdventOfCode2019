package util;

//There are probably tons of better ways to implement this,
//But I was bored and wanted to make my own impl.

public class SmallestBuffer {
    private Integer[] buffer;
    boolean haveNullPositions = true;

    public SmallestBuffer() {
        buffer = new Integer[1];
    }

    public SmallestBuffer(int bufferSize) {
        buffer = new Integer[bufferSize];
    }

    public void add(int value) {

        if (haveNullPositions) {
            for (int i = 0; i < buffer.length; i++) {
                if (buffer[i] == null) {
                    buffer[i] = value;
                    return;
                }
            }
        }

        haveNullPositions = false;

        for (int i = 0; i < buffer.length; i++) {
            LargestValue largest = getLargestValue();

            if (value < largest.value) {
                buffer[largest.position] = value;
            }
        }
    }

    public LargestValue getLargestValue() {
        Integer largest = null;
        Integer pos = null;

        for (int i = 0; i < buffer.length; i++) {
            if (largest == null || buffer[i] > largest) {
                largest = buffer[i];
                pos = i;
            }
        }

        return new LargestValue(largest, pos);
    }

    public Integer getSmallestValue() {
        Integer smallest = null;

        for (int i = 0; i < buffer.length; i++) {
            if(smallest == null || buffer[i] < smallest){
                smallest = buffer[i];
            }
        }

        return smallest;
    }

    public Integer[] getValues() {
        return buffer;
    }

    private class LargestValue {
        int value;
        int position;

        private LargestValue(int value, int position) {
            this.value = value;
            this.position = position;
        }
    }
}
