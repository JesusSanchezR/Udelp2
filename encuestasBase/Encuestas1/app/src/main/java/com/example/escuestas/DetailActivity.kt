package com.example.escuestas

import android.os.Bundle
import com.example.escuestas.Entity.EntityEncuesta
import kotlinx.android.synthetic.main.activity_detail.*
import androidx.appcompat.app.AppCompatActivity
import com.example.escuestas.Data.EncuestasDb


class DetailActivity : AppCompatActivity(){
    val encuestasDb = EncuestasDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val position = intent.extras?.getString("ID")

        var enc = EntityEncuesta()

        var idencuesta = position?.toInt()
        enc = encuestasDb.encuestasGetOne(idencuesta!!)
        var genero =""
        var viajado=""
        var frecuencia=""
        var exp=""
        var ofertas=""

        txvNombreDetalle.setText("${enc.nombre.toString()}")
        txvNumeroEncuesta.setText(":${idencuesta.toString()}")
        txvApellidoPaternoDetalle.setText("${enc.ApellidoPaterno.toString()}")
        txvApellidoMaternoDetalle.setText("${enc.ApellidoMaterno.toString()}")
        txvCorreoDetalle.setText("${enc.correo.toString()}")

        if(enc.genero==1){
            genero= "Masculino"
        }else{
            genero= "Femenino"
        }
        txvGeneroDetalle.setText("$genero")
        if(enc.viajado==1){
            viajado= "Si ha viajadado en AeroPatito"
        }else{
            viajado= "No"
        }
        txvViajadoDetalle.setText("$viajado")
        when(enc.frecuencia){
            1->{
                frecuencia = "1 a 3 veces al año"
            }
            2->{
                frecuencia = "4 a 7 veces al año"
            }
            3->{
                frecuencia = "mas de 7 veces al año"
            }
        }
        txvFrecuenciaDetalle.setText("$frecuencia")
        when(enc.experiencia){
            1->{
                exp="Mala"
            }
            2->{
                exp="Regular"
            }
            3->{
                exp="Buena"
            }
            4->{
                exp="Excelente"
            }
        }
        txvExperienciaDetalle.setText("$exp")
        //?
        var esquema = ""
        if(enc.economica == true){
            esquema += "Económica "
        }
        if(enc.promo == true){
            esquema += "Promo "
        }
        if(enc.ejecutiva){
            esquema += "Ejecutiva "
        }



        txvEsquemaDetalle.setText("$esquema")


        txvServicioDetalle.setText("${enc.servicio.toString()}")
        txvMejorasDetalle
        txvMejorasDetalle.setText("${enc.mejora}")
        if(enc.ofertas==true){
            ofertas = "Recibir ofertas y descuentos"
        }else{
            ofertas = "NO!! Recibir ofertas y descuentos"
        }
        txvOfertasDescuentosDetalle.setText("$ofertas")

    }
}