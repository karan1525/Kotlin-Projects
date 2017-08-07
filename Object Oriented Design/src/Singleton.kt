import java.io.File

/**
 * Created by karan on 8/6/17
 */

object Singleton {
    private var count = 0

    fun doSomething(): Unit {
        println("Calling a doSomething (${++count} call/-s in total")
    }
}

object CaseInsenstiveFileComparator: Comparator<File> {
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path, ignoreCase = true)
    }
}

fun main(args: Array<String>) {
    println(CaseInsenstiveFileComparator.compare (
        File("/User"), File("/user")))

        val files = listOf(File("/Z"), File("/a"))
        println(files.sortedWith(CaseInsenstiveFileComparator))
}
