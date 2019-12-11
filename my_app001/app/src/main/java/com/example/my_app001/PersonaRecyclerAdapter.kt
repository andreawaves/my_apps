package com.example.my_app001

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.item_persona.view.*
import androidx.recyclerview.widget.RecyclerView

class PersonaRecyclerAdapter : RecyclerView.Adapter<PersonaRecyclerAdapter.ViewHolder> {

    private lateinit var mContext: Context
    private lateinit var listaNumeros: MutableList<Int>

    constructor(mContext: Context, listaNumeros: MutableList<Int>) : super() {
        this.mContext = mContext
        this.listaNumeros = listaNumeros
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(mContext).inflate(R.layout.item_persona, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaNumeros.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val numero: Int = listaNumeros.get(position)
        holder.item_numero.text = numero.toString()
    }

    class ViewHolder
    constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item_holder = itemView.item_holder
        val item_numero = itemView.item_numero
    }
}