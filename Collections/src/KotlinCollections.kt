fun main(args: Array<String>) {
    println("Lists")
    lists()

    println("\nSets")
    sets()

    println("\nMaps")
    maps()

    println("\nOther methods")
    otherMethods()

    println("\nMutable List Example")
    mutableListExample()

}

fun lists() {
    val strings = listOf("first", "second", "third")
    println(strings.last())
    val numbers = setOf(1,14,2)
    println(numbers.max())
}

fun sets() {

    val intSet: Set<Int> = setOf(1, 21, 21, 2, 6, 3, 2)
    print(intSet)

    val sortedIntegers: java.util.TreeSet<Int> = sortedSetOf(11, 0, 9, 11, 9, 8)
    println(sortedIntegers)

    val longSet: MutableSet<Long> = mutableSetOf(212312312, 1231234534521, 214312532412)
    println(longSet)
    println(longSet.first())
    println(longSet.plus(10))
    println(longSet.minus(-1))
    println(longSet)

}

fun maps() {
    val map = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
    println(map[53])
    println(map)

    val carsMap: Map<String, String> = mapOf("a" to "Aston Martin", "f" to "Ferrari")
    println("Car maker starting with A: ${carsMap["a"]}")

    val states: MutableMap<String, String> = mutableMapOf("AL" to "Alabama", "CA" to "California")
    println("States before: ${states.keys}")
    states += ("OR" to "Oregon")
    println("States after: ${states.values}")

    println("Traversing a map")

    val myMap = hashMapOf("one" to 1, "two" to 2)
    for((key, value) in myMap) {
        println("Key = $key, value = $value")
    }
}

fun otherMethods() {
    val list = listOf("a", "ab", "b")
    print("Group by: ")
    println(list.groupBy(String::first))
}

fun mutableListExample() {
    val myList: MutableList<String> = mutableListOf()

    myList.add("John")
    myList.add("Bob")
    myList.add("Nancy")
    myList.add("Lucy")
    println("My arrayList has " + myList)

    myList.add(0, "Joe")
    myList.add(1, "Tom")
    println("My arrayList now has " + myList)

    myList.remove("John")
    myList.remove("Nancy")
    println("My arrayList after removing elements now is " + myList)

    myList.removeAt(1)
    println("My arrayList after removing element at 1 now is " + myList)

    println(myList.filter{ it.startsWith("J")})
    println(myList.filter { it.startsWith("J")}.map{it.toUpperCase()})
}