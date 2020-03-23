package com.example.estudiantes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.estudiantes.Data.StudentsDb
import com.example.estudiantes.Data.StudentsDb.Companion.list
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity: AppCompatActivity() {
    val estudiantesBase = StudentsDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        if( estudiantesBase.studentsGetAllHelp().size >0){
            val adaptador = ArrayAdapter<String> (this@ListaActivity, android.R.layout.simple_list_item_1,estudiantesBase.studentsGetAllHelp())

            ltvEstudiantes.adapter=adaptador

            ltvEstudiantes.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->
                val estudiante =  list[id.toInt()]
                Toast.makeText(
                    this@ListaActivity, "Se selecciono el estudiante: $estudiante ",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this@ListaActivity, DetalleActivity::class.java)
                intent.putExtra("ID", estudiante.trim())
                startActivity(intent)
                estudiantesBase.studentsGetAll()
            }

        }



    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itmAlta -> {
                val intent = Intent(this@ListaActivity, AltaActivity::class.java)
                startActivity(intent)
            }


            R.id.itmEditar -> {
                val intent = Intent(this@ListaActivity, ListaEditarActivity::class.java)
                startActivity(intent)

            }

            R.id.itmEliminar -> {
                val intent = Intent(this@ListaActivity, EliminarActivity::class.java)
                startActivity(intent)

            }
            R.id.itmLista -> {
                val intent = Intent(this@ListaActivity, ListaActivity::class.java)
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