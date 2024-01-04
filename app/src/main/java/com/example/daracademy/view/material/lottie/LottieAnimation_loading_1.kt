package com.example.bigsam.grafic.material.loadingEffect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.daracademy.R


@Composable
fun LottieAnimation_loading_1(
    modifier : Modifier = Modifier
){

    val lottieComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading_animation))

    val progress by
    animateLottieCompositionAsState(
        composition = lottieComposition,
        isPlaying = true,
        iterations = Int.MAX_VALUE
    )

    LottieAnimation(
        composition = lottieComposition,
        progress = {
            progress
        },
        modifier = modifier
    )


}