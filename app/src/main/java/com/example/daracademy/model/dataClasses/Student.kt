package com.example.daracademy.model.dataClasses

data class Student(
    val id    : String = "",
    val name  : String = "",
    val email : String = "",
    val password  : String = "",
    val phone : Phone   = Phone(),
    val photo : String = "",
    val paid  : Boolean = true
)

