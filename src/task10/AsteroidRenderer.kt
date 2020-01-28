package task10

class AsteroidRenderer(private val station: Asteroid, private val asteroids: ArrayList<Asteroid>) {

    private lateinit var asteroidAndStatus: HashMap<String, Status>
    private var gridX = 0
    private var gridY = 0
    private var initiliazed = false

    fun init() {
        asteroidAndStatus = HashMap()

        asteroids.forEach { asteroid ->
            asteroidAndStatus.put(asteroid.key,
                    if (asteroid == station) Status.STATION else Status.ALIVE)

            updateGridSize(asteroid)
        }

        initiliazed = true
        printState()
    }

    fun destroyAsteroid(asteroid: Asteroid){
        if(initiliazed) {
            asteroidAndStatus[asteroid.key] = Status.DESTROYED
            printState()
        }
    }

    private fun updateGridSize(asteroid: Asteroid) {
        if (asteroid.x > gridX) gridX = asteroid.x
        if (asteroid.y > gridY) gridY = asteroid.y
    }

    fun printState() {
        if(initiliazed) {
            println()
            for (y in 0..gridY) {
                for (x in 0..gridX) {

                    var symbol : String?  = "."
                     asteroidAndStatus[Asteroid.getKey(x,y)]?.let { symbol = it.symbol }
                    print(symbol)
                }
                print("\n")
            }
            println()
        }
    }

    enum class Status(val symbol: String) {
        STATION("O"),
        ALIVE("#"),
        DESTROYED("X");

    }

}