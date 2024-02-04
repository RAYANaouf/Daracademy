package com.example.daracademy.view.screens.navigationScreen.formation.component.timeLine.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.customWhite0
import com.mxalbert.sharedelements.SharedElement

@Composable
fun point_dialog(
    show : Boolean = false,
    onDismiss : ()->Unit
) {


    if (show){

            Dialog(
                onDismissRequest = { onDismiss() }
            ) {


                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(35.dp))
                        .border(
                            width = 1.5.dp,
                            color = color1,
                            shape = RoundedCornerShape(35.dp)
                        )
//                        .fillMaxWidth(1f)
//                        .fillMaxHeight(0.7f)
//                        .heightIn(max = 450.dp)
                        .height(450.dp)
                        .width(300.dp)
                        .background(customWhite0)
                ) {

                }


        }
    }

}