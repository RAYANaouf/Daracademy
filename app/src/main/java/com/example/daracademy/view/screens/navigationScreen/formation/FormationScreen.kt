package com.example.daracademy.view.screens.navigationScreen.formation

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.dataClasses.Formation
import com.example.daracademy.model.dataClasses.Teacher
import com.example.daracademy.model.sealedClasses.Errors.Errors
import com.example.daracademy.model.sealedClasses.screens.Screens
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.model.variables.josefinSansFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.color2
import com.example.daracademy.ui.theme.customBlack3
import com.example.daracademy.ui.theme.customWhite0
import com.example.daracademy.view.screens.navigationScreen.formation.component.descriptionItem.DescriptionItem
import com.example.daracademy.view.screens.navigationScreen.formation.component.header.HeaderItem
import com.example.daracademy.view.screens.navigationScreen.formation.component.infoSection.InfoSection
import com.example.daracademy.view.screens.navigationScreen.formation.component.prerequisiteSection.PrerequisiteSection
import com.example.daracademy.view.screens.navigationScreen.formation.component.schedulerCards.SchedulerCards
import com.example.daracademy.view.screens.navigationScreen.formation.component.targetedSection.TargetedSection
import com.example.daracademy.view.screens.navigationScreen.formation.component.teacherCard.TeacherCard
import com.example.daracademy.view.screens.navigationScreen.formation.component.timeLine.TimeLine
import com.example.daracademy.viewModel.DaracademyViewModel
import com.mxalbert.sharedelements.SharedElement

@Composable
fun FormationScreen(
    formation     : Formation,
    viewModel     : DaracademyViewModel,
    modifier      : Modifier = Modifier
) {



    val window = LocalView.current.context as Activity

    LaunchedEffect(key1 = window){
        window.window.apply {
            statusBarColor     = Color.Transparent.toArgb()
            navigationBarColor = Color.Transparent.toArgb()
        }
    }



    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxHeight()
    ) {


        SharedElement(key = "name ${formation.name}" , screenKey = "formation screen") {
            HeaderItem(
                images        = formation.imgs ,
                onNavBack     = {
                    viewModel.screenRepo.navigate_to_screen(screen = Screens.HomeScreen().root )
                },
                modifier      = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.8f, true)
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 8.dp, bottom = 8.dp, end = 16.dp)
        ) {

            Text(
                text     = formation.name,
                style    = NormalTextStyles.TextStyleSZ5.copy(color = customBlack3 , fontFamily = josefinSansFamily),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
            )

        }

        Spacer(modifier = Modifier.height(36.dp))

        InfoSection(
            modifier = Modifier
                .padding(start = 20.dp , end = 16.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))


        DescriptionItem(
            description = formation.desc,
            modifier = Modifier
                .padding(start = 20.dp , end = 16.dp)
        )

        Spacer(modifier = Modifier.height(36.dp))

        var teacher : Teacher? by remember{
            mutableStateOf(null)
        }

        val context = LocalContext.current

        LaunchedEffect(key1 = true ){
            viewModel.getTeacherById(
                teacherId = formation.teacher,
                onSuccessCallBack = {
                    teacher = it
                },
                onFailureCallBack = {
                    if (it is Errors){
                        Toast.makeText(context , "error ${formation.teacher}" , Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(context , "error ${it.message}" , Toast.LENGTH_LONG).show()

                    }
                }
            )
        }



        TeacherCard(
            teacher = teacher ,
            modifier = Modifier
                .padding(start = 20.dp , end = 16.dp)
        )

        Spacer(modifier = Modifier.height(36.dp))

        SchedulerCards(
            lessons = formation.lessons,
            modifier = Modifier
                .padding(start = 20.dp , end = 16.dp)
        )

        Spacer(modifier = Modifier.height(36.dp))

        TimeLine(
            com.example.daracademy.model.dataClasses.TimeLine(),
            modifier = Modifier
//                .padding(start = 20.dp , end = 16.dp)
        )

        Spacer(modifier = Modifier.height(36.dp))

        TargetedSection(
            emptyList(),
            modifier = Modifier
                .padding(start = 20.dp , end = 16.dp)
        )

        Spacer(modifier = Modifier.height(36.dp))

        PrerequisiteSection(
            emptyList(),
            modifier = Modifier
                .padding(start = 20.dp , end = 16.dp)

        )

//            CompaniesStage(
//                companies = listOf(
//                    Company(name = "sonatrach"        , img = "https://th.bing.com/th/id/R.7e8d95668e8b4374f42abcad9397a2b9?rik=So5gGhq%2bqGYiYQ&riu=http%3a%2f%2fwww.ranklogos.com%2fwp-content%2fuploads%2f2015%2f06%2fSonatrach-Logo-500x500.png&ehk=aU2ZoLRUvR7rPIIR%2fhHKRjv4iomfRvlbEu%2bz71YhpFU%3d&risl=&pid=ImgRaw&r=0&sres=1&sresct=1") ,
//                    Company(name = "sonelgaz"         , img = "https://th.bing.com/th/id/OIP.xi9NzTPAf_hlM0LSwZ7cPgHaFV?rs=1&pid=ImgDetMain"),
//                    Company(name = "algerie telecom"  , img = "data:image/jpeg;base64,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")
//                ),
//                modifier = Modifier
//                    .padding(start = 20.dp , end = 16.dp)
//            )

        Spacer(modifier = Modifier.height(60.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 26.dp, end = 26.dp)
        ) {

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .weight(3f)
            ) {
                Text(
                    text     = buildAnnotatedString {

                        withStyle(
                            SpanStyle(fontSize = NormalTextStyles.TextStyleSZ7.fontSize)
                        ){
                            append("Price per Month\n")
                        }

                        append("${formation.prix} DA ")
                    },
                    style    = NormalTextStyles.TextStyleSZ9.copy(color = color1 , fontFamily = firaSansFamily),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                )
            }
            
            Spacer(modifier = Modifier.width(10.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(2f)
                    .padding(top = 4.dp, bottom = 4.dp)
                    .clip(CircleShape)
                    .background(color1)
                    .height(55.dp)
            ) {
                Text(
                    text = "Register",
                    style = NormalTextStyles.TextStyleSZ6.copy(fontFamily = josefinSansFamily , color = customWhite0),
                    modifier = Modifier
                        .offset(x = 0.dp, y = (-4).dp)
                )
            }
        }




        Spacer(modifier = Modifier.height(45.dp))

    }


}


@Preview
@Composable
fun FormationScreen_preview() {

    val context = LocalContext.current
    val navHostController = rememberNavController()


    FormationScreen(
        viewModel     = viewModel(
            factory   = object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(DaracademyViewModel::class.java)){
                        return DaracademyViewModel(context , navHostController) as T
                    }
                    else{
                        throw IllegalArgumentException("can tcreate daracademy viewmodel (formationScreen)")
                    }
                }
            }
        ),
        formation     = Formation()
    )
}