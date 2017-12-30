package io.github.karan.kotlin_calc

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun multiplyButtonPressed(view: View) {
        val operator = findViewById<View>(R.id.textViewOperator) as TextView
        operator.setTextColor(Color.WHITE)
        operator.setBackgroundColor(Color.BLUE)
        operator.text = "*"
    }

    fun plusButtonPressed(view: View) {
        val operator = findViewById<View>(R.id.textViewOperator) as TextView
        operator.setTextColor(Color.WHITE)
        operator.setBackgroundColor(Color.MAGENTA)
        operator.text = "+"
    }

    fun minusButtonPressed(view: View) {
        val operator = findViewById<View>(R.id.textViewOperator) as TextView
        operator.setTextColor(Color.RED)
        operator.setBackgroundColor(Color.BLACK)
        operator.text = "-"
    }

    fun divideButtonPressed(view: View) {
        val operator = findViewById<View>(R.id.textViewOperator) as TextView
        operator.setTextColor(Color.YELLOW)
        operator.setBackgroundColor(Color.BLACK)
        operator.text = "/"
    }

    fun calculateButtonPressed(view: View) {
        val firstOperand = findViewById<View>(R.id.editTextFirstOperand) as EditText
        val secondOperand = findViewById<View>(R.id.editTextSecondOperand) as EditText
        val operator = findViewById<View>(R.id.textViewOperator) as TextView
        val result = findViewById<View>(R.id.textViewResult) as TextView

        val firstOperandString = firstOperand.text.toString()
        val secondOperandString = secondOperand.text.toString()
        val operatorString = operator.text.toString()

        val firstOperandDouble = java.lang.Double.parseDouble(firstOperandString)
        val secondOperandDouble = java.lang.Double.parseDouble(secondOperandString)

        when(operatorString) {
            "/" -> result.text = (firstOperandDouble / secondOperandDouble).toString()
            "*" -> result.text = (firstOperandDouble * secondOperandDouble).toString()
            "-" -> result.text = (firstOperandDouble - secondOperandDouble).toString()
            "+" -> result.text = (firstOperandDouble + secondOperandDouble).toString()
            else -> return
        }
    }
}
