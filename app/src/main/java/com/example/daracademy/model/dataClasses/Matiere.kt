package com.example.daracademy.model.dataClasses

data class Matiere(
    val id       : String = "",
    val img      : Int    = -1,
    val imgUrl   : String = "",
    val name     : String = "none",
    val phase    : String = "",
    val annee    : String = "",
    val teachers : List<String> = emptyList()
)