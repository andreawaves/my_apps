package com.example.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pokemon.Models.Entrenador
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {

    lateinit var realm : Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        btn_guardar.setOnClickListener { verificar() }

        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .name("entrenador.realm").build()

        realm = Realm.getInstance (config)

        }

    private fun verificar() {
        val usuario = et_iusuario.text.toString().trim()
        val password1 = et_ipassword1.text.toString().trim()
        val password2 = et_ipassword2.text.toString().trim()

        //Verificar si están vacíos
        if (usuario == "" || password1 == "" || password2 == ""){
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_LONG).show()
        } else {
            //Verificar si las contraseñas coinciden
            if (password1.equals(password2)) {
                guardar(usuario,password1)
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
            }
        }
    }

    //TODO Que no exista el usuario

    private fun guardar(usuario:String, password:String){
        realm.beginTransaction()
        val entrenador = realm.createObject(Entrenador::class.java)
        entrenador.usuario = usuario
        entrenador.password = password
        realm.commitTransaction()

        volver()
    }

    private fun volver() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
