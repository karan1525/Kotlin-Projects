package io.github.karan.kotlin_db

import android.content.Context
import android.database.Cursor
import android.support.v4.content.CursorLoader

class MyLoader(context: Context, private val myDatabaseHelper: DatabaseHelper) : CursorLoader(context) {

    override fun loadInBackground(): Cursor? {
        return myDatabaseHelper.allEmployees
    }
}
