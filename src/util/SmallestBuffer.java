package util;

//There are probably tons of better ways to implement this,
//But I was bored and wanted to make my own impl.

/**
 * A buffer that keeps the smallest values it receives.
 * Size is customizable.
 * Note: can contain the same value multiple times.
 */
public class SmallestBuffer {
    private final Integer[] buffer;
    private boolean haveNullPositions = true;

    public SmallestBuffer() {
        buffer = new Integer[1];
    }

    public SmallestBuffer(int bufferSize) {
        if(bufferSize < 1){
            throw new IllegalArgumentException("Buffer size must be greater than 0");
        }

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

    private LargestValue getLargestValue() {
        Integer largest = null;
        Integer pos = null;

        for (int i = 0; i < buffer.length; i++) {
            if (largest == null || buffer[i] > largest) {
                largest = buffer[i];
                pos = i;
            }
        }

        if (largest == null) {
            throw new NullPointerException("Somehow received null when trying to find largestNumber");
        }

        return new LargestValue(largest, pos);
    }

    public Integer getSmallestValue() {
        Integer smallest = null;

        for (Integer value : buffer) {
            if (smallest == null || value < smallest) {
                smallest = value;
            }
        }

        return smallest;
    }

    public Integer[] getValues() {
        return buffer;
    }

    private class LargestValue {
        final int value;
        final int position;

        private LargestValue(int value, int position) {
            this.value = value;
            this.position = position;
        }
    }
}
