package com.example.escuestas.Data

import android.util.Log
import com.example.escuestas.Entity.EntityUser

class ListUsers {
    companion object {
        val listaUsers = arrayListOf<EntityUser>()

    }
    public fun agregarU(user:EntityUser): Int{
        listaUsers.add(user)
        Log.d("UDELP_AGREGA_IU","$user")
        return listaUsers.size
    }

    }




