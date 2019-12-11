package com.example.my_app001

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    var listaNumeros:MutableList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        for (i in 1..20){
            listaNumeros.add(i)
        }
        setRecyclerAdapter(listaNumeros)
    }

    fun setRecyclerAdapter(listaNumeros:MutableList<Int>){
        val recyclerView: RecyclerView = personas_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PersonaRecyclerAdapter(this, listaNumeros)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}
