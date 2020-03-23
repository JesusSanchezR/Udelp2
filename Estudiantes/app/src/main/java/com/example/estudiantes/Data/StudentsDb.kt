package com.example.estudiantes.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class StudentsDb {
    private var connectionDb:ConnectionDb
    private lateinit var sqliteDataBase: SQLiteDatabase

    constructor(context: Context){
        connectionDb= ConnectionDb(context)
    }

    fun  studentAdd(student:StudentsEntity): Long{
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues()
        values.put(NAME,student.name)
        values.put(LASTNAME,student.lastName)
        values.put(APELLIDOMATERNO,student.apellidoMaterno)
        values.put(GENDER,student.gender)
        values.put(BIRTHDAY,student.birthday)
        values.put(NIVELAC,student.nivelAcademico)
        values.put(ESCPRO,student.escuelaProcedencia)
        values.put(TELEFONO,student.telefono)
        values.put(CORREO,student.correo)

        return sqliteDataBase.insert(ConnectionDb.TABLE_NAME_STUDENTS,null,values)
    }

    fun  studentEdit(student:StudentsEntity): Int {

        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues()
        values.put(NAME,student.name)
        values.put(LASTNAME,student.lastName)
        values.put(APELLIDOMATERNO,student.apellidoMaterno)
        values.put(GENDER,student.gender)
        values.put(BIRTHDAY,student.birthday)
        values.put(NIVELAC,student.nivelAcademico)
        values.put(ESCPRO,student.escuelaProcedencia)
        values.put(TELEFONO,student.telefono)
        values.put(CORREO,student.correo)

        var selection = "Id=?"
        var args = arrayOf(student.id.toString())

        return sqliteDataBase.update(ConnectionDb.TABLE_NAME_STUDENTS,values,selection,args)

    }

    fun  studentDelete(idStudent:Int): Int {

        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)
        var selection = "Id=?"
        var args = arrayOf(idStudent.toString())
        sqliteDataBase.delete(ConnectionDb.TABLE_NAME_STUDENTS,selection,args)
        return sqliteDataBase.delete(ConnectionDb.TABLE_NAME_STUDENTS,selection,args)

    }

    fun studentsGetAll(){
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, NAME, LASTNAME, APELLIDOMATERNO, GENDER, BIRTHDAY, NIVELAC, ESCPRO,
            TELEFONO, CORREO)

        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_STUDENTS,fields,null,null,null,null,null)

        if(cursor.moveToFirst()){
            do {
                Log.d("UDELP","${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getString(3)} ${cursor.getString(4)} ${cursor.getString(5)} ${cursor.getString(6)} ${cursor.getString(7)} ${cursor.getString(8)} ${cursor.getString(9)}")

            }while (cursor.moveToNext())

        }


    }

    fun studentsGetOne(idStudent:Int): StudentsEntity {
        var estudiantes= StudentsEntity()

        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, NAME, LASTNAME, APELLIDOMATERNO, GENDER, BIRTHDAY, NIVELAC, ESCPRO,
            TELEFONO, CORREO)
        var selection = "Id=?"
        var args = arrayOf(idStudent.toString())
        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_STUDENTS,fields,selection,args,null,null,null)

        if(cursor.moveToFirst()){
            var id=cursor.getInt(0)
            var name=cursor.getInt(1)
            var lastname=cursor.getString(2)
            var apellidoMaterno=cursor.getString(3)
            var gender=cursor.getInt(4)
            var birthday=cursor.getString(5)
            var telefono=cursor.getString(8)
            var correo=cursor.getString(9)
            estudiantes= StudentsEntity(cursor.getInt(0),"${name}","$lastname",apellidoMaterno,gender,birthday,cursor.getInt(6),cursor.getInt(7),telefono,correo)
            Log.d("UDELP","${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(3)} ${cursor.getString(4)}  ${cursor.getString(5)} ${cursor.getString(6)} ${cursor.getString(7)} ${cursor.getString(8)} ${cursor.getString(9)}")
        }
        return estudiantes
    }

    fun studentsGetAllHelp():Array<String>{
        //limpiamos
        listaEst.clear()
        list.clear()
        //Igual que getone, en esta parte
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, NAME, LASTNAME, GENDER, BIRTHDAY)
        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_STUDENTS,fields,null,null,null,null,null)

        if(cursor.moveToFirst()){
            do {
                listaEst.add("${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(2)} ${cursor.getString(4)}")
                Log.d("UDELP","${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(3)} ${cursor.getString(4)}")
                list.add("${cursor.getInt(0)} ")
            }while (cursor.moveToNext())

        }
        val elems = listaEst
        return elems.toTypedArray()


    }

    companion object {
        private val listaEst= arrayListOf<String>()
        const val ID = "id"
        const val NAME = "Name"
        const val LASTNAME = "Lastname"
        const val APELLIDOMATERNO="ApellidoMaterno"
        const val GENDER = "Gender"
        const val BIRTHDAY = "Birthday"
        const val NIVELAC= "nivelAcademico"
        const val ESCPRO="escuelaProcedencia"
        const val TELEFONO="telefono"
        const val CORREO="correo"

        val list= arrayListOf<String>()

    }
}