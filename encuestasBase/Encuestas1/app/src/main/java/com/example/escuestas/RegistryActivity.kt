package com.example.escuestas

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.escuestas.Data.ListUsers
import com.example.escuestas.Data.UsuarioDb
import com.example.escuestas.Email.Companion.validarCorreo
import com.example.escuestas.Entity.EntityUser
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_encuesta.*
import kotlinx.android.synthetic.main.activity_registro.*

class RegistryActivity : AppCompatActivity() {
    val misusuario = ListUsers()
    val usersDB = UsuarioDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)


        btnAceptar.setOnClickListener {

            val miusuario = EntityUser()

            if(edtNombreRegistro.text.toString().trim().isNotEmpty()){
                miusuario.nombre = edtNombreRegistro.text.toString()
                var  nombreR = edtNombreRegistro.text.toString()
                if(edtApellidoPaternoR.text.toString().trim().isNotEmpty()){
                    miusuario.ApellidoPaterno = edtApellidoPaternoR.text.toString()
                    var apellidoP = edtApellidoPaternoR.text.toString()

                    if(edtApellidoMaternoR.text.toString().trim().isNotEmpty()){
                        miusuario.ApellidoMaterno = edtApellidoMaternoR.text.toString()
                        var apellidoM = edtApellidoMaternoR.text.toString()



                        val selectedServicio = rdgGenero.checkedRadioButtonId
                        var generoR =0

                        if (selectedServicio != -1) {
                            when (selectedServicio) {
                                rdbMasculinoR.id -> {
                                    miusuario.genero = 1
                                    generoR = 1
                                }
                                rdbFemeninoR.id -> {
                                    miusuario.genero = 0
                                    generoR = 0
                                }
                            }
                            val delegacionposition = spnDelegacionR.selectedItemPosition
                            if (delegacionposition > 0) {
                                miusuario.delegacion = delegacionposition
                                var delegacionR = delegacionposition


                                if(edtDireccionR.text.toString().trim().isNotEmpty()) {
                                    miusuario.direccion = edtDireccionR.text.toString()
                                    var direccionR = edtDireccionR.text.toString()

                                    val edoCivil = spnEstadoCivil.selectedItemPosition
                                    if (edoCivil > 0) {
                                        miusuario.estadoCivil = edoCivil


                                        if(edtCorreoR.text.toString().trim().isNotEmpty()){
                                            miusuario.correo = edtCorreoR.text.toString()
                                            var correoR = edtCorreoR.text.toString()

                                            if(edtContraseñaR.text.toString().trim().isNotEmpty()) {
                                                miusuario.password = edtContraseñaR.text.toString()
                                                var passwdR = edtContraseñaR.text.toString()
                                                var values = EntityUser(-1,nombreR,apellidoP,apellidoM,generoR,delegacionR,direccionR,edoCivil,correoR,passwdR)
                                                var idR = usersDB.UsuarioAdd(values)
                                                usersDB.usuarioGetAll()



                                                Toast.makeText(this@RegistryActivity,"Guardado",Toast.LENGTH_SHORT).show()
                                                Log.d("tamañoLISTA",misusuario.agregarU(miusuario).toString())
                                            }else{
                                                Snackbar.make(it, "Ingresa  contraseña", Snackbar.LENGTH_SHORT).show()
                                            }
                                        }else{
                                            Snackbar.make(it, "Ingresa correo electrónico", Snackbar.LENGTH_SHORT).show()
                                        }
                                    }else{
                                        Snackbar.make(it, "Ingresa estado civil", Snackbar.LENGTH_SHORT).show()
                                    }
                                }else{
                                    Snackbar.make(it, "Ingresa una dirección", Snackbar.LENGTH_SHORT).show()
                                }
                            }else{
                                Snackbar.make(it, "Ingresa el genero", Snackbar.LENGTH_SHORT).show()
                            }
                        }else{
                            Snackbar.make(it, "Ingresa la delegación", Snackbar.LENGTH_SHORT).show()
                        }
                    }else{
                        Snackbar.make(it, "Ingresa el apellido materno", Snackbar.LENGTH_SHORT).show()
                    }
                }else{
                    Snackbar.make(it, "Ingresa el apellido paterno", Snackbar.LENGTH_SHORT).show()
                }
            }else{
                Snackbar.make(it, "Ingresa  el nombre", Snackbar.LENGTH_SHORT).show()
            }
        }

    }



}
