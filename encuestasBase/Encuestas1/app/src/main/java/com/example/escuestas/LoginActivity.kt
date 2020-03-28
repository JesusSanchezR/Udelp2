package com.example.escuestas

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.escuestas.Data.ListUsers
import com.example.escuestas.Data.UsuarioDb

import com.example.escuestas.Data.UsuarioDb.Companion.usuario_registrado
import com.example.escuestas.Entity.EntityUser
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro.*

class LoginActivity : AppCompatActivity(){
    val usuarioBase = UsuarioDb(this)
    val usuarioRe = ListUsers()
    var busqueda =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnEntrar.setOnClickListener {
            val user = edtUsuario.text.toString()
            val password = edtContraseña.text.toString()

            val vuelta1 = EntityUser()
            vuelta1.nombre = "jesus"
            vuelta1.password = "123456"
            usuarioRe.agregarU(vuelta1)

            val vuelta2 = EntityUser()
            vuelta2.nombre = user
            vuelta2.password = password
           //pasado busqueda = usuarioR.encontrarU(vuelta2)
            busqueda = usuarioBase.autenticacion(vuelta2)

            if (busqueda >= 0){
                Snackbar.make(it,"Usuario encontrado con metodo $user $password", Snackbar.LENGTH_SHORT).show()
                Toast.makeText(this@LoginActivity,"id $busqueda", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity,MainActivity::class.java)
                intent.putExtra("usuario", "$user");
                usuario_registrado  = usuarioBase.usuariGetOne(busqueda)
                usuarioBase.usuarioGetAll()
                startActivity(intent)
            }else{
                Snackbar.make(it,"Usuario o contraseña incorrectos", Snackbar.LENGTH_SHORT).show()
            }


        }
    }


/*-------------------------------------------------------------*/
            /*if(user.equals("Jesus") && password.equals("123456")){
                val intent = Intent(this@LoginActivity,MainActivity::class.java)
                startActivity(intent)

            }else
            {
                Toast.makeText(this@LoginActivity,"Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show()


            }*/
            /*if(user.equals(edtCorreoR) && password.equals(edtContraseñaR)){
                val intent = Intent(this@LoginActivity,MainActivity::class.java)
                startActivity(intent)

            }else
            {
                Toast.makeText(this@LoginActivity,"Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show()


            }*/


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.login_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itmRegistroUsuario->{
                val intent = Intent(this@LoginActivity, RegistryActivity::class.java)
                startActivity(intent)
            }

        }


        return true
    }


}













