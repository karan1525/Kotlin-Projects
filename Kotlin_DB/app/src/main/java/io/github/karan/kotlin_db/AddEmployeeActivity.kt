package io.github.karan.kotlin_db

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText

class AddEmployeeActivity : AppCompatActivity() {

    private var nameEditText: EditText? = null
    private var addressEditText: EditText? = null

    private var myHelper: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)

        nameEditText = findViewById<View>(R.id.name_edittext) as EditText
        addressEditText = findViewById<View>(R.id.address_edittext) as EditText

        myHelper = DatabaseHelper(this)
        myHelper!!.open()
    }

    fun addButtonPressed(view: View) {
        val name = nameEditText!!.text.toString()
        val address = addressEditText!!.text.toString()

        myHelper!!.add(name, address)

        finish()
    }
}
