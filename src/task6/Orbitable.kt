package task6

class Orbitable {
    //Will be non-null on all orbitables, except center of mass
    var parent: Orbitable? = null

    var numberOfOrbits: Int = -1


    //Total direct and indirect orbits are the same as the number of "connected jumps" to center of mass.
    fun calcNumberOfOrbits(): Int {
        if (alreadyCalculated()) {
            return numberOfOrbits
        }

        numberOfOrbits = if (isCenterOfMass())
            0
        else
            parent!!.calcNumberOfOrbits() + 1

        return numberOfOrbits
    }

    private fun isCenterOfMass(): Boolean = parent == null
    private fun alreadyCalculated(): Boolean = numberOfOrbits >= 0
}