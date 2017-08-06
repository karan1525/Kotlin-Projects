/**
 * Created by karan on 8/6/17
 */

data class Point(val x: Int, val y: Int) {
    operator fun plus (other: Point):Point {
        return Point(x + other.x, y + other.y)
    }

    operator fun unaryMinus (): Point {
        return Point (-x, -y)
    }

    override fun equals(other: Any?) : Boolean {
        if (other === this) return true
        if (other !is Point) return false
        return other.x == x && other.y == y
    }
}

//Overloading the += operator
fun main(args: Array<String>) {
    var point = Point (1,2)
    point += Point(3,4)
    println(point)

    println(-point) //makes the output negative

    //Overloading the == operator
    println(Point(10, 20) != Point(5, 5))

    //CompareValuesBy
    val p1 = Person ("Alice", "Smith")
    val p2 = Person ("Alice", "Smith")

    //Overloading the comparison operators
    println(p2.compareTo(p1))
    println(p1 > p2)

}

class Person (val firstName: String, val lastName: String):Comparable<Person> {
    override fun compareTo(other: Person): Int {
        return compareValuesBy(this, other,  Person::lastName, Person::firstName)
    }
}