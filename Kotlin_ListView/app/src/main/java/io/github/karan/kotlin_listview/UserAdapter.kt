package io.github.karan.kotlin_listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

import java.util.ArrayList

class UserAdapter(context: Context, users: ArrayList<User>) : ArrayAdapter<User>(context, 0, users) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val user = getItem(position)

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.simple_list_item_1, parent, false)
        }

        val image = convertView!!.findViewById<View>(R.id.logo) as ImageView
        val text1 = convertView.findViewById<View>(R.id.label) as TextView
        val text2 = convertView.findViewById<View>(R.id.label2) as TextView

        text1.text = user!!.firstName
        text2.text = user.lastName
        image.setImageResource(android.R.drawable.btn_star)

        return convertView
    }
}


