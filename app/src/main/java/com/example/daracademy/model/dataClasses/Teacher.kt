package com.example.daracademy.model.dataClasses

data class Teacher(
    val id        : String = "",
    val name      : String = "",
    val domain    : String = "",
    val email     : String = "",
    val photo     : String = "",
    val phone     : Phone = Phone(),
    val formation : List<String> = listOf(),
    val supports  : List<String> = listOf()
)


data class Phone(
    val type   : String = "07",
    val number : String = ""
)



