package com.example.pokemon.Models

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Entrenador() : RealmObject() {
    var usuario:String = ""
    var password:String = ""
}