package com.example.bigsam.grafic.material.topBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.customWhite5

@Composable
fun AlphaTopBar2(
    modifier       : Modifier = Modifier,
    img            : Painter  ,
    onImgClick     : ()->Unit = {},
    onMenuClick    : ()->Unit = {},
    txt            : String   ,
    containerColor : Color = Color.White  ,
    elevation      : Dp = 8.dp,

) {

    Surface(
        shadowElevation = elevation,
        color = containerColor,
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
    ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 12.dp, end = 8.dp)
        ) {
                
            Image(
                painter = img,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .clickable {
                        onImgClick()
                    }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text       = txt,
                style      = NormalTextStyles.TextStyleSZ5.copy(color = color1),
                fontFamily = firaSansFamily
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                Icons.Default.Menu,
                contentDescription = "",
                tint = customWhite5,
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        onMenuClick()
                    }
            )
                
        }

    }
}


@Preview
@Composable
fun AlphaTopBar2_preview() {
    AlphaTopBar2(
        img = painterResource(id = R.drawable.daracademy),
        txt = "Daracademy"
    )
}