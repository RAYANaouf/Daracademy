package com.example.daracademy.view.screens.fullScreen.signIn.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.color2
import com.example.daracademy.ui.theme.color3

@Composable
fun SignInTitle(
    modifier : Modifier = Modifier
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 85.sp,
                        color = color1,
                        fontFamily = firaSansFamily
                    )
                ){
                    append("S")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 45.sp,
                        color = color3,
                        fontFamily = firaSansFamily
                    )
                ){
                    append("i")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 45.sp,
                        color = color2,
                        fontFamily = firaSansFamily
                    )
                ){
                    append("gn in")
                }
            },
            style = NormalTextStyles.TextStyleSZ6
        )
    }

}


@Preview
@Composable
fun SignInTitle_preview() {
    SignInTitle()
}