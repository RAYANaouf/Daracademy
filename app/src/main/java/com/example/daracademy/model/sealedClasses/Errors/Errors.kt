package com.example.daracademy.model.sealedClasses.Errors

sealed class Errors(val msg : String) : Exception() {


    class TeachersDidntExistError : Errors(msg = "the teachers with that id  didnt exist ")
    class userOrPasswordIncorrect : Errors(msg = "user name or password incorrect")

}
