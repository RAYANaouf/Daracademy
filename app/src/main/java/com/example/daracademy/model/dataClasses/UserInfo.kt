package com.example.daracademy.model.dataClasses

import com.example.daracademy.model.sealedClasses.userType.UserType


data class UserInfo(
    val userType : UserType? = null,
    val id       : String = "",
    val name     : String = "",
    val email    : String = "",
    val image    : String = ""
)