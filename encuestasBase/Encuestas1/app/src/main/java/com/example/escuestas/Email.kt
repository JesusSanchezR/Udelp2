package com.example.escuestas

class Email {
    companion object{
        fun validarCorreo(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
}