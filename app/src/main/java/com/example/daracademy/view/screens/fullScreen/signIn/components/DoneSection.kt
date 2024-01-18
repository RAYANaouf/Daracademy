package com.example.daracademy.view.screens.fullScreen.signIn.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bigsam.grafic.material.loadingEffect.LottieAnimation_loading_1
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.variables.nunitoFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.customWhite0
import com.example.daracademy.view.material.lottie.LottieAnimation_loading

@Composable
fun DoneSection(
    loading  : Boolean  = false,
    onClick  : ()->Unit = {},
    modifier : Modifier = Modifier
) {



    if (!loading){
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .clip(RoundedCornerShape(12.dp))
                .background(color1)
                .clickable { onClick() }
        ) {
            Text(
                style = NormalTextStyles.TextStyleSZ7.copy(color = customWhite0 , fontFamily = nunitoFamily , fontWeight = FontWeight.SemiBold),
                text = "Sign in"
            )
        }
    }
    else{
        LottieAnimation_loading_1(
            modifier = modifier
        )
    }

}


@Preview
@Composable
fun DoneSection_preview() {
    DoneSection()
}