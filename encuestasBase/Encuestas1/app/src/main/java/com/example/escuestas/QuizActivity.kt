package com.example.escuestas

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.escuestas.Data.EncuestasDb
import com.example.escuestas.Data.ListEncuestas
import com.example.escuestas.Data.UsuarioDb.Companion.usuario_registrado
import com.example.escuestas.Email.Companion.validarCorreo
import com.example.escuestas.Entity.EntityEncuesta
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_encuesta.*

class QuizActivity : AppCompatActivity() {


    //pasado---val mispelotas =ListEncuestas()

    val misencuestas = ListEncuestas()
    var user =""
    val encuestasBase = EncuestasDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encuesta)
        user = intent.extras?.getString("usuario").toString()
        btnEnviar.setOnClickListener {
            val miencuesta = EntityEncuesta()
            if (edtNombreE.text.toString().trim().isNotEmpty()) {
                miencuesta.nombre = edtNombreE.text.toString()
                if (edtApellidoPaternoE.text.toString().trim().isNotEmpty()) {
                    miencuesta.ApellidoPaterno = edtApellidoPaternoE.text.toString()
                    if (edtApellidoMaternoE.text.toString().trim().isNotEmpty()) {
                        miencuesta.ApellidoMaterno = edtApellidoMaternoE.text.toString()
                        if ((etdCorreoE.text.toString().trim().isNotEmpty()) and (validarCorreo(etdCorreoE.text.toString().trim().toString()))){
                            miencuesta.correo = etdCorreoE.text.toString()
                            val generoposition = spnGeneroE.selectedItemPosition
                            if (generoposition > 0){
                                miencuesta.genero = generoposition
                                val genero = spnGeneroE.getSelectedItemId().toString()
                                Log.d("udelp","Seleccionaste Genero $genero")
                                val aeropatitoposition = spnAero.selectedItemPosition
                                if (aeropatitoposition > 0){

                                    miencuesta.viajado = aeropatitoposition

                                    val frecuenciaposition = spnFrecuencia.selectedItemPosition

                                    if (frecuenciaposition > 0){
                                        miencuesta.frecuencia = frecuenciaposition
                                        val experienciaposition =spnExperiencia.selectedItemPosition
                                        if (experienciaposition > 0){
                                            miencuesta.experiencia = experienciaposition

                                            val selectedServicio = rdgServicios.checkedRadioButtonId
                                            if (selectedServicio != -1){
                                                when(selectedServicio){
                                                    rdbExcelente.id->{
                                                        miencuesta.servicio = "Excelente"
                                                    }
                                                    rdbBueno.id->{
                                                        miencuesta.servicio  = "Bueno"
                                                    }
                                                    rdbMalo.id->{
                                                        miencuesta.servicio  = "Malo"
                                                    }
                                                }

                                                if((ckbEconomica.isChecked) or (ckbEjecutiva.isChecked) or (ckbPromo.isChecked)){
                                                    if(ckbEconomica.isChecked){
                                                        miencuesta.economica = true
                                                    }
                                                    if(ckbPromo.isChecked){
                                                        miencuesta.promo = true
                                                    }
                                                    if(ckbEjecutiva.isChecked){
                                                        miencuesta.ejecutiva = true
                                                    }


                                                    Log.d("checkbox",miencuesta.correo)
                                                    if(swtOfertasDescuentos.isChecked){
                                                        miencuesta.ofertas =true
                                                    }
                                                    miencuesta.mejora = edtMejorar.text.toString()
                                                    miencuesta.usuario = usuario_registrado.id//cuando sea con base de datos cambiar por id
                                                    encuestasBase.encuestasAdd(miencuesta)
                                                    Toast.makeText(this@QuizActivity,"Guardado ",Toast.LENGTH_SHORT).show()
                                                    limpiar()


                                                }else{
                                                    Snackbar.make(it,"Ingresa que tipo de esquema usaste ",Snackbar.LENGTH_SHORT).show()

                                                }

                                            }else{
                                                Snackbar.make(it,"Ingresa  como es nuestro servicio",Snackbar.LENGTH_SHORT).show()
                                            }


                                        }else{
                                            Snackbar.make(it,"Ingresa  tu experiencia",Snackbar.LENGTH_SHORT).show()
                                        }

                                    }else{
                                        Snackbar.make(it, "Ingresa  la frecuencia", Snackbar.LENGTH_SHORT).show()
                                    }

                                }else{
                                    Snackbar.make(it, "Ingresa si has viajado con nosotros", Snackbar.LENGTH_SHORT).show()
                                }


                            }else{
                                Snackbar.make(it, "Ingresa selecciona Genero", Snackbar.LENGTH_SHORT).show()
                            }


                        }else{
                            Snackbar.make(it, "Ingresa  el Correo", Snackbar.LENGTH_SHORT).show()
                        }


                    } else {
                        Snackbar.make(it, "Ingresa  el Apellido Materno", Snackbar.LENGTH_SHORT).show()
                    }


                } else {
                    Snackbar.make(it, "Ingresa  el Apellido Paterno", Snackbar.LENGTH_SHORT).show()
                }


            } else {
                Snackbar.make(it, "Ingresa  el nombre", Snackbar.LENGTH_SHORT).show()


            }

        }
    }

    fun limpiar(){
        edtNombreE.text.clear()
        edtApellidoPaternoE.text.clear()
        edtApellidoMaternoE.text.clear()
        etdCorreoE.text.clear()
        spnGeneroE.setSelection(0)
        spnAero.setSelection(0)
        spnFrecuencia.setSelection(0)
        spnExperiencia.setSelection(0)
        ckbEconomica.isChecked = false
        ckbPromo.isChecked = false
        ckbEjecutiva.isChecked = false
        rdgServicios.clearCheck()
        edtMejorar.text.clear()
        swtOfertasDescuentos.isChecked = false

    }
}