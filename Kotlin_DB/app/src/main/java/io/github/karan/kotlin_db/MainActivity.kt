package io.github.karan.kotlin_db

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v4.widget.SimpleCursorAdapter
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    private var myHelper: DatabaseHelper? = null

    private var adapter: SimpleCursorAdapter? = null

    internal val from = arrayOf(DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.ADDRESS)

    private val to = intArrayOf(R.id.id, R.id.name, R.id.address)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_emp_list)

        myHelper = DatabaseHelper(this)
        myHelper!!.open()

        val listView = findViewById<ListView>(R.id.list_view)
        listView.emptyView = findViewById(R.id.empty)

        adapter = SimpleCursorAdapter(this, R.layout.activity_view_record, null, from, to, 0)

        listView.adapter = adapter

        supportLoaderManager.initLoader(LOADER_ID, null, this)

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, view, _, _ ->
            val idTextView = view.findViewById<TextView>(R.id.id)
            val nameTextView = view.findViewById<TextView>(R.id.name)
            val addressTextView = view.findViewById<TextView>(R.id.address)

            val id = idTextView.text.toString()
            val title = nameTextView.text.toString()
            val desc = addressTextView.text.toString()

            val modifyIntent = Intent(applicationContext, ModifyEmployeeActivity::class.java)
            modifyIntent.putExtra("name", title)
            modifyIntent.putExtra("address", desc)
            modifyIntent.putExtra("id", id)

            startActivity(modifyIntent)
        }
    }

    public override fun onResume() {
        super.onResume()
        supportLoaderManager.restartLoader(LOADER_ID, null, this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == R.id.add_record) {
            val intent = Intent(this, AddEmployeeActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateLoader(i: Int, bundle: Bundle): Loader<Cursor> {
        return MyLoader(this, myHelper)
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

    companion object {

        private val LOADER_ID = 1976
    }
}

