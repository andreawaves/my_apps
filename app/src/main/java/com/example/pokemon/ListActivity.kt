package com.example.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.Models.Pokemon
import com.example.pokemon.Models.PokemonRespuesta
import kotlinx.android.synthetic.main.activity_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListActivity : AppCompatActivity() {

    private lateinit var retrofit: Retrofit
    private lateinit var service : ApiService
    val TAG = "POKEAPI"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        supportActionBar!!.title = "Pokemon List"

        retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        obtenerPokemones()
    }

    fun obtenerPokemones() {
        service = retrofit.create(ApiService::class.java)
        service.obtenerListaPokemon().enqueue(object : Callback<PokemonRespuesta> {
            override fun onResponse(
                call: Call<PokemonRespuesta>?,
                response: Response<PokemonRespuesta>?
            ) {
                if (response!!.isSuccessful()){
                    val pokemonRespuesta = response.body()
                    var listaPokemons = pokemonRespuesta.results

                    for (i in 0 until listaPokemons.size){
                        Log.e(TAG,"Pokemon: " + listaPokemons[i].name)
                        listaPokemons[i].id = i+1
                        listaPokemons[i].urlFoto = listaPokemons[i].urlFoto + (i+1) + ".png"
                        Log.e(TAG,"Pokemon: " + listaPokemons[i].urlFoto)
                    }
                    setRecyclerAdapter(listaPokemons)
                } else {
                    Log.e(TAG,"onResponse: " + response.errorBody())
                }
            }
            override fun onFailure(call: Call<PokemonRespuesta>?, t: Throwable?) {
                Log.e(TAG,"onFailure: " + t!!.message)
            }
        })
    }



    fun setRecyclerAdapter(listaPokemons : ArrayList<Pokemon>){
        val recyclerView: RecyclerView = pokemons_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PokemonsRecyclerAdapter(this, listaPokemons)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}
