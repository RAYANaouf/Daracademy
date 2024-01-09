package com.example.daracademy.view.screens.fullScreen.signIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.daracademy.view.screens.fullScreen.signIn.components.CoreSection
import com.example.daracademy.view.screens.fullScreen.signIn.components.SignInTitle

@Composable
fun SignInScreen(
    modifier : Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {
        SignInTitle(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        )

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
        ) {
            CoreSection()
        }

        Box(
            modifier = Modifier
                .weight(1f)
        ) {

        }
    }

}

@Preview
@Composable
fun SignInScreen_preview() {
    SignInScreen()
}