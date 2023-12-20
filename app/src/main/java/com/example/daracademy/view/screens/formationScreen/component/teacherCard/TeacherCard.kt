package com.example.daracademy.view.screens.formationScreen.component.teacherCard

import android.text.style.StyleSpan
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.dataClasses.Teacher
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.model.variables.josefinSansFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.color2
import com.example.daracademy.ui.theme.color3
import com.example.daracademy.ui.theme.customWhite4
import com.example.daracademy.ui.theme.customWhite5
import com.example.daracademy.ui.theme.customWhite6

@Composable
fun TeacherCard(
    teacher      : Teacher?,
    shape        : Shape        = RoundedCornerShape(16.dp),
    elevation    : Dp           = 3.dp,
    borderStroke : BorderStroke = BorderStroke(width = 2.dp , color = customWhite4),
    color        : Color        = Color.White,
    modifier     : Modifier     = Modifier
) {

    Column(
        modifier = modifier
    ) {

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = color1 , fontFamily = firaSansFamily , fontSize = NormalTextStyles.TextStyleSZ2.fontSize) ){
                    append("T")
                }
                withStyle(style = SpanStyle(color = color3 , fontFamily = firaSansFamily , fontSize = NormalTextStyles.TextStyleSZ6.fontSize) ){
                    append("ea")
                }
                withStyle(style = SpanStyle(color = color2 , fontFamily = firaSansFamily , fontSize = NormalTextStyles.TextStyleSZ6.fontSize) ){
                    append("eacher")
                }

            },
            style = NormalTextStyles.TextStyleSZ6
        )

        Spacer(modifier = Modifier.height(4.dp))

        Surface(
            color           = color,
            shape           = shape,
            shadowElevation = elevation,
            border          = borderStroke,
            modifier        = Modifier
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Row {
                    Image(
                        painter            = if (teacher == null) painterResource(id = R.drawable.teacher) else rememberAsyncImagePainter(model = teacher?.photo) ,
                        contentDescription = null,
                        contentScale       = ContentScale.Crop,
                        modifier           = Modifier
                            .clip(CircleShape)
                            .size(35.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = if (teacher == null) "teacher name " else teacher?.name ?: "",
                        style = NormalTextStyles.TextStyleSZ8.copy(fontFamily = josefinSansFamily)
                    )

                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Kotlin Teacher,Learned at [University Name]\n" +
                            "Exemplified comprehensive Kotlin expertise,Applies practical insights from [University Name]'s curriculum,\n" +
                            "Facilitates immersive learning with industry-relevant practices,\n" +
                            "Brings depth through real-world software development experience,\n" +
                            "Creates a supportive environment for exploration and growth.",
                    style = NormalTextStyles.TextStyleSZ9.copy(color = customWhite5 , fontFamily = josefinSansFamily),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }

}


@Preview
@Composable
fun TeacherCard_preview() {
    TeacherCard(
        null
    )
}