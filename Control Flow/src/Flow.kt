/**
 * Created by karan on 8/6/17
 */
fun main(args: Array<String>) {
    val x = "English"

    val y = when (x) {
        "English" -> print("How are you?")
        "German" -> print("Wie geht es Ihnen?")
        else -> print("I don't understand that language yet")
    }

    println()

    val names = listOf("John", "Sarah", "Karan", "Manisha")
    val m: String? = "Manisha"

    when (m) {
        in names -> print("I know that name!")
        is String -> print(m.length)
    }
}

//When construct

class nullClass()

fun cases(obj: Any) {
    when (obj) {
        1 -> println("One")
        "Hello", "hello" -> println("Greeting")
        is Long -> println("Long")
        !is String -> println("Not a string!")
        else -> println("IDEK")
    }
}

fun test(args: Array<String>) {
    cases("Hello")
    cases(1)
    cases(0L)
    cases(nullClass())
    cases("hello")



}