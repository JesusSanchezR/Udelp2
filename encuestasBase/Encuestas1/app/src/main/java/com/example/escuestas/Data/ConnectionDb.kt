package com.example.estudiantes.Data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class ConnectionDb(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USUARIOS)
        db?.execSQL(CREATE_TABLE_ENCUESTAS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE_USUARIOS)
        db?.execSQL(DROP_TABLE_ENCUESTAS)

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
        const val DATABASE_NAME = "ENCUESTAS"
        const val DATABASE_VERSION = 1

        const val TABLE_NAME_USUARIOS= "CTL_USUARIOS"
        const val CREATE_TABLE_USUARIOS = "CREATE TABLE $TABLE_NAME_USUARIOS(Id INTEGER PRIMARY KEY AUTOINCREMENT,Nombre VARCHAR(20),ApellidoP VARCHAR(25),ApellidoM VARCHAR(25),Genero INTEGER,Delegacion INTEGER,Direccion VARCHAR(100),EstadoCivil INTEGER,Correo VARCHAR(50),Password VARCHAR(25))"
        const val DROP_TABLE_USUARIOS = "DROP TABLE IF EXISTS $TABLE_NAME_USUARIOS"

//encuestas
        const val TABLE_NAME_ENCUESTAS = "CTL_ENCUESTAS"
        const val CREATE_TABLE_ENCUESTAS = "CREATE TABLE $TABLE_NAME_ENCUESTAS(Id INTEGER PRIMARY KEY AUTOINCREMENT,Nombre VARCHAR(20),ApellidoP VARCHAR(25),ApellidoM VARCHAR(25),Correo VARCHAR(50),Genero INTEGER,Viajado INTEGER,Frecuencia INTEGER,Experiencia INTEGER,Ejecutiva INTEGER,Economica INTEGER,Promo INTEGER,Servicio VARCHAR(50),Mejora VARCHAR(50),Ofertas INTEGER,UserId INTEGER,Date DATE ,FOREIGN KEY (UserId) REFERENCTAB${TABLE_NAME_USUARIOS}_USUARIOS (Id) )"
        const val DROP_TABLE_ENCUESTAS = "DROP TABLE IF EXISTS $TABLE_NAME_ENCUESTAS"

        const val MODE_WRITE=1
        const val MODE_READ=2


    }



}