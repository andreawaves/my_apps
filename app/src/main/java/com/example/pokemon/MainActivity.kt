package com.example.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pokemon.Models.Entrenador
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var realm : Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        btn_login.setOnClickListener { verificar() }
        btn_registro.setOnClickListener { irRegistro() }

        Realm.init(this)

        val config = RealmConfiguration.Builder().directory(getExternalFilesDir(null)!!)
            .name("entrenador.realm").build()

        realm = Realm.getInstance(config)
    }

    private fun verificar() {
        val usuario = et_usuario.text.toString()
        val password = et_password.text.toString()
        if (usuario.trim() == ""){
            Toast.makeText(this, "Ingrese su usuario", Toast.LENGTH_LONG).show()
        } else if (password.trim() == "") {
            Toast.makeText(this, "Ingrese su password", Toast.LENGTH_LONG).show()
        } else {
            login(usuario,password)
        }
    }

    private fun login(usuario:String, password:String){
        val resultados = realm.where(Entrenador::class.java).equalTo("usuario",usuario).findAll()

        if (resultados.isEmpty()) {
            Toast.makeText(this, "Usuario inexistente", Toast.LENGTH_LONG).show()
        } else {
            val entrenador = resultados.first()!!
            if (entrenador.usuario != "" && entrenador.password.equals(password)) {
                irList()
            } else {
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun irRegistro() {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }

    private fun irList() {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }
}
