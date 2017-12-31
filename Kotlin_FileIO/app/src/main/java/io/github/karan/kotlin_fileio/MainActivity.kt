package io.github.karan.kotlin_fileio

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private var readView: EditText? = null
    private var writeView: EditText? = null

    private var outputStream: FileOutputStream? = null
    private val filename = "temp.txt"

    private var myExternalFile: File? = null

    /* Helper Methods */

    private// Get the directory for the user's public pictures directory.
    val documentDir: File?
        get() {
            var file: File? = null
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                file = File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOCUMENTS), "temp.txt")
            }

            return file
        }

    /* Checks if external storage is available for read and write */
    private val isExternalStorageWritable: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED == state
        }

    /* Checks if external storage is available to at least read */
    private val isExternalStorageReadable: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readView = findViewById(R.id.editTextRead)
        writeView = findViewById(R.id.editTextWrite)

        val externalWrite = findViewById<Button>(R.id.WriteExternalButton)
        val externalRead = findViewById<Button>(R.id.ReadExternalButton)
        val externalDelete = findViewById<Button>(R.id.DeleteExternalButton)

        // if not able to write to external storage, disable buttons
        if (!isExternalStorageWritable && isExternalStorageReadable) {
            externalWrite.isEnabled = false
            externalRead.isEnabled = false
            externalDelete.isEnabled = false
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    12)
        }

        myExternalFile = documentDir
    }

    fun writeInternalOnClick(view: View) {
        val data = writeView!!.text.toString()

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE)
            outputStream!!.write(data.toByteArray())
            outputStream!!.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun readInternalOnClick(view: View) {

        try {
            val instream = this.openFileInput(filename)
            readData(instream)

        } catch (e: java.io.FileNotFoundException) {
            // do something if the filename does not exits
        }

    }

    fun deleteInternalOnClick(view: View) {
        this.deleteFile("temp.txt")
    }

    fun writeExternalOnClick(view: View) {
        val data = writeView!!.text.toString()

        try {
            outputStream = FileOutputStream(myExternalFile!!)
            outputStream!!.write(data.toByteArray())
            outputStream!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun readExternalOnClick(view: View) {
        try {
            val instream = FileInputStream(myExternalFile!!)
            readData(instream)

        } catch (e: java.io.FileNotFoundException) {

            // do something if the filename does not exits
        }

    }

    fun deleteExternalOnClick(view: View) {
        myExternalFile!!.delete()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            12 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(applicationContext, "Permission granted! :).",
                            Toast.LENGTH_SHORT).show()
                    // do something
                } else {
                    Toast.makeText(applicationContext, "Permission NOT granted :(",
                            Toast.LENGTH_SHORT).show()
                    // not granted
                }
            }
        }
    }

    private fun readData(instream: InputStream) {
        try {
            val inputreader = InputStreamReader(instream)

            val buffreader = BufferedReader(inputreader)

            val line: String
            val allLines = StringBuilder()

            // read every line of the file into the line-variable, on line at the time

            line = buffreader.readLine()
            while (line != null) {
                allLines.append(line)
            }

            readView!!.setText(allLines.toString())

            // close the file again
            instream.close()

        } catch (e: Exception) {

            // do something if the filename does not exits
        }

    }
}
