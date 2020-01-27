package task8

class Layer(private val width: Int, private val layerData: String){

    val digitCount: HashMap<Int, Int> = HashMap()

    fun countDigits(){
        layerData.toCharArray().forEach { c ->
            val digit = Character.getNumericValue(c)
            digitCount.merge(digit, 1, Integer::sum)
        }
    }

    fun getDigitCount(digit: Int): Int{
        return digitCount.getOrDefault(digit, 0)
    }

    fun getPixelColor(row: Int, col: Int) :PixelColor{
        val position = row * width + col
        return PixelColor.of(layerData[position])
    }

}