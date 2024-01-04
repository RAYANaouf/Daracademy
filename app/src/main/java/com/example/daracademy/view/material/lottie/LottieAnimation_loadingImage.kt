package com.example.daracademy.view.material.lottie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.daracademy.R


@Composable
fun LottieAnimation_loadingImage(
    modifier : Modifier = Modifier
){

    val lottieComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading_image))

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