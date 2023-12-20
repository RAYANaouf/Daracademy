package com.example.alphaspace.screens.common.textFields

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.customWhite0

@Composable
fun AlphaTextField(
    modifier : Modifier = Modifier,
    shape : Shape = RoundedCornerShape(12.dp),
    borderStroke: BorderStroke = BorderStroke(1.dp , color1),
    background : Color = customWhite0,
    text : String,
    onValueChange : (String)->Unit,
    textFieldStyle: TextStyle,
    hint : String ,
    hintStyle : TextStyle,
    maxLine   : Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    visualTransformation : VisualTransformation = VisualTransformation.None,
    cursorColor : Color = Color.Black
) {


    var focusRequester = remember {
        FocusRequester()
    }


    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .clip(shape)
            .border(
                border = borderStroke,
                shape = shape
            )
            .background(background)
            .clickable {
                focusRequester.requestFocus()
            }
            .padding(start = 10.dp, end = 10.dp, top = 6.dp, bottom = 6.dp)
    ) {



        BasicTextField(
            value = text,
            onValueChange = {
                onValueChange(it)
            },
            textStyle = textFieldStyle,
            maxLines = maxLine,
            cursorBrush = SolidColor(cursorColor),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            modifier = Modifier
                .focusRequester(focusRequester)
        )

        if (text == ""){
            Text(
                text = hint ,
                style = hintStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
            )
        }



    }
}


@Preview
@Composable
fun AlphaTextField_preview() {
    AlphaTextField(
        text = "",
        onValueChange = {

        },
        hint = "hint",
        hintStyle = TextStyle(),
        textFieldStyle = TextStyle(),
    )
}