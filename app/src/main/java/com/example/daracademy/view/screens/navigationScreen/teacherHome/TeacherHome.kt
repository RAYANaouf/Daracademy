package com.example.daracademy.view.screens.navigationScreen.teacherHome

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.daracademy.ui.theme.backgroundLight
import com.example.daracademy.view.screens.navigationScreen.teacherHome.components.coursesCard.CoursesCard
import com.example.daracademy.view.screens.navigationScreen.teacherHome.components.formationsCard.FormationsCard
import com.example.daracademy.view.screens.navigationScreen.teacherHome.components.headerSection.HeaderSection

@Composable
fun TeacherHome(
    modifier: Modifier = Modifier
) {


    val window  = LocalView.current.context as Activity
    val context = LocalContext.current

    LaunchedEffect(key1 = window){
        window.window.apply {
            navigationBarColor = backgroundLight.toArgb()
        }

    }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {

        HeaderSection()

        Spacer(modifier = Modifier.height(45.dp))

        CoursesCard(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(45.dp))

        FormationsCard(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth()
        )

//        Spacer(modifier = Modifier.height(45.dp))
//
//        CoursesCard(
//            modifier = Modifier
//                .padding(start = 20.dp, end = 20.dp)
//                .fillMaxWidth()
//        )


        Spacer(modifier = Modifier.height(120.dp))

    }
}

@Preview
@Composable
fun TeacherHome_preview() {
    TeacherHome()
}