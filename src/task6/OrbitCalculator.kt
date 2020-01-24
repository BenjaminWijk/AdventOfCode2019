package task6

import java.lang.RuntimeException

class OrbitCalculator(val orbitStrings: List<String>) {

    private val orbitables: HashMap<String, Orbitable> = HashMap()
    var totalNumberOfOrbits = 0

    fun connectOrbitables() {
        orbitStrings.forEach { orbString ->
            val parentAndChild = orbString.split(")")

            val parentOrb = orbitables.computeIfAbsent(parentAndChild[0]) { Orbitable() }
            val childOrb = orbitables.computeIfAbsent(parentAndChild[1]) { Orbitable() }

            childOrb.parent = parentOrb
        }
    }

    fun calculateNoOfOrbits() {
        orbitables.values.forEach { orbitable ->
            totalNumberOfOrbits += orbitable.calcNumberOfOrbits()
        }
    }

    fun getDistanceBetweenYouAndSanta(): Int {
        val you = orbitables["YOU"]!!
        val santa = orbitables["SAN"]!!
        val closestParent = findClosestCommonParent(you, santa)

        val youDistanceToClosest = you.parent!!.numberOfOrbits - closestParent.numberOfOrbits
        val santaDistanceToClosest = santa.parent!!.numberOfOrbits - closestParent.numberOfOrbits

        return youDistanceToClosest + santaDistanceToClosest
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