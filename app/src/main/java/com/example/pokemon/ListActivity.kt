package com.example.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.Models.Pokemon
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val pokemon1 = Pokemon()
        pokemon1.nombre = "Bulbasour"

        val pokemon2 = Pokemon()
        pokemon2.nombre = "Pikachu"

        var listaPokemons = mutableListOf<Pokemon>()
        listaPokemons.add(pokemon1)
        listaPokemons.add(pokemon2)
        setRecyclerAdapter(listaPokemons)
    }

    fun setRecyclerAdapter(listaPokemons : MutableList<Pokemon>){
        val recyclerView: RecyclerView = pokemons_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PokemonsRecyclerAdapter(this, listaPokemons)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}
