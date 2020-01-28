package task10

class AsteroidFinder(val gridStrings: List<String>) {
    val maxX = gridStrings[0].length
    val maxY = gridStrings.size

    val asteroids = ArrayList<Asteroid>()

    //Best location for station
    lateinit var bestAsteroid: Asteroid

    fun createAsteroidsAndGroup() {
        for (x in 0 until maxX) {
            for (y in 0 until maxY) {
                if (gridStrings[y][x] == '#') {
                    asteroids.add(Asteroid(x, y))
                }
            }
        }

        asteroids.forEach { asteroid ->
            asteroid.groupAsteroids(asteroids) }
    }

    fun getBestStationAsteroid() : Asteroid{
        var highest = 0

        asteroids.forEach { asteroid ->
            val count = asteroid.getNumberOfVisibleAsteroids()
            if (count > highest) {
                highest = count
                bestAsteroid = asteroid
            }
        }

        return bestAsteroid
    }

    fun getHighestAsteroidCount(): Int {
        val bestAsteroid = getBestStationAsteroid()
        println("Best asteroid has coordinates: ${bestAsteroid.x}, ${bestAsteroid.y}")

        return bestAsteroid.getNumberOfVisibleAsteroids()
    }

    fun get200thVictim(debugEnabled: Boolean): Int{

        val innocentVictim200 = bestAsteroid.spinToWin(debugEnabled)

        return innocentVictim200.x * 100 + innocentVictim200.y
    }


}