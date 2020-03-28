package com.example.escuestas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.escuestas.Data.EncuestasDb
import com.example.escuestas.Data.EncuestasDb.Companion.listI

import com.example.escuestas.Data.ListEncuestas
import kotlinx.android.synthetic.main.activity_misencuestas.*

class SurveysActivity : AppCompatActivity() {
    val encuestasDb = EncuestasDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_misencuestas)

        if(encuestasDb.encuestasGetAllHelp().size >0){
            val miAdaptador = ArrayAdapter<String> (this@SurveysActivity, android.R.layout.simple_list_item_1,encuestasDb.encuestasGetAllHelp())
            encuestasDb.encuestasGetAll()
            ltvEncuestas.adapter=miAdaptador

            ltvEncuestas.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->

                var index= listI[position]
                Toast.makeText(this@SurveysActivity, "Se selecciono la encuesta $index", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@SurveysActivity, DetailActivity::class.java)
                intent.putExtra("ID", index)
                startActivity(intent)
            }
        }






    }
}