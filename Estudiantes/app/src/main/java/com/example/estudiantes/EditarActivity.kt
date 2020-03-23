package com.example.estudiantes

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.estudiantes.Data.StudentsDb
import com.example.estudiantes.Data.StudentsEntity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_alta.*
import kotlinx.android.synthetic.main.activity_detalle.*
import kotlinx.android.synthetic.main.activity_editar.*
import java.util.*

class EditarActivity: AppCompatActivity() {
    val estudiantesBase = StudentsDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)
        val id = intent.extras?.getString("ID")
        var idEdit =-1


        var estudiantes = StudentsEntity()
        if (id != null) {
            estudiantes = estudiantesBase.studentsGetOne(id.toInt())
            idEdit = id.toInt()
        }

        txvNumeroDetalleEdicion.setText("${estudiantes.id.toString()}")
        txvNombreEdicion.setText("${estudiantes.name.toString()}")
        txvApellidoPEdicion.setText("${estudiantes.lastName.toString()}")
        txvApellidoMEdicion.setText("${estudiantes.apellidoMaterno.toString()}")
        txvGeneroEdicion.setText("${estudiantes.gender.toString()}")
        txvFechaNacimientoEdicion.setText("${estudiantes.birthday.toString()}")

        when (estudiantes.gender) {
            1 -> {
                txvGeneroEdicion.setText("Masculino")
            }
            2 -> {
                txvGeneroEdicion.setText("Femenino")
            }

        }
        spnEscuelaProcedenciaEdicion.setSelection(estudiantes.escuelaProcedencia)
        edtTelefonoEdicion.setText(estudiantes.telefono)
        edtCorreoEdicion.setText(estudiantes.correo)

        btnEditar.setOnClickListener{
            val selectedNivelAcademico = rdgNivelAcademicoEdicion.checkedRadioButtonId
            if (selectedNivelAcademico != -1) {
                var nivelAcademico = 0
                when (selectedNivelAcademico) {
                    rdgSecundariaE.id -> {
                        nivelAcademico = 1
                    }
                    rdgPrepaE.id -> {
                        nivelAcademico = 2
                    }
                    rdgUniversidadE.id -> {
                        nivelAcademico = 3
                    }
                }



                val escuelaposition = spnEscuelaProcedenciaEdicion.selectedItemPosition

                if (escuelaposition > 0) {
                    if (edtTelefonoEdicion.text.toString().trim().isNotEmpty()) {
                        var phone = edtTelefonoEdicion.text.toString()
                        if ((edtCorreoEdicion.text.toString().trim().isNotEmpty())
                        ) {
                            var correo = edtCorreoEdicion.text.toString()




                                var values = StudentsEntity(
                                    idEdit,
                                    estudiantes.name,
                                    estudiantes.lastName,
                                    estudiantes.apellidoMaterno,
                                    estudiantes.gender,
                                    estudiantes.birthday,
                                    nivelAcademico,
                                    escuelaposition,
                                    phone,
                                    correo
                                )
                                var id = estudiantesBase.studentEdit(values)
                                Log.d("UDELP", "El estudiante que se edito es: $id")

                            estudiantesBase.studentsGetAllHelp()
                                Toast.makeText(this@EditarActivity, "Se edito el estudiante", Toast.LENGTH_LONG).show()






                        } else {
                            Toast.makeText(
                                this@EditarActivity, "Ingresa el correo", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(
                            this@EditarActivity, "Ingresa telefono", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(
                        this@EditarActivity, "Ingresa escuela de procedencia", Toast.LENGTH_LONG).show()
                }

            }else{
                Toast.makeText(
                    this@EditarActivity, "Ingresa el nivel Academico", Toast.LENGTH_LONG).show()
            }
        }

    }
}

