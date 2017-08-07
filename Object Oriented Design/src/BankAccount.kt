/**
 * Created by karan on 8/6/17
 */

open class BankAccount(var balance: Double) {
    constructor(): this(0.0)

    open fun deposit(amount: Double) {
        balance += amount
    }

    open fun withdraw (amount: Double) {
        balance -+ amount
    }

    fun transfer(amount: Double, other:BankAccount) {
        withdraw(amount)
        other.deposit(amount)
    }
}

class SavingsAccount(var interestRate: Double):BankAccount() {
    fun addInterest() {
        val interest = balance * interestRate / 100

    }
}

class CheckingAccount(var initialBalance: Double):BankAccount(initialBalance) {
    var transactionCount = 0

    //Static member variables
    companion object {
        private val FREE_TRANSACTIONS = 3
        private val TRANSACTION_FEE = 2.0
    }
    override fun deposit (amount: Double) {
        transactionCount++;
        super.deposit(amount)

    }

    override fun withdraw(amount: Double) {
        transactionCount++
        super.withdraw(amount)
    }

    fun deductFees() {
        if (transactionCount > FREE_TRANSACTIONS) {
            val fees = TRANSACTION_FEE * (transactionCount - FREE_TRANSACTIONS)
        }
    }
}

fun main (args: Array<String>) {

    val momsSavings = SavingsAccount(0.5)
    val dadsChecking = CheckingAccount(100.0)

    momsSavings.deposit(10000.0)

    momsSavings.transfer(2000.0, dadsChecking)
    dadsChecking.withdraw(1500.0)
    dadsChecking.withdraw(80.0)

    momsSavings.transfer(1000.0, dadsChecking)
    dadsChecking.withdraw(400.0)

    println("Mom's saving balance = $" + momsSavings.balance)
    println("Dad's checking balance = $" + dadsChecking.balance)

    //Simulate EOM (end of month)
    println("-------------- END OF MONTH BOOKEEPING")
    momsSavings.addInterest()
    dadsChecking.deductFees()

    println("Mom's saving balance = $" + momsSavings.balance)
    println("Dad's checking balance = $" + dadsChecking.balance)

}