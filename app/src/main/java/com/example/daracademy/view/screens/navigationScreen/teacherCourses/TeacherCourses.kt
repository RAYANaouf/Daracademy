package com.example.daracademy.view.screens.navigationScreen.teacherCourses

import android.graphics.Color.parseColor
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalAccessibilityManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.dataClasses.Course
import com.example.daracademy.model.dataClasses.Matiere
import com.example.daracademy.model.dataClasses.MatiereWithCourses
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.model.variables.nunitoFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.customWhite0
import com.example.daracademy.viewModel.DaracademyViewModel

@Composable
fun TeacherCourses(
    viewModel: DaracademyViewModel ,
    modifier : Modifier = Modifier
) {

    val course_matiere = remember {
        mutableStateListOf<MatiereWithCourses>()
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true ){
        viewModel.getCourses_Matieres(
            onSuccessCallBack = {
                Toast.makeText(context , "$it" , Toast.LENGTH_LONG).show()
                course_matiere.addAll(it)
            },
            onFailureCallBack = {

            }
        )
    }


    val course_matiere_grouped_by_phase = course_matiere.groupBy {
        it.matiere.phase
    }




    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {

        course_matiere_grouped_by_phase.forEach{

            herderItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(parseColor("#662388C1")))
                    .padding(16.dp)
                ,
                txt = it.key
            )

            listOfCourses( title = it.key , matiereWithCourses = it.value )

        }

    }

}


@Composable
fun herderItem(
    txt     : String,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
    ) {
        Text(
            text = txt,
            style = NormalTextStyles.TextStyleSZ2.copy(color = customWhite0 , fontFamily = nunitoFamily)
        )
    }
}

@Composable
fun listOfCourses(
    title    : String ,
    matiereWithCourses  : List<MatiereWithCourses>,
    modifier : Modifier = Modifier
) {


    matiereWithCourses.groupBy {
        it.matiere.annee
    }.forEach {
        Column(
            modifier = modifier
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(color1))

                Text(
                    text  = "${it.key}",
                    style = NormalTextStyles.TextStyleSZ5
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            it.value.forEachIndexed { index, item ->
                MatiereItem(item.courses)
            }

        }

    }


}


@Composable
fun MatiereItem(
    course: Course,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
    ) {
        Column {

            Image(
                painter = rememberAsyncImagePainter(model = course.matiereId) ,
                contentDescription = null,
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = course.teacherId )

        }
    }

}

@Preview
@Composable
fun TeacherCourses_preview() {

    val context = LocalContext.current
    val navHostController = rememberNavController()



    TeacherCourses(
        viewModel = viewModel(
            factory = object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(DaracademyViewModel::class.java))
                        return DaracademyViewModel(context , navHostController) as T
                    else
                        throw IllegalArgumentException("cant create viewModel (TeacherCoursesScreen)")
                }
            }
        )
    )
}