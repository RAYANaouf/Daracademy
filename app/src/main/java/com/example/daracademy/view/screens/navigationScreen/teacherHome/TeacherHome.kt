package com.example.daracademy.view.screens.navigationScreen.teacherHome

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.daracademy.R
import com.example.daracademy.model.sealedClasses.screens.Screens
import com.example.daracademy.ui.theme.backgroundLight
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.customWhite0
import com.example.daracademy.view.screens.navigationScreen.teacherHome.components.ActsRow.acts
import com.example.daracademy.view.screens.navigationScreen.teacherHome.components.coursesCard.CoursesCard
import com.example.daracademy.view.screens.navigationScreen.teacherHome.components.formationsCard.FormationsCard
import com.example.daracademy.view.screens.navigationScreen.teacherHome.components.headerSection.HeaderSection
import com.example.daracademy.view.screens.navigationScreen.teacherHome.components.headerSection.actItem
import com.example.daracademy.viewModel.DaracademyViewModel

@Composable
fun TeacherHome(
    viewModel: DaracademyViewModel,
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

        HeaderSection(
            onNavigate = { index ->
                when(index){
                    1->{
                        viewModel.screenRepo.navigate_to_screen(Screens.HomeScreen().root)
                    }
                    2->{
                        viewModel.screenRepo.navigate_to_screen(Screens.TeacherCoursesScreen().root  )
                    }
                    3->{
                        viewModel.screenRepo.navigate_to_screen(Screens.FormationsScreen().root)
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(45.dp))

        acts(
            modifier = Modifier.padding(start = 16.dp , end = 16.dp)
        )

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
    val context = LocalContext.current
    val navHostController = rememberNavController()
    TeacherHome(
        viewModel = viewModel(
            factory = object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(DaracademyViewModel::class.java))
                        return DaracademyViewModel(context , navHostController) as T
                    else
                        throw IllegalArgumentException("cant create viewModel (teacher home)")
                }
            }
        )
    )
}