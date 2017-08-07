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

    val sum = { x: Int, y: Int -> x + y}
    val action: () -> Unit = { println(42) }

    val people = listOf(Person("Alice", 29), Person("Bob", 31))

    // When the lambda is the only argument to a function, you can also remove the empty parenthesis from the call
    println(people.maxBy ({ p: Person -> p.age }))

    //You can have the argument type inferred this way
    println(people.maxBy { p -> p.age })

    //Finally use it, if the type can be inferred
    println(people.maxBy { it.age })

    //To do non-local returns
    fun lookForAlice(listOfPeople :List<Person>) {
        listOfPeople.forEach label@ {
            if (it.name == "Alice") return@label
        }

        println("Alice might be somewhere")
    }
}

data class Person( val name: String, val age: Int)