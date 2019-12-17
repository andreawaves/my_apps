package com.example.pokemon.Models

import java.io.Serializable

data class PokemonDetalle (
    var base_experience:Int = 0,
    var height:Int = 0,
    var weight:Int = 0
) : Serializable