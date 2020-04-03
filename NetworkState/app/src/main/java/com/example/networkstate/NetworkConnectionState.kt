package com.example.networkstate

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log

class NetworkConnectionState(val context :Context) {

    fun isNetworkConnected():Boolean{

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.M){
            val network = connectivityManager.activeNetwork

            if(network != null){
                connectivityManager.getNetworkCapabilities(network).run {  
                    return when{
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->{


                            Log.d("UDELP","TRANSPORT_WIFI")
                            true
                        }
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->{


                            Log.d("UDELP","TRANSPORT_CELLULAR")
                            true
                        }
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->{


                            Log.d("UDELP","TRANSPORT_ETHERNET")
                            true
                        }
                        else->{
                            Log.d("UDELP","**NADA**")

                            false
                        }
                    }
                }
            }else{
                Log.d("UDELP","NETWORK NULL")
                return false
            }

        }else{
            Log.d("UDELP","**OLD VERSIONS**")
            connectivityManager.activeNetworkInfo.run {
                return isConnected
            }
        }
    }
}