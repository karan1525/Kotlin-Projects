/**
 * Created by karan on 8/6/17
 */
import java.util.*

fun main(args: Array<String>) {

    val secretNumber: Int
    secretNumber = (Math.random() * 999 + 1).toInt()

    val keyboard = Scanner(System.`in`) //tilde quotes
    var guess: Int
    do {
        println("Enter a guess (1-1000): ")
        guess = keyboard.nextInt()

        if (guess == secretNumber) {
            println("Wow, You guessed correctly!")
        } else if (guess < secretNumber) {
            println("Go higher")

        } else if (guess > secretNumber) {
            println("Go lower")
        }

    } while (guess != secretNumber)

}