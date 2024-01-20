package com.example.daracademy.view.screens.navigationScreen.teacherHome.components.headerSection

import android.graphics.Color.parseColor
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.color2
import com.example.daracademy.ui.theme.color3
import com.example.daracademy.ui.theme.customBlack3
import com.example.daracademy.ui.theme.customWhite0
import com.example.daracademy.ui.theme.customWhite1
import com.example.daracademy.ui.theme.customWhite2

@Composable
fun HeaderSection(
    onNavigate : (Index : Int)->Unit ={},
    modifier: Modifier = Modifier
) {

    Surface(
        color    = Color(parseColor("#FFFFFF")),
        shape    = RoundedCornerShape(bottomEnd = 55.dp, bottomStart = 55.dp),
        shadowElevation = 12.dp,
        border   = BorderStroke(
            width = 1.5.dp,
            color = Color(parseColor("#2388C1")),
        ),
        modifier = modifier
            .offset(x = 0.dp, y = (-2).dp)
            .fillMaxWidth()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(parseColor("#662388C1")))
                .padding(start = 36.dp, end = 36.dp)
        ) {

            Spacer(modifier = Modifier.height(35.dp))

            MonyIndicator()

            Spacer(modifier = Modifier.height(30.dp))

            actionRow(
                onClick = {index->
                    onNavigate(index)
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(30.dp))

            Spacer(
                modifier = Modifier
                    .height(4.dp)
                    .width(35.dp)
                    .clip(CircleShape)
                    .background(Color(parseColor("#2388C1")))
            )

            Spacer(modifier = Modifier.height(16.dp))


        }
    }

}

@Composable
fun MonyIndicator(
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = color1,
                shape = RoundedCornerShape(12.dp)
            )
            .background(customWhite0)
            .fillMaxWidth()
            .clickable { }
    ) {
        Row(
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier              = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = "35000 DA",
                style = NormalTextStyles.TextStyleSZ4.copy(color = color1 , fontFamily = firaSansFamily , fontWeight = FontWeight.SemiBold)
            )

            Icon(
                painter            = painterResource(id = R.drawable.eye_icon) ,
                contentDescription = null,
                tint               = color3 ,
                modifier           = Modifier
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(0.75f)
        ) {
            Box(
                modifier = Modifier
                    .height(12.dp)
                    .clip(CircleShape)
                    .background(customWhite2)
                    .fillMaxWidth()
                    .border(
                        width = 1.5.dp,
                        color = Color(parseColor("#0B357D")),
                        shape = CircleShape
                    )
            ) {
                Box(
                    modifier = Modifier
                        .height(12.dp)
                        .clip(CircleShape)
                        .background(color1)
                        .fillMaxWidth(0.73f)
                )
            }
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .fillMaxWidth(1f)
            ) {
                Text(
                    text = "22/30",
                    style = NormalTextStyles.TextStyleSZ9.copy(color = customBlack3),
                    modifier = Modifier
                        .offset(x = 16.dp, y = 0.dp)
                        .zIndex(5f)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun actionRow(
    onClick  : (Int)->Unit = {},
    modifier : Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(55.dp)
                .border(
                    width = 1.dp,
                    color = color1,
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
                .background(customWhite0)
                .clickable {
                    onClick(1)
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.student_icon),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(26.dp)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(55.dp)
                .border(
                    width = 1.dp,
                    color = color1,
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
                .background(customWhite0)
                .clickable {
                    onClick(2)
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.support),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(26.dp)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(55.dp)
                .border(
                    width = 1.dp,
                    color = color1,
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
                .background(customWhite0)
                .clickable {
                    onClick(3)
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.formation),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(26.dp)
            )
        }

    }

}

@Preview
@Composable
fun HeaderSection_preview() {
    HeaderSection()
}