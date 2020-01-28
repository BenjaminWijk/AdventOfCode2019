package task10

import util.FileUtil

fun main(){
    val input = FileUtil.readLinesFromFile(10, "inputTestB.txt")
    val asteroidFinder = AsteroidFinder(input)
    asteroidFinder.createAsteroidsAndGroup()

    asteroidFinder.getHighestAsteroidCount()
    println(asteroidFinder.get200thVictim(true))




}