package com.example.daracademy.model.dataClasses

data class MatiereWithCourses(
    val matiere     : Matiere = Matiere(),
    var courses     : Course  = Course()
)