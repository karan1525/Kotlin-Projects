import java.io.FileNotFoundException

class AnnotationExample {

    @FunctionInfo(author = "Karan", comments = "toString method", date = "Dec 29 2017", revision = 1)
    override fun toString():String {
        return "Overridden toString method"
    }

    fun main(args: Array<String>) {

    }

    @FunctionInfo(comments = "deprecated method", date = "Dec 29 2017")
    @Deprecated("", ReplaceWith("println(\"old method, don't use it.\")"))
    private fun oldMethod() {
        println("Old method, don't use it")
    }

    @FunctionInfo(author = "Karan", comments = "generic method", date = "Dec 29 2017", revision = 10)
    @Suppress("deprecation")
    @Throws(FileNotFoundException::class)
    fun genericsTest() {
        oldMethod()
    }

}