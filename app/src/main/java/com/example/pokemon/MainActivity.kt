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

        btn_login.setOnClickListener { verificar() }
        btn_registro.setOnClickListener { irRegistro() }

        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .name("entrenador.realm").build()

        realm = Realm.getInstance (config)
    }

    private fun verificar() {
        val usuario = et_usuario.text.toString()
        val password = et_password.text.toString()
        if (usuario.trim() != "" && password.trim() != "") {
            login(usuario,password)
        } else {
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_LONG).show()
        }
    }

    private fun login(usuario:String, password:String){


        val resultados = realm.where(Entrenador::class.java).equalTo("usuario",usuario).findAll()

        if (resultados.isEmpty()) {
            Toast.makeText(this, "Login incorrecto", Toast.LENGTH_LONG).show()
        } else {
            val entrenador = resultados.first()!!
            if (entrenador.usuario!="" && entrenador.password.equals(password)) {
                Toast.makeText(this, "EXCELENTE", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Login incorrecto", Toast.LENGTH_LONG).show()
            }
        }



    }

    private fun irRegistro() {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }
}
