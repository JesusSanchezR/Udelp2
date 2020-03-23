package com.example.estudiantes

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.estudiantes.Data.StudentsDb
import com.example.estudiantes.Data.StudentsEntity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_alta.*
import java.util.*

class AltaActivity : AppCompatActivity() {
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itmAlta -> {
                val intent = Intent(this@AltaActivity, AltaActivity::class.java)
                startActivity(intent)
            }

            R.id.itmEditar -> {
                val intent = Intent(this@AltaActivity, ListaEditarActivity::class.java)
                startActivity(intent)

            }

            R.id.itmEliminar -> {
                val intent = Intent(this@AltaActivity, EliminarActivity::class.java)
                startActivity(intent)

            }
            R.id.itmLista -> {
                val intent = Intent(this@AltaActivity, ListaActivity::class.java)
                startActivity(intent)

            }
        }
        return true

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity,menu)
        return super.onCreateOptionsMenu(menu)
    }






    val estudiantesBase = StudentsDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alta)

        btnRegistrar.setOnClickListener{

            if (edtNombreAlta.text.toString().trim().isNotEmpty()) {
                var altaNombre =edtNombreAlta.text.toString()
                if (edtApellidoPAlta.text.toString().trim().isNotEmpty()) {
                    var altaApellidoP = edtApellidoPAlta.text.toString()
                    if (edtApellidoMAlta.text.toString().trim().isNotEmpty()) {
                        var altaApellidoM=edtApellidoMAlta.text.toString()

                        val generoposition = spnGeneroAlta.selectedItemPosition
                        if (generoposition > 0){

                            if(edtFechaNacimientoAlta.text.toString().trim().isNotEmpty()){
                                val feNacimiento= edtFechaNacimientoAlta.text.toString()

                                val nivelAcademicoSelect = rdgNivelAcademico.checkedRadioButtonId
                                if (nivelAcademicoSelect != -1){
                                    var nivelAcademico=0
                                    when(nivelAcademicoSelect){
                                        rdgSecundaria.id->{
                                           nivelAcademico=1
                                        }
                                        rdgPrepa.id->{
                                            nivelAcademico=2
                                        }
                                        rdgUniversidad.id->{
                                            nivelAcademico=3
                                        }
                                    }



                                    val procedenciaEscuela = spnEscuelaProcedencia.selectedItemPosition
                                    if (procedenciaEscuela > 0){


                                        if (edtTelefonoAlta.text.toString().trim().isNotEmpty()) {
                                            val telefono= edtTelefonoAlta.text.toString()




                                            if (edtCorreoAlta.text.toString().trim().isNotEmpty()) {
                                                val correo=edtCorreoAlta.text.toString()


                                              var datos = StudentsEntity(-1,altaNombre,altaApellidoP,altaApellidoM,generoposition,feNacimiento,nivelAcademico,procedenciaEscuela,telefono,correo)
                                              var id = estudiantesBase.studentAdd(datos)
            //--------------------------------------------------------------------------------------------------
                                                Log.d("UDELP","Se guardo el estudiante $id $altaApellidoP")
                                                edtNombreAlta.text.clear()
                                                edtApellidoPAlta.text.clear()
                                                edtApellidoMAlta.text.clear()
                                                spnGeneroAlta.setSelection(0)
                                                edtFechaNacimientoAlta.setText("Fecha Nacimiento")
                                                rdgNivelAcademico.clearCheck()
                                                spnEscuelaProcedencia.setSelection(0)
                                                edtTelefonoAlta.text.clear()
                                                edtCorreoAlta.text.clear()




                                            } else {
                                                Snackbar.make(it, "Porfavor ingresa el correo", Snackbar.LENGTH_SHORT).show()
                                            }






                                        } else {
                                            Snackbar.make(it, "Porfavor ingresa el telefono", Snackbar.LENGTH_SHORT).show()
                                        }





                                        }else{
                                        Snackbar.make(it, "porfavor selecciona Escuela de procedencia", Snackbar.LENGTH_SHORT).show()
                                         }

                                }else{
                                    Snackbar.make(it,"porfavor Selecciona el nivel academico",Snackbar.LENGTH_SHORT).show()
                                }



                            }else{
                                    Snackbar.make(it, "porfavor seleccione la fecha de nacimiento", Snackbar.LENGTH_SHORT).show()
                                }




                        }else{
                            Snackbar.make(it, "porfavor selecciona Genero", Snackbar.LENGTH_SHORT).show()
                        }




                    } else {
                        Snackbar.make(it, "Porfavor ingresa el Apellido Materno", Snackbar.LENGTH_SHORT).show()
                    }



                } else {
                    Snackbar.make(it, "Porfavor ingresa el Apellido Paterno", Snackbar.LENGTH_SHORT).show()
                }


            } else {
                Snackbar.make(it, "Porfavor ingresa el nombre", Snackbar.LENGTH_SHORT).show()
            }

        }

        //creamos un efecto al editext Y variables para el control del datepicker
        val calendario = Calendar.getInstance()
        val año = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        edtFechaNacimientoAlta.setOnClickListener {
            val fn= DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                var cMes= (monthOfYear + 1)
                var meses =""
                if(cMes in 1..9){
                    meses = ("0")+mes.toString()
                }else{
                    meses = mes.toString()
                }

                edtFechaNacimientoAlta.setText("" + year + "/" + meses + "/" + dayOfMonth)
            }, año, mes, dia)
            fn.show()

        }


    }




}