package com.example.my_app001

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.my_app001.Models.Persona
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_registrarse.*

class RegistrarseActivity : AppCompatActivity() {

    lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        Realm.init(this) //REALM

        val config = RealmConfiguration.Builder()
            .name("persona.realm").build()
        realm = Realm.getInstance(config)

        btnGuardar.setOnClickListener{guardar()}
    }

    private fun guardar(){
        val usuario = etUsuarioR.text.toString()
        val contrasena = etContrasenaR.text.toString()

        realm.beginTransaction()
        val persona = realm.createObject(Persona::class.java)
        persona.usuario = usuario
        persona.contrasena = contrasena
        realm.commitTransaction()

    }
}
