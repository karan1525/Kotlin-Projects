import java.util.*

/**
 * Created by karan on 8/5/17
 */

fun main(strings: Array<String>) {
    //Get the perimeter and area of a rectangle
    val width:Double = 5.6
    val height:Double = 8.5
    val perimeter  = 2.0 * (height + width)

    val area = width * height;

    System.out.printf("Perimeter is 2 * %.1f + %.1f = %.2f \n", height, width, perimeter)
    System.out.printf("Area is 2 * %.1f + %.1f = %.2f \n", height, width, area)

    //Converting minutes to years
    val minutesInYear: Double = (60 * 24 * 365).toDouble()
    var input = Scanner(System.`in`)
    print("Input the number of minutes: ")

    val min = input.nextDouble().toInt()

    val years = (min / minutesInYear).toLong()
    val days = (min / 60 / 24.0).toInt() % 365

    println("$min minutes is approximately $years years and  $days days ")

    //Starts with code

    val str1:String = "Red is my favorite color"
    var str2:String = "Orange is also my favorite color"

    val startStr:String = "Red"

    val starts1:Boolean = str1.startsWith(startStr);
    val starts2:Boolean = str2.startsWith(startStr);

    println("$str1 starts with $startStr ? $starts1") //returns true or false depending
    println("$str2 starts with $startStr ? $starts2") //prints boolean

    //Smart Casts examples
    val string1 = "jason"
    val intExample = 1

    println(getStringLength(string1))
    println(getStringLength(intExample))

}

fun getStringLength(obj: Any):Int? {
    if (obj is String) {
        return obj.length
    }

    return null
}