package com.example.pokemon

import com.example.pokemon.Models.Pokemon
import com.example.pokemon.Models.PokemonDetalle
import com.example.pokemon.Models.PokemonRespuesta
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon")
    fun obtenerListaPokemon(): Call<PokemonRespuesta>

    @GET("pokemon/{id}")
    fun obtenerPokemonDetalle(@Path("id") id:String): Call<PokemonDetalle>

}