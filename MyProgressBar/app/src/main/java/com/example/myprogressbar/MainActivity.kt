package com.example.myprogressbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOk.setOnClickListener{
            llProgressBar.visibility = View.VISIBLE

            Handler().postDelayed({
                llProgressBar.visibility = View.GONE
            },5000)
        }
    }
}
