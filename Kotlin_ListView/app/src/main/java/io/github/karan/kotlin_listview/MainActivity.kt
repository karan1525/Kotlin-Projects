package io.github.karan.kotlin_listview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayOfUsers: ArrayList<User> = ArrayList<User>()
        val adapter = UserAdapter(this, arrayOfUsers)

        val listView = findViewById<View>(R.id.myListView) as ListView
        listView.setAdapter(adapter);

        val newUser = User("karan", "bhargava")
        val newUser1 = User("karan2", "bhargava2")
        val newUser2 = User("karan3", "bhargava3")

        adapter.add(newUser)
        adapter.add(newUser1)
        adapter.add(newUser2)

    }
}
