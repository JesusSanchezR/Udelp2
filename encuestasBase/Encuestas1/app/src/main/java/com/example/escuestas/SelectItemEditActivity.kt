package com.example.escuestas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.escuestas.Data.EncuestasDb
import kotlinx.android.synthetic.main.activity_select_item_edit.*

class SelectItemEditActivity : AppCompatActivity(){
    //pasada---var listaEncuestas = com.example.escuestas.Data.ListEncuestas()
    val encuestasBase = EncuestasDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_item_edit)
        if(encuestasBase.encuestasGetAllHelp().size >0){
            val miAdaptador = ArrayAdapter<String> (this@SelectItemEditActivity, android.R.layout.simple_list_item_1,encuestasBase.encuestasGetAllHelp())
            encuestasBase.encuestasGetAll()
            ltvEditarEncuesta.adapter=miAdaptador

            ltvEditarEncuesta.setOnItemClickListener {
                    adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->

                var index= EncuestasDb.listI[position]
                Toast.makeText(this@SelectItemEditActivity, "Se selecciono la encuesta $index", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@SelectItemEditActivity, EditActivity::class.java)
                intent.putExtra("ID", position.toString())
                startActivity(intent)
            }
        }
    }




}