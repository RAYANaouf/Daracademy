package com.example.daracademy.view.screens.navigationScreen.formation.component.LogoRowItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.color2
import com.example.daracademy.ui.theme.color3

@Composable
fun LogoRowItem(
    modifier : Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(start = 16.dp , end = 16.dp)
            .height(60.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.daracademy),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = color1,
                        fontSize = NormalTextStyles.TextStyleSZ1.fontSize
                    )
                ){
                    append("D")
                }
                withStyle(
                    style = SpanStyle(
                        color = color3
                    )
                ){
                    append("ara")
                }
                withStyle(
                    style = SpanStyle(
                        color = color2
                    )
                ){
                    append("cademy")
                }
            },
            style = NormalTextStyles.TextStyleSZ5.copy(fontFamily = firaSansFamily),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

}