package com.example.daracademyadmin.graphics.material.AlphaTextFields

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.checkScrollableContainerConstraints
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bigsam.model.data.`object`.NormalTextStyles

@Composable
fun AlphaUnderLinedTextField(
    text : String,
    onValueChange : (String)->Unit,
    textFieldStyle: TextStyle,
    hint : String,
    hintStyle : TextStyle,
    underLineColor : Color = Color.Transparent,
    underLineWidth : Float = 1.5f,
    maxLine : Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation : VisualTransformation = VisualTransformation.None,
    cursorColor : Color = Color.LightGray,
    modifier : Modifier = Modifier
) {

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .wrapContentWidth()
            .drawBehind {
                drawLine(
                    color       = underLineColor,
                    strokeWidth = underLineWidth,
                    start       = Offset(0f , size.height),
                    end         = Offset(size.width , size.height)
                )
            }
            .padding(bottom = 4.dp)

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
            visualTransformation = visualTransformation,
            modifier = Modifier
        )

        if (text == ""){
            Text(
                text  = hint,
                style = hintStyle
            )
        }


    }
}


@Preview
@Composable
fun AlphaUnderLinedTextField_preview() {
    AlphaUnderLinedTextField(
        text = "hello there",
        textFieldStyle = NormalTextStyles.TextStyleSZ9,
        onValueChange = {},
        hint = "",
        hintStyle = NormalTextStyles.TextStyleSZ9,
    )
}