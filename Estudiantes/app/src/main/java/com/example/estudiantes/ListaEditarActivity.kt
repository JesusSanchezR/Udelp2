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
import kotlinx.android.synthetic.main.activity_lista_editar.*

class ListaEditarActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        val estudiantesBase = StudentsDb(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_editar)


        if( estudiantesBase.studentsGetAllHelp().size >0){
            val adaptador = ArrayAdapter<String> (this@ListaEditarActivity, android.R.layout.simple_list_item_1,estudiantesBase.studentsGetAllHelp())
            ltvEditar.adapter=adaptador
            ltvEditar.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->
                val estudiante =  StudentsDb.list[id.toInt()]
                Toast.makeText(
                    this@ListaEditarActivity, "Se selecciono el estudiante: $estudiante ",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this@ListaEditarActivity, EditarActivity::class.java)
                intent.putExtra("ID", estudiante.trim())
                startActivity(intent)
            }

        }











    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itmAlta -> {
                val intent = Intent(this@ListaEditarActivity, AltaActivity::class.java)
                startActivity(intent)
            }


            R.id.itmEditar -> {
                val intent = Intent(this@ListaEditarActivity, ListaEditarActivity::class.java)
                startActivity(intent)

            }

            R.id.itmEliminar -> {
                val intent = Intent(this@ListaEditarActivity, EliminarActivity::class.java)
                startActivity(intent)

            }
            R.id.itmLista -> {
                val intent = Intent(this@ListaEditarActivity, ListaActivity::class.java)
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