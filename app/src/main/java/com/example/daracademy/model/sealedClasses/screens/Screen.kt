package com.example.daracademy.model.sealedClasses.screens

import com.example.daracademy.model.objects.screen.screens

sealed class Screens(val root : String){



    class HomeScreen         : Screens(root = screens.homeScreen)

    class FormationScreen    : Screens(root = screens.formationScreen)


    class AnneesScreen       : Screens(root = screens.anneesScreen)

    class MateriauxScreen    : Screens(root = screens.materiaux_Screen)

    class Chat_Screen        : Screens(root = screens.chat_Screen)

    class ChatBox_Screen     : Screens(root = screens.chatBox_Screen)

    class CoursesScreen      : Screens(root = screens.coursesScreen)

}
