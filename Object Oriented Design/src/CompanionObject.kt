/**
 * Created by karan on 8/6/17
 */

fun getFacebookName(accountId: Int) = "fb: $accountId"

class Us private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) = Us(email.substringBefore('@'))

        fun newFacebookUser(accountId: Int) = User(getFacebookName(accountId))
    }
}

fun main(args: Array<String>) {
    val subscribingUser = Us.newSubscribingUser("bob@gmail.com")
    val facebookUser = Us.newFacebookUser(4)
    println(subscribingUser.nickname)
    println(facebookUser.nickName)
}