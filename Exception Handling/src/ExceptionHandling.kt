import java.io.BufferedReader
import java.io.StringReader
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    try {
        val reader = BufferedReader(StringReader("not a number"))

        val number = Integer.parseInt(reader.readLine())

    } catch (e: NumberFormatException) {
        println("Exception Occurred")

    } finally {
        println("Executed every time!")
    }

    val reader = BufferedReader(StringReader("not a number"))
    println()
    readNumber(reader)
    println()
    readFile()
}

fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
            null
    }

    println(number)
}

fun readFile() {
    val stream = Files.newInputStream(Paths.get("/some/file.txt"))
    stream.buffered().reader().use{
        reader -> println(reader.readText())
    }
}