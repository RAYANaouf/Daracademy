package com.example.daracademy.view.screens.formationScreen.component.schedulerCards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.dataClasses.Lesson
import com.example.daracademy.model.variables.dayImg
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.model.variables.josefinSansFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.color2
import com.example.daracademy.ui.theme.color3
import com.example.daracademy.ui.theme.customWhite0
import com.example.daracademy.ui.theme.customWhite4
import com.example.daracademy.ui.theme.customWhite5

@Composable
fun SchedulerCards(
    lessons : List<Lesson>,
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier
    ) {

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = color1 , fontFamily = firaSansFamily , fontSize = NormalTextStyles.TextStyleSZ2.fontSize) ){
                    append("S")
                }
                withStyle(style = SpanStyle(color = color3 , fontFamily = firaSansFamily , fontSize = NormalTextStyles.TextStyleSZ6.fontSize) ){
                    append("ch")
                }
                withStyle(style = SpanStyle(color = color2 , fontFamily = firaSansFamily , fontSize = NormalTextStyles.TextStyleSZ6.fontSize) ){
                    append("eduler")
                }

            },
            style = NormalTextStyles.TextStyleSZ6
        )

        Spacer(modifier = Modifier.height(4.dp))

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
        emptyList()
    )
}