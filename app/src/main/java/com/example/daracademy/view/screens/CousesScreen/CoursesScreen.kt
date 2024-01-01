package com.example.daracademy.view.screens.CousesScreen

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.dataClasses.Course
import com.example.daracademy.model.dataClasses.Lesson
import com.example.daracademy.model.dataClasses.Teacher
import com.example.daracademy.model.sealedClasses.screens.Screens
import com.example.daracademy.model.variables.dayImg
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.model.variables.josefinSansFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.customWhite0
import com.example.daracademy.ui.theme.customWhite4
import com.example.daracademy.ui.theme.customWhite5
import com.example.daracademy.ui.theme.customWhite6
import com.example.daracademy.viewModel.DaracademyViewModel

@Composable
fun CoursesScreen(
    viewModel  : DaracademyViewModel,
    phase      : String,
    annee      : String,
    matiere    : String,
    modifier   : Modifier = Modifier
) {


    val window = LocalView.current.context as Activity

    LaunchedEffect(key1 = window){
        window.window.apply {
            statusBarColor = Color.White.toArgb()
        }
    }

    viewModel.courses = emptyList()
    viewModel.getCourses(phase , annee , matiere , onSuccessCallBack = {} , onFailureCallBack = {})



    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {


        itemsIndexed(
            viewModel.courses,
        ){index , course->

            Item(course , viewModel)

            if (index != viewModel.courses.size-1){
                Spacer(modifier = Modifier.height(16.dp))
            }

        }
    }


}



@Composable
fun Item(course : Course, viewModel: DaracademyViewModel ,  modifier: Modifier = Modifier) {

    var loaingTeachers : Boolean by rememberSaveable {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true ){
        viewModel.getTeacherById(
            teacherId = course.teacherId,
            onSuccessCallBack = {
                loaingTeachers = false
            },
            onFailureCallBack = {

            }
        )
    }




    Column(
        modifier = Modifier
    ) {

        Spacer(modifier = Modifier.height(26.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
        ){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxHeight()
            ){
                Image(
                    painter = if (loaingTeachers == true) painterResource(id = R.drawable.teacher) else rememberAsyncImagePainter(model = viewModel.isTeacherExist(course.teacherId)?.photo) ,
                    contentDescription = null  ,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(55.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
            ){
                Text(
                    text = if (loaingTeachers == true) "Teacher Name" else viewModel.isTeacherExist(course.teacherId)?.name ?: "Teacher Name",
                    style = NormalTextStyles.TextStyleSZ6.copy(fontFamily = firaSansFamily),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = if (loaingTeachers == true) "Group : " else "Group : " + course.group,
                    style = NormalTextStyles.TextStyleSZ8.copy(fontFamily = josefinSansFamily , color = customWhite5 ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }


        Column(
            modifier = Modifier
        ) {
            course.lessons.forEachIndexed { index, lesson ->

                LessonCard(
                    lineColor = color1,
                    num = index,
                    lesson = lesson
                )

            }
        }
    }
}


@Composable
fun LessonCard(
    lineColor   : Color = Color.Blue,
    num         : Int      = 0,
    lesson      : Lesson,
    elevation   : Dp = 6.dp,
    shape       : Shape = RoundedCornerShape(12.dp),
    modifier    : Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(80.dp)
                .height(120.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp)
                    .background(lineColor)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(customWhite0)
                    .border(
                        width = 2.dp,
                        color = lineColor,
                        shape = CircleShape
                    )
            ) {
                Text(
                    text = "${num+1}",
                    style = NormalTextStyles.TextStyleSZ8.copy(color = lineColor , fontFamily = firaSansFamily)
                )
            }
        }

        Spacer(modifier = Modifier.width(6.dp))

        Surface(
            shadowElevation = elevation,
            shape = shape,
            modifier = Modifier
                .height(80.dp)
                .weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Spacer(modifier = Modifier.width(12.dp))

                Image(
                    painter = painterResource(id = dayImg[lesson.day]) ,
                    contentDescription = null ,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(36.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = lesson.start + "-" + lesson.end,
                    style = NormalTextStyles.TextStyleSZ8.copy(color = customWhite4 , fontFamily = firaSansFamily)
                )


                Spacer(modifier = Modifier.weight(1f))


                Spacer(modifier = Modifier.width(12.dp))
            }
        }

        Spacer(modifier = Modifier.width(26.dp))


    }
}


@Preview
@Composable
fun CoursesScreen_preview() {

    val context = LocalContext.current

    CoursesScreen(
        viewModel = viewModel(
            factory = object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if(modelClass.isAssignableFrom(DaracademyViewModel::class.java))
                        return DaracademyViewModel(context , null) as T
                    else
                        throw IllegalArgumentException("can't create daracademyViewModel (coursesScreen)")
                }
            }
        ),
        phase      ="",
        annee      ="",
        matiere    ="",
    )
}