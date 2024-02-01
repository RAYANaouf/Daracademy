package com.example.daracademy.view.screens.navigationScreen.formation.component.schedulerCards

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.dataClasses.Lesson
import com.example.daracademy.model.variables.dayImg
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.model.variables.josefinSansFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.color2
import com.example.daracademy.ui.theme.color3
import com.example.daracademy.ui.theme.customBlack5
import com.example.daracademy.ui.theme.customBlack6
import com.example.daracademy.ui.theme.customBlack7
import com.example.daracademy.ui.theme.customWhite4

@Composable
fun SchedulerCards(
    lessons : List<Lesson>,
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier
    ) {

        Text(
            text = "Scheduler",
            style = NormalTextStyles.TextStyleSZ5.copy(fontFamily = josefinSansFamily , color = customBlack5),
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {



            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .horizontalScroll(rememberScrollState())
            ) {

                lessons.forEachIndexed { index, lesson -> 
                    LessonCard(lesson = lesson)
                    
                    if (index < lessons.size-1 ){
                        Spacer(modifier = Modifier.width(18.dp))
                    }
                }



            }

        }
    }

}


@Composable
fun LessonCard(
    lesson      : Lesson,
    shape       : Shape = RoundedCornerShape(12.dp),
    borderColor : Color = Color.LightGray,
    borderWidth : Dp    = 2.dp,
    modifier    : Modifier = Modifier
) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(80.dp)
            .width(180.dp)
            .clip(shape)
            .border(
                width = borderWidth,
                color = borderColor,
                shape = shape
            )
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


@Preview
@Composable
fun SchedulerCards_preview() {
    SchedulerCards(
        arrayListOf(Lesson(),Lesson(),Lesson())
    )
}