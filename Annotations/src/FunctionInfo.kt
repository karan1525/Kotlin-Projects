@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)

annotation class FunctionInfo(val author: String = "Karan", val date: String, val revision:Int = 1, val comments: String)