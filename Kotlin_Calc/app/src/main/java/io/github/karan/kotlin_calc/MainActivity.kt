package io.github.karan.kotlin_calc

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

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
}
