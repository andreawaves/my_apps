package com.example.pokemon.Models
import java.io.Serializable

data class Pokemon (
    var id:Int = 0,
    var name:String = "",
    var url:String = "",
    var urlFoto:String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" //1.png
): Serializable