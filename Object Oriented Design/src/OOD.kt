import javax.naming.Context
import javax.swing.text.AttributeSet

/**
 * Created by karan on 8/6/17
 */

//Class with only a primary constructor
class User constructor(_nickname: String) {
    val nickName: String
    init {
        nickName = _nickname
    }
}

//Valid class declaration that has a constructor
class otherUser (val nickname: String, val isSubscribed: Boolean = true)

//Class with many secondary constructors

class View {
    constructor( ctx: Context) {
        //some code
    }

    constructor( ctx: Context, attr: AttributeSet) {
        //some code
    }
}

//Class that has a primary and a secondary constructor
class Person (val name: String) {
    constructor(name: String, parent: Person): this(name) {
        //parent.children.add(this)
    }
}

//change the visibility of a modifier
open class Container {
    protected open val fieldA: String = "Some value"
}

class DerivedContainer: Container() {
    public override val fieldA: String = "something else"
}

//Inner class
class Outer {
    private val bar: Int = 1
    inner class Inner {
        fun foo() = bar
    }
}