package com.example.daracademy.view.screens.homeScreen.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alphaspace.screens.common.textFields.AlphaTextField
import com.example.bigsam.grafic.material.loadingEffect.LottieAnimation_loading_1
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.customWhite4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddChatFeatureDialog(
    show        : Boolean,
    onDismiss   : ()->Unit          = {},
    onDoneClick : (String)->Boolean = {false},
    modifier    : Modifier          = Modifier
) {

    if (show){

        var name by remember{
            mutableStateOf("")
        }

        var loading by remember {
            mutableStateOf(false)
        }

        AlertDialog(
            onDismissRequest = {
                if (!loading)
                    onDismiss()
            },
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                ) {
                    Text(
                        text  = "chat with admin",
                        style = NormalTextStyles.TextStyleSZ6.copy(color = color1 , fontFamily = firaSansFamily)
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                AlphaTextField(
                    text           = name,
                    onValueChange  = {
                        name = it
                    },
                    textFieldStyle = NormalTextStyles.TextStyleSZ6,
                    hint           = "Enter your name",
                    hintStyle      = NormalTextStyles.TextStyleSZ6,
                    cursorColor    = color1
                )

                Spacer(modifier = Modifier.height(55.dp))
                
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Box(
                        modifier = Modifier
                            .clickable {
                                if (!loading)
                                    onDismiss()
                            }
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)
                    ){
                        Text(
                            text  = "Cancel",
                            style = NormalTextStyles.TextStyleSZ7.copy(color = customWhite4 , fontFamily = firaSansFamily)
                        )
                    }
                    
                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .clickable {
                                loading = true

                                loading = onDoneClick(name)

                            }
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)
                    ){
                        if (!loading){
                            Text(
                                text  = "Done",
                                style = NormalTextStyles.TextStyleSZ7.copy(color = color1 , fontFamily = firaSansFamily)
                            )
                        }
                        else{
                            LottieAnimation_loading_1()
                        }
                    }
                }
                
                

            }

        }
    }

}


@Preview
@Composable
fun AddChatFeatureDialog_preview() {

}