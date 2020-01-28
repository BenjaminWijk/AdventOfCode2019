package task10

import java.lang.RuntimeException

class Angle(val value: Double) : Comparable<Angle> {

    //TODO simplify
    override fun compareTo(other: Angle): Int {
        val o = other.value
        
        if (o == value) return 0

        if (bothPositive(o)) {
            return if (value > o)
                1
            else
                -1
        }

        if (bothNegative(o)) {
            return if (value < o)
                -1
            else
                1
        }

        if (onlyoNegative(o)) {
            return -1
        }

        if (onlyvalueNegative(o)) {
            return 1
        }

        throw RuntimeException("Something went wrong with comparison. \nvalue value: $value\no value: $o")
    }

    private fun onlyoNegative(o: Double): Boolean = o < 0.0 && value >= 0.0
    private fun onlyvalueNegative(o: Double): Boolean = o >= 0.0 && value < 0.0

    private fun bothPositive(o: Double): Boolean = o >= 0.0 && value >= 0.0
    private fun bothNegative(o: Double): Boolean = o < 0.0 && value < 0.0

    override fun equals(other: Any?): Boolean {
        return if(other is Angle) {
            this.value == other.value
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}