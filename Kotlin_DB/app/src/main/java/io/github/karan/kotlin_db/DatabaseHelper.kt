package io.github.karan.kotlin_db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    private var database: SQLiteDatabase? = null

    val allEmployees: Cursor
        get() {
            val projection = arrayOf(ID, NAME, ADDRESS)

            return database!!.query(TABLE_NAME, projection, null, null, null, null, null)
        }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    @Throws(SQLException::class)
    fun open() {
        database = this.writableDatabase
    }

    override fun close() {
        database!!.close()
    }

    fun add(name: String, address: String) {
        val contentValues = ContentValues()
        contentValues.put(NAME, name)
        contentValues.put(ADDRESS, address)

        database!!.insert(TABLE_NAME, null, contentValues)
    }

    fun update(_id: Long, name: String, address: String): Int {
        val contentValues = ContentValues()

        contentValues.put(NAME, name)
        contentValues.put(ADDRESS, address)

        return database!!.update(TABLE_NAME, contentValues, ID + " = " + _id, null)
    }

    fun delete(_id: Long) {
        database!!.delete(TABLE_NAME, ID + "=" + _id, null)
    }

    fun getNames(namePassedIn: String): Cursor? {
        // Define a projection that specifies which columns from the database you will actually use after this query.
        val projection = arrayOf(ID, NAME, ADDRESS)

        // Filter results WHERE "name" = passed in parameter
        val selection = NAME + " = ?"
        val selectionArgs = arrayOf(namePassedIn)

        // How you want the results sorted in the resulting Cursor
        val sortOrder = NAME + " DESC"

        val cursor = database!!.query(
                TABLE_NAME, // The table to query
                projection, // The columns to return
                selection, // The columns for the WHERE clause
                selectionArgs, null, null, // don't filter by row groups
                sortOrder                      // The sort order
        )// The values for the WHERE clause
        // don't group the rows

        cursor?.moveToFirst()

        return cursor
    }

    companion object {

        // Table Name
        val TABLE_NAME = "EMPLOYEES"

        // Table columns
        val ID = "_id"
        val NAME = "name"
        val ADDRESS = "address"

        // Database Information
        internal val DB_NAME = "myEmployees.DB"

        // database version
        internal val DB_VERSION = 1

        // Creating table query
        private val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "(" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + ADDRESS + " CHAR(50));")
    }
}

