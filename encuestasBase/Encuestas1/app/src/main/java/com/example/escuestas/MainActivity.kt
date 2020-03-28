package com.example.escuestas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.escuestas.Data.UsuarioDb
import com.example.escuestas.Data.UsuarioDb.Companion.usuario_registrado
import com.example.escuestas.Data.UsuarioDb.Companion.usuario_registrado
import kotlinx.android.synthetic.main.activity_login.*

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var usuario=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usuario = intent.extras?.getString("usuario").toString()

        Log.d("udelp","$usuario")

        txvUsuarioMain.setText(UsuarioDb.usuario_registrado.nombre.toString())

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itmRealizar -> {
                val intent = Intent(this@MainActivity, QuizActivity::class.java)
                startActivity(intent)
            }

            R.id.itmMade -> {
                val intent = Intent(this@MainActivity, SurveysActivity::class.java)
                startActivity(intent)

            }
            R.id.itmEliminar -> {
                val intent = Intent(this@MainActivity, DeleteActivity::class.java)
                startActivity(intent)

            }

            R.id.itmEditar -> {
                val intent = Intent(this@MainActivity, SelectItemEditActivity::class.java)
                startActivity(intent)

            }
        }
        return true

        }

    }
