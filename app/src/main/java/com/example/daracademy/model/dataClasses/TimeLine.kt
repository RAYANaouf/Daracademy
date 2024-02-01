package com.example.daracademy.model.dataClasses

data class TimeLine(
    val id           : String = "",
    val formation_id : String = "",
    val points       : List<Point> = emptyList()
)


data class Point(
    val img      : String   = "" ,
    val desc     : String   = "" ,
    val duration : Duration = Duration()
)

data class Duration(
    val num  : Int = 1,
    val type : String = "Day"
)


