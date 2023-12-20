package com.example.daracademy.model.dataClasses

data class Post(
    val postId     : String = "",
    val title  : String = "",
    val desc   : String = "",
    val ltr    : Boolean = false,
    val imgs : List<String> = emptyList()
)

