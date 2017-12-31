package io.github.karan.kotlin_db

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText

class ModifyEmployeeActivity : AppCompatActivity() {

    private var nameText: EditText? = null
    private var addressText: EditText? = null

    private var _id: Long = 0

    private var myHelper: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_employee)

        myHelper = DatabaseHelper(this)
        myHelper!!.open()

        nameText = findViewById(R.id.name_edittext)
        addressText = findViewById(R.id.address_edittext)

        val intent = intent
        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val desc = intent.getStringExtra("address")

        _id = java.lang.Long.parseLong(id)

        nameText!!.setText(name)
        addressText!!.setText(desc)
    }

    private fun returnToMainActivity() {
        finish()
    }

    fun updateButtonPressed(view: View) {
        val name = nameText!!.text.toString()
        val address = addressText!!.text.toString()

        myHelper!!.update(_id, name, address)
        returnToMainActivity()
    }

    fun deleteButtonPressed(view: View) {
        myHelper!!.delete(_id)
        returnToMainActivity()

    }
}

