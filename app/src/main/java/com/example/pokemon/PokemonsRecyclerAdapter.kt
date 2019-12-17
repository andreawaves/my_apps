package com.example.pokemon

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.Models.Pokemon
import com.example.pokemon.Models.PokemonDetalle
import com.example.pokemon.Models.PokemonRespuesta
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_pokemon.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonsRecyclerAdapter: RecyclerView.Adapter<PokemonsRecyclerAdapter.ViewHolder> {

    private lateinit var mContext: Context
    private lateinit var listaPokemons: MutableList<Pokemon>
    private lateinit var retrofit: Retrofit
    private lateinit var service : ApiService
    val TAG = "POKEAPI"

    constructor(mContext: Context, listaPokemons: MutableList<Pokemon>) : super() {
        this.mContext = mContext
        this.listaPokemons = listaPokemons
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(mContext).inflate(R.layout.item_pokemon, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaPokemons.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon: Pokemon = listaPokemons.get(position)
        holder.item_nombre.text = pokemon.name

        Glide.with(mContext)
            .load(pokemon.urlFoto)
            .into(holder.item_foto)

        holder.item_pokemon.setOnClickListener{obtenerDetalle(pokemon)}
    }

    fun obtenerDetalle(pokemon: Pokemon) {
        retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ApiService::class.java)
        service.obtenerPokemonDetalle(pokemon.id.toString()).enqueue(object : Callback<PokemonDetalle> {
            override fun onResponse(
                call: Call<PokemonDetalle>?,
                response: Response<PokemonDetalle>?
            ) {
                if (response!!.isSuccessful) {
                    val pokemonDetalle = response.body()
                    Log.e(TAG,"onResponse: " + pokemonDetalle.height)
                    irDetalle(pokemon,pokemonDetalle)
                } else {
                    Log.e(TAG,"onResponse: " + response.errorBody())
                }
            }

            override fun onFailure(call: Call<PokemonDetalle>?, t: Throwable?) {
                Log.e(TAG,"onFailure: " + t!!.message)
            }
        })
    }

    fun irDetalle(pokemon: Pokemon, pokemonDetalle: PokemonDetalle) {
        val intent = Intent(mContext, DetalleActivity::class.java)
        intent.putExtra("pokemon",pokemon)
        intent.putExtra("pokemonDetalle",pokemonDetalle)
        mContext.startActivity(intent)
    }

    class ViewHolder
    constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item_pokemon = itemView.item_pokemon
        val item_nombre = itemView.item_nombre
        val item_foto = itemView.item_foto
    }
}
