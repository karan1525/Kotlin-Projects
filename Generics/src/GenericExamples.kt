class SimpleGeneric<T: Any>(param: T) {
    var reference: T? = null

    init {
        reference = param
    }

    fun printType() {
        println("Type: " + reference?.javaClass?.name) //not null
    }
}

val <T> List<T>.penultimate: T
    get() = this[size - 2]

inline fun <reified T> isA(value: Any) = value is T

fun <T: Comparable<T>> max(first:T, second: T): T {
    return if (first > second) first else second
}

fun main(args: Array<String>) {

    println("\nTest Methods")
    testMethods()

    println("\nSimple Generic Class")

    val temp = SimpleGeneric("KaransClass")
    temp.printType()

    val temp2 = SimpleGeneric(java.lang.Boolean.TRUE)
    temp2.printType()

}

fun testMethods() {
    println(listOf(1, 2, 3).penultimate)

    println(isA<String>("abc"))
    println(isA<String>(123))

    val items = listOf("one", 2, "three")
    println(items.filterIsInstance<String>())

    println(max("kotlin", "java"))
}