import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.findAnnotation

fun main(args: Array<String>) {

    val myObject = AnnotationExample::class

    for (function in myObject.declaredFunctions) {
        if (function.findAnnotation<FunctionInfo>() != null) {
            val functionInformation = function.findAnnotation<FunctionInfo>() as FunctionInfo
            println(function)
            println("Author: ${functionInformation.author}")
            println("Comments: ${functionInformation.comments}")
            println("Date: ${functionInformation.date}")
            println("Revision: ${functionInformation.revision}")
            println("------------------------------")
        }
    }

}