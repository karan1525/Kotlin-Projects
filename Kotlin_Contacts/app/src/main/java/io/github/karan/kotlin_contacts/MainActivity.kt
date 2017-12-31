package io.github.karan.kotlin_contacts

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.support.v4.app.LoaderManager
import android.support.v4.content.ContextCompat
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v4.widget.SimpleCursorAdapter
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    private var adapter: SimpleCursorAdapter? = null

    internal val from = arrayOf(ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME)

    private val to = intArrayOf(R.id.name, R.id.address)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.list_view)
        listView.emptyView = findViewById<View>(R.id.empty)

        adapter = SimpleCursorAdapter(this, R.layout.activity_view_record, null,
                from, to, 0)

        listView.adapter = adapter

        requestPermissions()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == R.id.add_record) {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateLoader(i: Int, bundle: Bundle): Loader<Cursor> {
        /* Display all of the Contacts */
        val selection = ContactsContract.Data.MIMETYPE + " = ?"
        val selectionArgs = arrayOf(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)

        return CursorLoader(this, ContactsContract.Data.CONTENT_URI,
                arrayOf(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME),
                selection, selectionArgs, ContactsContract.Contacts.DISPLAY_NAME + " ASC")

    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
        adapter!!.swapCursor(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        adapter!!.swapCursor(null)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        if (requestCode == PERMISSIONS_REQUEST_WRITE_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                supportLoaderManager.initLoader(LOADER_ID, null, this)
            } else {
                Toast.makeText(this, "You must grant permission to display contacts",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun requestPermissions() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_CONTACTS),
                    PERMISSIONS_REQUEST_WRITE_CONTACTS)
        } else {
            supportLoaderManager.initLoader(LOADER_ID, null, this)
        }
    }

    companion object {

        private val LOADER_ID = 1976

        // Request code for READ_CONTACTS.
        private val PERMISSIONS_REQUEST_WRITE_CONTACTS = 100
    }

}