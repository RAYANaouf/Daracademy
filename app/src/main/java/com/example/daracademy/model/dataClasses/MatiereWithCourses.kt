package com.example.daracademy.model.dataClasses

data class MatiereWithCourses(
    val matiere     : Matiere?      = null,
    var courses     : List<Course>? = null
)