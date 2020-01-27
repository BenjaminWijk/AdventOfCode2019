package task8

enum class PixelColor(val code: Int, val symbol:String){

    BLACK(0,"--"),
    WHITE(1,"||"),
    TRANSPARENT(2, "  ");

    companion object{
        val codeToPixelColor: HashMap<Int, PixelColor> = HashMap()

        init {
            values().forEach { color ->
                codeToPixelColor[color.code] = color
            }
        }

        fun of(code: Int) : PixelColor{
            return codeToPixelColor[code]!!
        }

        fun of(code: Char) : PixelColor{
            return of(Character.getNumericValue(code))
        }
    }

}