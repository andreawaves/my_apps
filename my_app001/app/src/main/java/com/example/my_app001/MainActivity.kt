package com.example.my_app001

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnInicio.setOnClickListener{iniciar()}
        btnRegistro.setOnClickListener{irRegistro()}
    }

    private fun iniciar(){
        val usuario = etUsuario.text.toString()
        val contrasena = etContrasena.text.toString()

        if (usuario == "Admin" && contrasena == "admin"){
            continuar()
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
        }
    }

    private fun continuar() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    private fun irRegistro(){
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }

}
