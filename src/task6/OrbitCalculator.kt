package task6

import java.lang.RuntimeException

class OrbitCalculator(val orbitStrings: List<String>) {

    private val orbitables: HashMap<String, Orbitable> = HashMap()
    var totalNoOfOrbits = 0

    fun connectOrbitables() {
        orbitStrings.forEach { orbString ->
            val sArr = orbString.split(")")
            createAndConnectOrbitables(sArr[0], sArr[1])
        }
    }

    fun calculateNoOfOrbits() {
        orbitables.values.forEach { orbitable ->
            totalNoOfOrbits += orbitable.calcNumberOfOrbits()
        }
    }

    fun getDistanceToSanta(): Int {
        val you = orbitables["YOU"]!!
        val santa = orbitables["SAN"]!!
        val closestParent = findClosestCommonParent(you, santa)

        val youDistToClosest = you.parent!!.noOfOrbits - closestParent.noOfOrbits
        val santaDistToClosest = santa.parent!!.noOfOrbits - closestParent.noOfOrbits

        return youDistToClosest + santaDistToClosest
    }

    private fun createAndConnectOrbitables(parent: String, child: String) {
        val childOrb = orbitables.computeIfAbsent(child) { Orbitable() }
        val parentOrb = orbitables.computeIfAbsent(parent) { Orbitable() }

        childOrb.parent = parentOrb
    }

    private fun findClosestCommonParent(first: Orbitable, second: Orbitable): Orbitable {
        val firstParents = ArrayList<Orbitable>()

        //Map up all parents for one of the two orbitables
        var firstParent = first.parent
        while (firstParent != null) {
            firstParents.add(firstParent)
            firstParent = firstParent.parent
        }

        //Compare second orbitable's parents with first orbitable's.
        //Since list is sorted closest to farther away, we can just return the first match
        var secondParent = second.parent
        while (secondParent != null) {
            if (firstParents.contains(secondParent))
                return secondParent
            secondParent = secondParent.parent
        }

        throw RuntimeException("Something has gone horribly wrong, no common parent found")
    }
}