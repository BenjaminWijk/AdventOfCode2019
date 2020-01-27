package task8

import kotlin.streams.toList

class SIFDecoder(val width: Int, val height: Int, val imageData: String) {
    val numberOfPixels = width * height

    lateinit var layers: List<Layer>

    fun createLayers() {
        val layerData: ArrayList<String> = separateDataByLayer()
        layers = layerData.stream().map { data ->
            Layer(width, data)
        }.toList()
    }


    fun getMultipliedDigitsValue(): Int {
        var fewestZeroes: Layer? = null
        layers.forEach { layer ->
            layer.countDigits()
            if (fewestZeroes == null) {
                fewestZeroes = layer
            }

            if (layer.getDigitCount(0) < fewestZeroes!!.getDigitCount(0)) {
                fewestZeroes = layer
            }
        }

        return fewestZeroes!!.getDigitCount(1) * fewestZeroes!!.getDigitCount(2)
    }

    fun renderImage() {
        for (row in 0 until height) {
            for (col in 0 until width) {
                renderPixel(row, col)
            }
            print("\n")
        }

    }

    private fun renderPixel(row: Int, col: Int) {
        var layerDepth = 0
        var color: PixelColor = PixelColor.TRANSPARENT

        do {
            var layer = layers[layerDepth++]
            color = layer.getPixelColor(row, col)

        } while (color == PixelColor.TRANSPARENT)

        print(color.symbol)
    }

    private fun separateDataByLayer(): ArrayList<String> {
        val layerData: ArrayList<String> = ArrayList()
        var index = 0

        while (index < imageData.length) {
            layerData.add(imageData.substring(index, index + numberOfPixels))
            index += numberOfPixels
        }

        return layerData
    }


}