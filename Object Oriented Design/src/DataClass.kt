/**
 * Created by karan on 8/6/17
 */

data class U(var name: String, var id: Int)

fun main (args: Array<String>) {
    val jason = U("jason", 1)
    jason.name = "jason fedin"
    jason.id = 1
    println("name = ${jason.name}, id = ${jason.id}")

    val (name, id) = jason
    println("name = $name, id = $id")

    val john = U("John", 2)
    val jennifer = U("Jennifer", 3)

//    println(jason.copy())
//    println(jason.copy("Jason Fedin"))

    println("jason == jennifer : ${jason === john}")

}