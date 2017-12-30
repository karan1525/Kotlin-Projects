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
        operator.setText("*")
    }

    fun plusButtonPressed(view: View) {
        val operator = findViewById<View>(R.id.textViewOperator) as TextView
        operator.setTextColor(Color.WHITE)
        operator.setBackgroundColor(Color.MAGENTA);
        operator.setText("+")
    }

    fun minusButtonPressed(view: View) {
        val operator = findViewById<View>(R.id.textViewOperator) as TextView
        operator.setTextColor(Color.RED);
        operator.setBackgroundColor(Color.BLACK);
        operator.setText("-")
    }

    fun divideButtonPressed(view: View) {
        val operator = findViewById<View>(R.id.textViewOperator) as TextView
        operator.setTextColor(Color.YELLOW);
        operator.setBackgroundColor(Color.BLACK);
        operator.setText("/")
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

        val value = 0.0

        when(operatorString) {
            "/" -> result.setText((firstOperandDouble / secondOperandDouble).toString())
            "*" -> result.setText((firstOperandDouble * secondOperandDouble).toString())
            "-" -> result.setText((firstOperandDouble - secondOperandDouble).toString())
            "+" -> result.setText((firstOperandDouble + secondOperandDouble).toString())
            else -> return
        }
    }
}
