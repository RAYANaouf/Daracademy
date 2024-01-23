package com.example.daracademy.view.screens.navigationScreen.teacherCourses

import android.graphics.Color.parseColor
import android.graphics.Paint.Align
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalAccessibilityManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.dataClasses.Course
import com.example.daracademy.model.dataClasses.Matiere
import com.example.daracademy.model.dataClasses.MatiereWithCourses
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.model.variables.les_annees_d_etude.matieres_primaire_premiere_annee
import com.example.daracademy.model.variables.nunitoFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.customBlack3
import com.example.daracademy.ui.theme.customWhite0
import com.example.daracademy.ui.theme.customWhite3
import com.example.daracademy.ui.theme.customWhite7
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
//                Toast.makeText(context , "$it" , Toast.LENGTH_LONG).show()
                course_matiere.addAll(it)
            },
            onFailureCallBack = {

            }
        )
    }






    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {

        course_matiere.groupBy {
            it.matiere.phase
        }.forEach{

            herderItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(parseColor("#662388C1")))
                    .padding(16.dp)
                ,
                txt = it.key
            )
            
            Spacer(modifier = Modifier.height(12.dp))

            listOfCourses(
                matiereWithCourses = it.value,
                modifier = Modifier
                    .padding(start = 36.dp , end = 16.dp)
            )

        }

    }

}




@Composable
fun listOfCourses(
    matiereWithCourses  : List<MatiereWithCourses>,
    modifier : Modifier = Modifier
) {

    val context = LocalContext.current


    matiereWithCourses.groupBy {
        it.matiere.annee
    }.forEach {
        Column(
            modifier = modifier
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(color1))
                
                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text  = "${it.key}",
                    style = NormalTextStyles.TextStyleSZ5
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            it.value.forEachIndexed { index, item ->
                MatiereItem(
                    item,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

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
fun MatiereItem(
    matiereWithCourse: MatiereWithCourses,
    modifier: Modifier = Modifier
) {

    Surface(
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 1.dp,
        border = BorderStroke(
            width = 1.dp,
            color = customWhite3
        ),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp, bottom = 8.dp, end = 10.dp)
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
            ) {

                Image(
                    painter = rememberAsyncImagePainter(model = if (matiereWithCourse.matiere.imgUrl != "") matiereWithCourse.matiere.imgUrl else matieres_primaire_premiere_annee[matiereWithCourse.matiere.img].img) ,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(55.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text  = matiereWithCourse.matiere.name ,
                    style = NormalTextStyles.TextStyleSZ9.copy(fontFamily = nunitoFamily , textAlign = TextAlign.Center ),
                    maxLines = 2
                )

            }

            Spacer(modifier = Modifier.width(25.dp))

            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.square_point),
                        contentDescription = null,
                        tint = customWhite7,
                        modifier = Modifier
                            .size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Students : 7",
                        style = NormalTextStyles.TextStyleSZ9.copy(fontFamily = nunitoFamily )
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.square_point),
                        contentDescription = null,
                        tint = customWhite7,
                        modifier = Modifier
                            .size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "price : 1500DA",
                        style = NormalTextStyles.TextStyleSZ9.copy(fontFamily = nunitoFamily )
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.square_point),
                        contentDescription = null,
                        tint = customWhite7,
                        modifier = Modifier
                            .size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "State : Started",
                        style = NormalTextStyles.TextStyleSZ9.copy(fontFamily = nunitoFamily )
                    )
                }


            }
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