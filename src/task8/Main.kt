package task8

import util.FileUtil
import util.Task

//Task A: 1560
//Task B: UGCUH
fun main(){

    //Task A
    performTask(3,2,"inputTestA.txt", Task.A)
    performTask(25,6,"input.txt", Task.A)

    //Task B
    performTask(2,2,"inputTestB.txt", Task.B)
    performTask(25,6,"input.txt", Task.B)
}

fun performTask(width: Int, height: Int, filename: String, task: Task){
    val input = FileUtil.readLinesFromFile(8, filename)[0]
    val decoder = SIFDecoder(width, height, input)
    decoder.createLayers()

    when(task){
        Task.A -> println(decoder.getMultipliedDigitsValue())
        Task.B -> decoder.renderImage()
    }

    println()

}