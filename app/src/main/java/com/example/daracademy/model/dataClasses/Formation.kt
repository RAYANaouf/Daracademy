package com.example.daracademy.model.dataClasses

data class Formation (
    val name      : String = "",
    val desc      : String = "",
    val teacher   : String = "",
    val companies : List<Company> = emptyList(),
    val imgs      : List<String> = emptyList(),
    val lessons   : List<Lesson> = emptyList(),
)