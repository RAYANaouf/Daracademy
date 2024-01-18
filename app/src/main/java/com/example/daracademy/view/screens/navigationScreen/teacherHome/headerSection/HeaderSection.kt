package com.example.daracademy.view.screens.navigationScreen.teacherHome.headerSection

import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.color2
import com.example.daracademy.ui.theme.color3
import com.example.daracademy.ui.theme.customWhite0

@Composable
fun HeaderSection(
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .offset(x = 0.dp, y = (-2).dp)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(bottomEnd = 55.dp, bottomStart = 55.dp)
            )
            .background(Color(parseColor("#552388C1")))
            .border(
                width = 1.5.dp,
                color = Color(parseColor("#2388C1")),
                shape = RoundedCornerShape(bottomStart = 55.dp, bottomEnd = 55.dp)
            )
            .padding(start = 36.dp, end = 36.dp)
    ) {

        Spacer(modifier = Modifier.height(55.dp))

        MonyIndicator()

        Spacer(modifier = Modifier.height(36.dp))

        actionRow(modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(36.dp))


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
        Spacer(modifier = Modifier.height(55.dp))
    }
}

@Composable
fun actionRow(
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .size(55.dp)
                .border(
                    width = 1.dp,
                    color = color1,
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
                .background(customWhite0)
        ) {

        }

        Box(
            modifier = Modifier
                .size(55.dp)
                .border(
                    width = 1.dp,
                    color = color1,
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
                .background(customWhite0)
        ) {

        }

        Box(
            modifier = Modifier
                .size(55.dp)
                .border(
                    width = 1.dp,
                    color = color1,
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
                .background(customWhite0)
        ) {

        }

    }

}

@Preview
@Composable
fun HeaderSection_preview() {
    HeaderSection()
}