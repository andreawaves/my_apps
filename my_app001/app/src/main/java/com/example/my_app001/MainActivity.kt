package com.example.my_app001

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.my_app001.Models.Persona
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnInicio.setOnClickListener{iniciar()}
        btnRegistro.setOnClickListener{irRegistro()}

        Realm.init(this) //REALM

        val config = RealmConfiguration.Builder()
            .name("persona.realm").build()
        realm = Realm.getInstance(config)

    }

    private fun iniciar(){
        val usuario = etUsuario.text.toString()
        val contrasena = etContrasena.text.toString()


        val persona = realm.where(Persona::class.java).equalTo("usuario",usuario).findFirst()!!

            if (contrasena == persona!!.contrasena){
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
        val intent = Intent(this, RegistrarseActivity::class.java)
        startActivity(intent)
    }

}
