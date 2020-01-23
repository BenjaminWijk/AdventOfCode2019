package task6

import util.FileUtil

//Task A: 110190
//Task B: 343
fun main(){
    val orbits = FileUtil.readLinesFromFile(6, "input.txt")

    val oc = OrbitCalculator(orbits)
    oc.connectOrbitables()
    oc.calculateNoOfOrbits()

    //Task A
    println(oc.totalNumberOfOrbits)

    //Task B
    println(oc.getDistanceToSanta())
}