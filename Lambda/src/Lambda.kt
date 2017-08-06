/**
 * Created by karan on 8/6/17
 */

fun main (args: Array<String>) {
    run ({ println ("I am a function literal")})

    val printHello = ({println("Hello")})
   // println(printHello) //prints () because printHello already prints
    printHello()

    val printMessage = { message: String -> println(message)}
    printMessage("Hello World!")

    val sumAndPrint = { x: Int, y: Int ->
        println("Computing the sum of $x and $y")
        x + y
    }

    val sumConcise = { x:Int, y:Int -> x + y}

    val sumEvenMoreConcise: (Int, Int) -> Int = { x, y -> x + y}

    println(sumAndPrint(5, 2))
    println(sumConcise(1, 2))
    println(sumEvenMoreConcise(2, 2))
}