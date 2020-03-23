package com.example.estudiantes.Data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ConnectionDb(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE)
    }

    fun openConnection(typeConnectionBD: Int): SQLiteDatabase {
        return when(typeConnectionBD){
            MODE_WRITE ->
                return writableDatabase
            MODE_READ ->
                return  readableDatabase
            else->
                return readableDatabase
        }
    }

    companion object{
        const val DATABASE_NAME = "UNIVERSIDAD"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME_STUDENTS = "CTL_ESTUDIANTES"
        const val CREATE_TABLE = "CREATE TABLE $TABLE_NAME_STUDENTS(Id INTEGER PRIMARY KEY AUTOINCREMENT,Name VARCHAR(20),Lastname VARCHAR(15),ApellidoMaterno VARCHAR(15),Gender INTERGER,Birthday DATE,NivelAcademico INTERGER,EscuelaProcedencia INTERGER,Telefono VARCHAR(15),Correo VARCHAR(30))"
        const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME_STUDENTS"
        const val MODE_WRITE=1
        const val MODE_READ=2
    }



}