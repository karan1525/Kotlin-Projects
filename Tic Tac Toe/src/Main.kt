import java.util.*

/**
 * Created by karan on 8/6/17
 */

class TicTacToe {
    private val board: Array<CharArray> = Array(3, {CharArray(3) })
    var currentPlayerMark: Char = ' '

    init {
        currentPlayerMark = 'x'
        initializeBoard()
    }

    private fun initializeBoard()  {
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = '-'
            }
        }
    }

    fun printBoard() {
        println("-------------")

        for (i in 0..2) {
            print("| ")
            for (j in 0..2) {
                print(board[i][j] + " | ")
            }
            println()
            println("-------------")
        }
    }

    fun isBoardFull():Boolean {
        var isFull = true
        for (i in 0..2) {
            (0..2)
                    .asSequence()
                    .filter { board[i][it] == '-' }
                    .forEach { isFull = false }
        }

        return isFull
    }

    private fun checkRowCol(c1:Char, c2: Char, c3:Char):Boolean {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3))
    }

    private fun checkRowsForWin():Boolean {

        return (0..2).any { checkRowCol (board[it][0], board[it][1], board[it][2]) }
    }

    private fun checkColumnsForWin():Boolean {

        return (0..2).any { checkRowCol (board[0][it], board[1][it], board[2][it]) }
    }

    private fun checkDiagonalsForWin():Boolean {
        return (checkRowCol(board[0][0], board[1][1], board[2][2]) || checkRowCol(board[0][2], board[1][1], board[2][0]))
    }

    fun checkForWin():Boolean{
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin())
    }

    fun changePlayer() {
        currentPlayerMark = if (currentPlayerMark == 'x') {
            'o'
        } else {
            'x'
        }
    }

    fun placeMark(row: Int, col: Int): Boolean {
        if ((row >= 0) && (row < 3)) {
            if ((col >= 0) && (col < 3)) {
                if (board[row][col] == '-') {
                    board[row][col] = currentPlayerMark
                    return true
                }
            }
        }
        return false
    }
}


fun main(args: Array<String>) {
    val game = TicTacToe()

    val keyboard = Scanner(System.`in`)
    var row: Int
    var col: Int

    println("Ready to play Tic Tac Toe, lets go!!")

    game.printBoard()

    println()

    while (true) {

        println("Player ${game.currentPlayerMark}, please enter the board coordinates for your next spot:")

        row = keyboard.nextInt()
        col = keyboard.nextInt()

        game.placeMark(row, col)

        game.printBoard()
        println()

        if (game.checkForWin()) {
            println("We have a winner! Congrats")
            System.exit(0)
        } else if (game.isBoardFull()) {
            println("Appears we have a draw")
            System.exit(0)
        }

        game.changePlayer()
    }

}