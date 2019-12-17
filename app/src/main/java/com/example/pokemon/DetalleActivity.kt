package com.example.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.pokemon.Models.Pokemon
import com.example.pokemon.Models.PokemonDetalle
import kotlinx.android.synthetic.main.activity_detalle.*

class DetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val pokemon = intent.getSerializableExtra("pokemon") as Pokemon
        val pokemonDetalle = intent.getSerializableExtra("pokemonDetalle") as PokemonDetalle

        tv_nombre.text = pokemon.name.capitalize()
        tv_experiencia.text = pokemonDetalle.base_experience.toString()
        tv_altura.text = pokemonDetalle.height.toString()
        tv_peso.text = pokemonDetalle.weight.toString()

        Glide.with(this)
            .load(pokemon.urlFoto)
            .override(300,300)
            .into(iv_foto)


        supportActionBar!!.title = "Información"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }


}
