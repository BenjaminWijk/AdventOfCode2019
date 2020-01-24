package task6

import util.FileUtil

//Task A: 110190
//Task B: 343
fun main(){
    val orbits = FileUtil.readLinesFromFile(6, "input.txt")

    val orbitCalculator = OrbitCalculator(orbits)
    orbitCalculator.connectOrbitables()
    orbitCalculator.calculateNoOfOrbits()

    //Task A
    println(orbitCalculator.totalNumberOfOrbits)

    //Task B
    println(orbitCalculator.getDistanceBetweenYouAndSanta())
}