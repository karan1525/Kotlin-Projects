package io.github.karanbhargava.kotlin_android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //No need to do findViewById -> Specific R file already loaded at the top
        TextView.text = getString(R.string.my_name)
    }
}
