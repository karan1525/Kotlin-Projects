/**
 * Created by karan on 8/6/17
 */

fun main(args: Array<String>) {
    if (args.size < 2) {
        println("No numbers supplied")
    } else {
        val x = parseInt(args[0])
        val y = parseInt(args[1])

        x ?: return
        y ?: return

        println(x * y) //only printing x * y if neither is null

        val jason:String? = "jason"
        println(jason!!.toUpperCase()) //forcefully telling the compiler that the value is not null
    }
}

//Function to convert the passed in args as integers
fun parseInt(str: String): Int? {
    try {
        return str.toInt()
    } catch (e: NumberFormatException) {
        println("one of the arguments cannot be converted to an int")
        e.printStackTrace()
    }
    return null
}