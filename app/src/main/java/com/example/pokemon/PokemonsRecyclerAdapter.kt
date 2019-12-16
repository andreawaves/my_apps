package com.example.pokemon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.Models.Pokemon
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonsRecyclerAdapter: RecyclerView.Adapter<PokemonsRecyclerAdapter.ViewHolder> {

    private lateinit var mContext: Context
    private lateinit var listaPokemons: MutableList<Pokemon>

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
        holder.item_nombre.text = pokemon.nombre
    }

    class ViewHolder
    constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item_pokemon = itemView.item_pokemon
        val item_nombre = itemView.item_nombre
        val item_foto = itemView.item_foto
    }
}
