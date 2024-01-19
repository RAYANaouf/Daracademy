package com.example.daracademy.view.screens.navigationScreen.teacherHome.components.formationsCard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.model.variables.nunitoFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.color2
import com.example.daracademy.ui.theme.color3
import com.example.daracademy.ui.theme.customWhite3
import com.example.daracademy.ui.theme.customWhite5
import com.example.daracademy.ui.theme.customWhite6


@Composable
fun FormationsCard(
    elevation : Dp = 1.dp,
    shapes    : Shape = RoundedCornerShape(24.dp),
    color     : Color = Color.White,
    modifier: Modifier = Modifier
) {

    Surface(
        shadowElevation = elevation,
        color           = color,
        border          = BorderStroke(
            width = 1.dp,
            color = color1
        ),
        shape           = shapes,
        modifier        = modifier
    ) {

        Column(
            modifier = Modifier
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier =Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(fontSize = NormalTextStyles.TextStyleSZ4.fontSize , fontFamily = firaSansFamily , color = color1)
                        ){
                            append("F")
                        }
                        withStyle(
                            SpanStyle(fontSize = NormalTextStyles.TextStyleSZ5.fontSize , fontFamily = firaSansFamily , color = color3)
                        ){
                            append("o")
                        }
                        withStyle(
                            SpanStyle(fontSize = NormalTextStyles.TextStyleSZ5.fontSize , fontFamily = firaSansFamily , color = color2)
                        ){
                            append("rmations")
                        }
                    },
                    style = NormalTextStyles.TextStyleSZ5.copy(fontFamily = firaSansFamily)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, bottom = 10.dp)
            ) {

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Started formations",
                    style = NormalTextStyles.TextStyleSZ7.copy(fontFamily = nunitoFamily , color = customWhite6)
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(id = R.drawable.right_arrow) ,
                    contentDescription = null ,
                    tint = customWhite6,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {

                        }
                )
                Spacer(modifier = Modifier.width(16.dp))
            }

            Spacer(modifier = Modifier.height(80.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, bottom = 10.dp)
            ) {

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Posed formations",
                    style = NormalTextStyles.TextStyleSZ7.copy(fontFamily = nunitoFamily , color = customWhite5)
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(id = R.drawable.right_arrow) ,
                    contentDescription = null ,
                    tint = customWhite5,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {

                        }
                )
                Spacer(modifier = Modifier.width(16.dp))
            }

            Spacer(modifier = Modifier.height(80.dp))
        }

    }

}


@Preview
@Composable
fun FormationsCard_preview() {
    FormationsCard()
}