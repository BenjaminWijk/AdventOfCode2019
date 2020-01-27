package task8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Space Image Format Decoder
 */
public class SIFDecoder {

    private final int width;
    private final int height;
    private final int totalPixels;
    private final String imageData;
    private List<Layer> layers;

    public SIFDecoder(int width, int height, String imageData) {
        this.width = width;
        this.height = height;
        this.totalPixels = width * height;
        this.imageData = imageData;
    }

    public void createLayers() {
        List<String> data = getLayerData(imageData);
        layers = data.stream().map(layerData -> new Layer(width, layerData))
                .collect(Collectors.toList());
    }

    public int getMultipliedDigitsValue() {
        Layer fewestZeroes = null;
        for (Layer layer : layers) {
            layer.countDigits();

            if (fewestZeroes == null ||
                    layer.getDigitCount(0) < fewestZeroes.getDigitCount(0)) {
                fewestZeroes = layer;
            }
        }

        if (fewestZeroes == null) {
            throw new NullPointerException("no layer found");
        }

        return fewestZeroes.getDigitCount(1) * fewestZeroes.getDigitCount(2);
    }

    public void renderImage() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                renderPixel(row, col);
            }
            System.out.print("\n");
        }
    }

    private void renderPixel(int row, int column) {
        int layerDepth = 0;
        PixelColor color;

        do {
            Layer layer = layers.get(layerDepth++);
            color = layer.getPixelColor(row, column);

        } while (color == PixelColor.TRANSPARENT);

        System.out.print(color.getSymbol());
    }

    private List<String> getLayerData(String imageData) {
        List<String> dataList = new ArrayList<>();

        int index = 0;
        while (index < imageData.length()) {
            dataList.add(imageData.substring(index, index + totalPixels));
            index += totalPixels;
        }

        return dataList;
    }

}
