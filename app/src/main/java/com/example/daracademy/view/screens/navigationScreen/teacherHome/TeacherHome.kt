package com.example.daracademy.view.screens.navigationScreen.teacherHome

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.daracademy.view.screens.navigationScreen.teacherHome.headerSection.HeaderSection

@Composable
fun TeacherHome(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {

        HeaderSection()

    }
}

@Preview
@Composable
fun TeacherHome_preview() {
    TeacherHome()
}