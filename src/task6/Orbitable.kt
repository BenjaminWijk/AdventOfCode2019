package task6

class Orbitable {
    //Will be set by all orbitables, except center of mass
    var parent: Orbitable? = null

    //-1 represents "not yet calculated"
    var noOfOrbits: Int = -1

    fun calcNumberOfOrbits(): Int{
        if(noOfOrbits >= 0){
            return noOfOrbits
        }

        //Is this object the COM?
        if (parent == null){
            noOfOrbits = 0
            return noOfOrbits
        }

        noOfOrbits = parent!!.calcNumberOfOrbits() + 1
        return noOfOrbits
    }


}