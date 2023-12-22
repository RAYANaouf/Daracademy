package com.example.daracademy.model.dataClasses

import com.google.firebase.Timestamp

data class Message(
    val id         : String     = "",
    val msg        : String     = "",
    val person_msg : Boolean    = true,
    val timestamp  : Timestamp? = null // Change this to Timestamp

)
