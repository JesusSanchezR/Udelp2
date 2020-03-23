package com.example.estudiantes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.estudiantes.Data.StudentsDb
import com.example.estudiantes.Data.StudentsEntity
import kotlinx.android.synthetic.main.activity_detalle.*

class DetalleActivity: AppCompatActivity() {
    val estudiantesBase = StudentsDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        val id= intent.extras?.getString("ID")
        var estudiante= StudentsEntity()

        if (id != null) {
            estudiante = estudiantesBase.studentsGetOne(id.toInt())
        }

        txvNumeroDetalle.setText("${estudiante.id.toString()}")
        txvNombreDetalle.setText("${estudiante.name.toString()} ")
        txvApellidoPDetalle.setText("${estudiante.lastName.toString()}")
        txvApellidoMDetalle.setText("${estudiante.apellidoMaterno.toString()}")


        when(estudiante.gender){
            1->{
                txvGeneroDetalle.setText("Masculino")
            }
            2->{
                txvGeneroDetalle.setText("Femenino")
            }

        }

        txvFechaNacimientoDetalle.setText(estudiante.birthday)

        when(estudiante.nivelAcademico){
            1->{
                txvNivelAcademicoDetalle.setText("Secundaria")
            }
            2->{
                txvNivelAcademicoDetalle.setText("Prepa")
            }
            3->{
                txvNivelAcademicoDetalle.setText("Universidad")

            }
        }

        when(estudiante.escuelaProcedencia){
            1->{
                txvUniversidadProcedenciaDetalle.setText("UVM")
            }
            2->{
                txvUniversidadProcedenciaDetalle.setText("UNAM")
            }
            3->{
                txvUniversidadProcedenciaDetalle.setText("TEC MONTERREY")
            }
            4->{
                txvUniversidadProcedenciaDetalle.setText("UDELP")
            }
            5->{
                txvUniversidadProcedenciaDetalle.setText("UNITEC")
            }
            6->{
                txvUniversidadProcedenciaDetalle.setText("ANAHUAC")
            }


        }

        txvTelefonoDetalle.setText(estudiante.telefono)
        txvCorreoDetalle.setText(estudiante.correo)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itmAlta -> {
                val intent = Intent(this@DetalleActivity, AltaActivity::class.java)
                startActivity(intent)
            }


            R.id.itmEditar -> {
                val intent = Intent(this@DetalleActivity, ListaEditarActivity::class.java)
                startActivity(intent)

            }

            R.id.itmEliminar -> {
                val intent = Intent(this@DetalleActivity, EliminarActivity::class.java)
                startActivity(intent)

            }
            R.id.itmLista -> {
                val intent = Intent(this@DetalleActivity, ListaActivity::class.java)
                startActivity(intent)

            }
        }
        return true

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity,menu)
        return super.onCreateOptionsMenu(menu)
    }

}