package task10

import jdk.jfr.Enabled
import java.lang.RuntimeException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2

class Asteroid(val x: Int, val y: Int) {
    val key = getKey(x,y)

    companion object {
        fun getKey(x:Int, y: Int): String{
            return "$x,$y"
        }
    }

    //Each unique key == 1 visible asteroid
    private val angleToAsteroids = TreeMap<Angle, ArrayList<Asteroid>>()
    lateinit var renderer: AsteroidRenderer

    //For convenience, also returns size of the asteroidMap
    fun groupAsteroids(asteroids: ArrayList<Asteroid>): Int {
        asteroids.forEach { ast ->
            if (ast != this) {
                val angle = getAngle(ast)
                addToList(angle, ast)
            }
        }

        renderer = AsteroidRenderer(this, asteroids)
        return angleToAsteroids.size
    }

    fun addToList(angle: Angle, asteroid: Asteroid) {
        if (angleToAsteroids[angle] == null) {
            angleToAsteroids[angle] = ArrayList()
        }

        angleToAsteroids[angle]!!.add(asteroid)
    }

    fun getDistance(asteroid: Asteroid): Double {
        return abs(x - asteroid.x).toDouble() + abs(y - asteroid.y).toDouble()
    }

    fun getAngle(asteroid: Asteroid): Angle {
        val normalizedX = (x - asteroid.x).toDouble()
        val normalizedY = (y - asteroid.y).toDouble()

        val angle = atan2(normalizedY, normalizedX) - PI/2

        //Points seem distinct enough to not create errors through rounding.
        return Angle(angle)
    }

    fun spinToWin(debugEnabled: Boolean): Asteroid {
        var killCount = 0
        if(debugEnabled) renderer.init()

        angleToAsteroids.entries.forEach { (key, angleList) ->
                val closest = getClosestAndPrune(angleList)
                println("Asteroid at ${closest.x},${closest.y} destroyed.")

                renderer.destroyAsteroid(closest)

                if(++killCount == 200){
                    return closest
                }
                if (angleList.isEmpty()) angleToAsteroids.remove(key)
        }

        throw RuntimeException("Could not find.. stuff")
    }

    //Retrieves closest in list and removes the value from list
    private fun getClosestAndPrune(angleList: ArrayList<Asteroid>): Asteroid {
        if (angleList.size == 1) {
            return angleList[0]
        }

        var closest: Asteroid? = null
        var closestDistance = -1.0

        angleList.forEach { asteroid ->
            val distance = getDistance(asteroid)
            if(closestDistance < 0 || distance < closestDistance){
                closestDistance = distance
                closest = asteroid
            }
        }

        angleList.remove(closest)
        return closest!!
    }

    fun getNumberOfVisibleAsteroids(): Int = angleToAsteroids.size


}
