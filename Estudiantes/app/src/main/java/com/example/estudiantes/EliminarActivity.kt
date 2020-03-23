package com.example.estudiantes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.estudiantes.Data.StudentsDb
import kotlinx.android.synthetic.main.activity_eliminar.*

class EliminarActivity: AppCompatActivity() {
    val estudiantesBase = StudentsDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar)
        if( estudiantesBase.studentsGetAllHelp().size>0){
            val adaptador = ArrayAdapter<String> (this@EliminarActivity, android.R.layout.simple_list_item_1,estudiantesBase.studentsGetAllHelp())
            ltvEliminar.adapter=adaptador

            ltvEliminar.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->

                var estudiante =  StudentsDb.list[id.toInt()]
                Toast.makeText(
                    this@EliminarActivity, "El estudiante seleccionado es: $estudiante ",
                    Toast.LENGTH_SHORT
                ).show()
                alerta("Se va a eliminar el estudiante: ${estudiante.trim().toString()}",estudiante.trim().toInt()).show()

            }

        }

    }

    private fun alerta(texto: String, id: Int): AlertDialog {
        val emergente = AlertDialog.Builder(this@EliminarActivity)
        emergente.setTitle("Cuidado!! deseas continuar eliminando?").setMessage(texto)
        emergente.setPositiveButton("SI"){dialog,which ->
            estudiantesBase.studentDelete(id)
            Toast.makeText(this@EliminarActivity,"Se elimino es estudiante!!", Toast.LENGTH_SHORT).show()
            finish();
            startActivity(intent);
        }
        emergente.setNegativeButton("NO"){ dialog,which ->
            Toast.makeText(this@EliminarActivity,"Eliminacion cancelada", Toast.LENGTH_SHORT).show()
        }
        return emergente.create()








    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itmAlta -> {
                val intent = Intent(this@EliminarActivity, AltaActivity::class.java)
                startActivity(intent)
            }


            R.id.itmEditar -> {
                val intent = Intent(this@EliminarActivity, ListaEditarActivity::class.java)
                startActivity(intent)

            }

            R.id.itmEliminar -> {
                val intent = Intent(this@EliminarActivity, EliminarActivity::class.java)
                startActivity(intent)

            }
            R.id.itmLista -> {
                val intent = Intent(this@EliminarActivity, ListaActivity::class.java)
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