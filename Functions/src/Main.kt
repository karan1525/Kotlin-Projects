import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Created by karan on 8/6/17
 */

fun main(args: Array<String>) {
    foo(12)

    //Not using named arguments
    val string = "a kindness of ravens"
    string.regionMatches(14, "Red Ravens", 4, 6, true)

    //Using named arguments [Similar to python]
    string.regionMatches(thisOffset = 14, other = "Red Ravens", otherOffset = 4, length = 6, ignoreCase = true)

}

fun foo(k: Int) {
    //Illegal Argument Exception
    require(k > 10, {"k should be greater than 10"}) //terminates program is k is less than 10
    print(k)
}

//Local functions (method inside a method) [Basically a private function]
fun printArea(width: Int, height: Int):Unit {
    fun calculateArea(width:Int, height: Int) = width*height
    val area = calculateArea(width, height)
    println("This is $area")
}

//Default Param methods
fun divide(divisor: BigDecimal, scale: Int = 0, roundingMode: RoundingMode = RoundingMode.UNNECESSARY): BigDecimal {
    return null!!
}

//Infix method calls
//Traditional -> to use this method you'd have to create an Object of Account and do Account.balance()
class Account {
    var balance = 0.0
    fun add (amount: Double): Unit {
        this.balance = balance + amount
    }
}

//Infix style
//To call this method, we can do val account2 = InfixAccount() and then account2 add 100.00
class InfixAccount {
    var balance = 0.0

    infix fun add (amount: Double): Unit {
        this.balance = balance + amount
    }
}

//Variable Number of Arguments
fun multiprint(vararg strings: String):Unit {
    for (string in strings) {
        println(string)
        //can be invoked like this: multiprint("a","b","c")
    }
}