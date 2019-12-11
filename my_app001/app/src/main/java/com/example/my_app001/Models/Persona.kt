package com.example.my_app001.Models

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Persona() : RealmObject() {
    var usuario:String = ""
    var contrasena:String = ""
}
