package task6

class Orbitable {
    //Will be set by all orbitables, except center of mass
    var parent: Orbitable? = null

    //-1 represents "not yet calculated"
    var noOfOrbits: Int = -1

    fun calcNumberOfOrbits(): Int {
        if (alreadyCalculated()) {
            return noOfOrbits
        }

        noOfOrbits = if (isCenterOfMass())
            0
        else
            parent!!.calcNumberOfOrbits() + 1
        
        return noOfOrbits
    }

    private fun isCenterOfMass(): Boolean = parent == null
    private fun alreadyCalculated(): Boolean = noOfOrbits >= 0

}