package com.example.networkstate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val networkState = NetworkConnectionState(this@MainActivity)
        networkState.isNetworkConnected()

        /*if(networkState.isNetworkConnected()==true){

        }else{

        }

         */
    }
}
