package com.example.escuestas

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.escuestas.Data.EncuestasDb
import com.example.escuestas.Data.UsuarioDb.Companion.usuario_registrado
import com.example.escuestas.Entity.EntityEncuesta
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    var encuestaS = EntityEncuesta()
    val encuestaBase= EncuestasDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val position = intent.extras?.getString("ID")

        if (position != null) {
            encuestaS = encuestaBase.encuestasGetOne(position.toInt())
        }


        edtNombreE.setText("${encuestaS.nombre}")
        edtApellidosEditar.setText("${encuestaS.ApellidoPaterno} ${encuestaS.ApellidoMaterno}")
        etdCorreoEditar.setText("${encuestaS.correo}")
        var genero=""
        if (encuestaS.genero == 1) {
            genero = "Masculino"
        } else {
            genero = "Femenino"
        }
        txtvGeneroEditar.setText("$genero")
        spnAero.setSelection(encuestaS.viajado)
        spnFrecuencia.setSelection(encuestaS.frecuencia)
        spnExperiencia.setSelection(encuestaS.experiencia)
        edtMejorar.setText(encuestaS.mejora)
        if(encuestaS.promo){
            ckbPromo.isChecked = true
        }
        if(encuestaS.ejecutiva){
            ckbEjecutiva.isChecked = true
        }
        if(encuestaS.economica){
            ckbEconomica.isChecked = true
        }
        if(encuestaS.servicio == "Excelente"){
            rdbExcelente.isChecked =true

        }
        if(encuestaS.servicio == "Bueno"){
            rdbBueno.isChecked =true

        }
        if(encuestaS.servicio == "Malo"){
            rdbMalo.isChecked =true

        }
        if(encuestaS.ofertas == true){
            swtOfertasDescuentos.isChecked = true
        }


        btnEnviar.setOnClickListener {



            var viajado = ""
            var frecuencia = ""
            var exp = ""
            var ofertas = ""


            val aeropatitoposition = spnAero.selectedItemPosition
            if (aeropatitoposition > 0) {
                encuestaS.viajado = aeropatitoposition

                val frecuenciaposition = spnFrecuencia.selectedItemPosition
                if (frecuenciaposition > 0) {
                    encuestaS.frecuencia = frecuenciaposition
                    val experienciaposition = spnExperiencia.selectedItemPosition
                    if (experienciaposition > 0) {
                        encuestaS.experiencia = experienciaposition

                        val selectedServicio = rdgServicios.checkedRadioButtonId
                        if (selectedServicio != -1) {
                            when (selectedServicio) {
                                rdbExcelente.id -> {
                                    encuestaS.servicio = "Excelente"
                                }
                                rdbBueno.id -> {
                                    encuestaS.servicio = "Bueno"
                                }
                                rdbMalo.id -> {
                                    encuestaS.servicio = "Malo"
                                }
                            }
                            if ((ckbEconomica.isChecked) or (ckbEjecutiva.isChecked) or (ckbPromo.isChecked)) {
                                if(ckbEconomica.isChecked){
                                    encuestaS.economica = true
                                }
                                if(ckbPromo.isChecked){
                                    encuestaS.promo = true
                                }
                                if(ckbEjecutiva.isChecked){
                                    encuestaS.ejecutiva = true
                                }

                                Log.d("udelp", encuestaS.correo)
                                if (swtOfertasDescuentos.isChecked) {
                                    encuestaS.ofertas = true
                                }
                                encuestaS.mejora = edtMejorar.text.toString()

                                if (position != null) {
                                    encuestaS.usuario = usuario_registrado.id
                                    encuestaBase.encuestasEdit(encuestaS)
                                }


                                Toast.makeText(this@EditActivity,"Encuesta guardada ${encuestaS.nombre} ${encuestaS.ApellidoPaterno} ",Toast.LENGTH_SHORT).show()

                            } else {
                                Snackbar.make(it,"Ingrese tipo de esquema usaste ", Snackbar.LENGTH_SHORT).show()

                            }
                        }else{
                            Snackbar.make(it,"Ingrese como es nuestro servicio",Snackbar.LENGTH_SHORT).show()
                        }
                    }else{
                        Snackbar.make(it,"Ingrese tu experiencia",Snackbar.LENGTH_SHORT).show()
                    }
                }else{
                    Snackbar.make(it, "Ingrese la frecuencia", Snackbar.LENGTH_SHORT).show()
                }
            } else{
                Snackbar.make(it, "Ingrese si has viajado con nosotros", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}