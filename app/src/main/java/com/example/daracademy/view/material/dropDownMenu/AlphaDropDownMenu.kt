package com.example.alphaspace.screens.common.dropDownMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.PopupProperties

@Composable
fun AlphaDropDownMenu(
    expanded : Boolean,
    items : List<String> ,
    onClick : (String)->Unit,
    onDismissRequest : ()->Unit,
    background : Color = Color.White,
    modifier: Modifier = Modifier,
    menuModifier: Modifier = Modifier,
    ) {
    Surface(
        color    = background,
        modifier = modifier
    ) {
        DropdownMenu(
            expanded = expanded ,
            onDismissRequest = { onDismissRequest() },
            properties = PopupProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                clippingEnabled = true
            ),
            modifier = menuModifier
                .background(background)
        ) {

            items.forEach {item->
                DropdownMenuItem(
                    text = {
                        Text(text = item)
                    },
                    onClick = {
                        onClick(item)
                        onDismissRequest()
                    }
                )
            }

        }
    }
}

@Preview
@Composable
fun AlphaDropDownMenu_preview() {
    AlphaDropDownMenu(
        expanded = true ,
        onDismissRequest = {},
        items = listOf("Man","Woman"),
        onClick = {

        }
    )
}