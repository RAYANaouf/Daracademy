package com.example.daracademy.model.dataClasses

data class Course(
    val courseId  : String = "" ,
    val teacherId : String = "" ,
    val matiereId : String = "" ,
    val group     : Int    = 1  ,
    val lessons   : List<Lesson> = emptyList()
)

