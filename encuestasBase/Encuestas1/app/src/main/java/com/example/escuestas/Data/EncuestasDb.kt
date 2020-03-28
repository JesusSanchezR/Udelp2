package com.example.escuestas.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.escuestas.Data.UsuarioDb.Companion.usuario_registrado
import com.example.escuestas.Data.UsuarioDb.Companion.usuario_registrado
import com.example.escuestas.Entity.EntityEncuesta
import com.example.estudiantes.Data.ConnectionDb

class EncuestasDb {
    private var connectionDb:ConnectionDb
    private lateinit var sqliteDataBase: SQLiteDatabase

    constructor(context: Context){
        connectionDb= ConnectionDb(context)
    }

    fun  encuestasAdd(Encuesta: EntityEncuesta): Long{
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues()
        values.put(NombreEn,Encuesta.nombre)
        values.put(ApellidoPEn,Encuesta.ApellidoPaterno)
        values.put(ApellidoMEn,Encuesta.ApellidoMaterno)
        values.put(CorreoEn,Encuesta.correo)
        values.put(GeneroEn,Encuesta.genero)
        values.put(ViajadoEn,Encuesta.viajado)
        values.put(FrecuenciaEn,Encuesta.frecuencia)
        values.put(ExperienciaEn,Encuesta.experiencia)
        values.put(EjecutivaEn,Encuesta.ejecutiva)
        values.put(EconomicaEn,Encuesta.economica)
        values.put(PromoEn,Encuesta.promo)
        values.put(ServicioEn,Encuesta.servicio)
        values.put(MejoraEn,Encuesta.mejora)
        values.put(OfertasEn,Encuesta.ofertas)
        values.put(UserIdEn,Encuesta.usuario)
        values.put(DateEn,Encuesta.date)

        return sqliteDataBase.insert(ConnectionDb.TABLE_NAME_ENCUESTAS,null,values)
    }
    fun  encuestasDelete(idEncuesta:Int): Int {

        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)
        var selection = "Id=?"
        var args = arrayOf(idEncuesta.toString())
        sqliteDataBase.delete(ConnectionDb.TABLE_NAME_ENCUESTAS,selection,args)
        return sqliteDataBase.delete(ConnectionDb.TABLE_NAME_ENCUESTAS,selection,args)

    }
    fun  encuestasEdit(Encuesta: EntityEncuesta): Int {

        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues()
        values.put(NombreEn,Encuesta.nombre)
        values.put(ApellidoPEn,Encuesta.ApellidoPaterno)
        values.put(ApellidoMEn,Encuesta.ApellidoMaterno)
        values.put(CorreoEn,Encuesta.correo)
        values.put(GeneroEn,Encuesta.genero)
        values.put(ViajadoEn,Encuesta.viajado)
        values.put(FrecuenciaEn,Encuesta.frecuencia)
        values.put(ExperienciaEn,Encuesta.experiencia)
        values.put(EjecutivaEn,Encuesta.ejecutiva)
        values.put(EconomicaEn,Encuesta.economica)
        values.put(PromoEn,Encuesta.promo)
        values.put(ServicioEn,Encuesta.servicio)
        values.put(MejoraEn,Encuesta.mejora)
        values.put(OfertasEn,Encuesta.ofertas)
        values.put(UserIdEn,Encuesta.usuario)
        values.put(DateEn,Encuesta.date)

        var selection = "Id=?"
        var args = arrayOf(Encuesta.id.toString())

        return sqliteDataBase.update(ConnectionDb.TABLE_NAME_ENCUESTAS,values,selection,args)

    }
    fun encuestasGetOne(idencuesta:Int):EntityEncuesta{

        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, NombreEn,ApellidoPEn, ApellidoMEn,
            CorreoEn,GeneroEn,ViajadoEn,FrecuenciaEn,
            ExperienciaEn, EjecutivaEn, EconomicaEn, PromoEn, ServicioEn, MejoraEn, OfertasEn,
            UserIdEn, DateEn)

        var selection = "Id=?"
        var args = arrayOf(idencuesta.toString())
        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_ENCUESTAS,fields,selection,args,null,null,null)
        var encuesta = EntityEncuesta()

        if(cursor.moveToFirst()){
            var ejecutiva = cursor.getInt(9)
            var ejecutivaB = ( ejecutiva == 1)?:true
            var economica = cursor.getInt(10)
            var economicaB = ( economica == 1)?:true
            var promo = cursor.getInt(11)
            var promoB = ( promo == 1)?:true
            var ofertas = cursor.getInt(14)
            var ofertasB = ( ofertas == 1)?:true

            encuesta= EntityEncuesta( cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6),
                cursor.getInt(7),cursor.getInt(8),ejecutivaB,economicaB,
                promoB,cursor.getString(12),cursor.getString(13),
                ofertasB,cursor.getInt(15),cursor.getString(16))
            Log.d("UDELP_ONE_E","${cursor.getInt(0)} " +
                    "${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getString(3)} " +
                    "${cursor.getString(4)}  ${cursor.getInt(5)} ${cursor.getInt(6)} " +
                    "${cursor.getInt(7)} ${cursor.getInt(8)} ${cursor.getInt(9)} ${cursor.getInt(10)} " +
                    "${cursor.getInt(11)}  ${cursor.getString(12)}  ${cursor.getString(13)} " +
                    "${cursor.getInt(14)}  ${cursor.getInt(15)} ${cursor.getString(16)}")
            Log.d("N",cursor.getString(13))

        }
        return  encuesta
    }
    fun encuestasGetAll(){

        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, NombreEn,ApellidoPEn, ApellidoMEn,
            CorreoEn,GeneroEn,ViajadoEn,FrecuenciaEn,
            ExperienciaEn, EjecutivaEn, EconomicaEn, PromoEn, ServicioEn, MejoraEn, OfertasEn,
            UserIdEn, DateEn)

        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_ENCUESTAS,fields,null,null,null,null,null)

        if(cursor.moveToFirst()){
            do {

                Log.d("UDELP_ALL_E","${cursor.getInt(0)} " +
                        "${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getString(3)} " +
                        "${cursor.getString(4)}  ${cursor.getInt(5)} ${cursor.getInt(6)} " +
                        "${cursor.getInt(7)} ${cursor.getInt(8)} ${cursor.getInt(9)} ${cursor.getInt(10)} " +
                        "${cursor.getInt(11)}  ${cursor.getString(12)}  ${cursor.getString(13)} " +
                        "${cursor.getInt(14)}  ${cursor.getInt(15)} ${cursor.getString(16)}")
                Log.d("N",cursor.getString(13))
            }while (cursor.moveToNext())

        }
    }

    fun encuestasGetAllHelp(): Array<String>{
        listStringEN.clear()
        listI.clear()
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)

        val fields = arrayOf(ID, NombreEn,ApellidoPEn, ApellidoMEn,
            CorreoEn,GeneroEn,ViajadoEn,FrecuenciaEn,
            ExperienciaEn, EjecutivaEn, EconomicaEn, PromoEn, ServicioEn, MejoraEn, OfertasEn,
            UserIdEn, DateEn)

        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_ENCUESTAS,fields,null,null,null,null,null)

        if(cursor.moveToFirst()){
            do {
                if (UsuarioDb.usuario_registrado.id == cursor.getInt(15)){
                    listStringEN.add("${cursor.getString(1)} ${cursor.getString(2)}  ${cursor.getString(3)}")
                    listI.add("${cursor.getInt(0)}")
                    Log.d("UDELP","${cursor.getInt(0)} " +
                            "${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getString(3)} " +
                            "${cursor.getString(4)}  ${cursor.getInt(5)} ${cursor.getInt(6)} " +
                            "${cursor.getInt(7)} ${cursor.getInt(8)} ${cursor.getInt(9)} ${cursor.getInt(10)} " +
                            "${cursor.getInt(11)}  ${cursor.getString(12)}  ${cursor.getString(13)} " +
                            "${cursor.getInt(14)}  ${cursor.getInt(15)} ${cursor.getString(16)}")

                }
            }while (cursor.moveToNext())

        }
        val elems = listStringEN
        return elems.toTypedArray()
    }





    companion object {
        private  val listStringEN = arrayListOf<String>()

        const val ID = "Id"
        const val NombreEn = "Nombre"
        const val ApellidoPEn = "ApellidoP"
        const val ApellidoMEn = "ApellidoM"
        const val CorreoEn ="Correo"
        const val GeneroEn = "Genero"
        const val ViajadoEn = "Viajado"
        const val FrecuenciaEn ="Frecuencia"
        const val ExperienciaEn   ="Experiencia"
        const val EjecutivaEn = "Ejecutiva"
        const val EconomicaEn   = "Economica"
        const val PromoEn = "Promo"
        const val  ServicioEn ="Servicio"
        const val  MejoraEn = "Mejora"
        const val  OfertasEn = "Ofertas"
        const val  UserIdEn = "UserId"
        const val  DateEn = "Date"

        val listI = arrayListOf<String>()

    }
}