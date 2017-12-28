/**
 * Created by karan on 8/6/17
 */
import java.util.*

fun main(args: Array<String>) {

    val secretNumber: Int = (Math.random() * 999 + 1).toInt()

    val keyboard = Scanner(System.`in`) //tilde quotes
    var guess: Int
    do {
        println("Enter a guess (1-1000): ")
        guess = keyboard.nextInt()

        when {
            guess == secretNumber -> println("Wow, You guessed correctly!")
            guess < secretNumber -> println("Go higher")
            guess > secretNumber -> println("Go lower")
        }

    } while (guess != secretNumber)

}