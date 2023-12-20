package com.example.daracademy.view.screens.homeScreen.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.ui.theme.customBlack0
import com.example.daracademy.ui.theme.customWhite0
import com.example.daracademy.ui.theme.customWhite7

@Composable
fun TrainingStage(
    modifier : Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text       = "Training & Courses",
                style      = NormalTextStyles.TextStyleSZ6,
                fontFamily = firaSansFamily
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                painter            = painterResource(id = R.drawable.right_arrow),
                contentDescription = null,
                tint = customWhite7,
                modifier           = Modifier
                    .size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .height(200.dp + 16.dp)
        ) {



        }
    }
}

@Composable
fun TrainingStageItem(
    @DrawableRes image : Int,
    txt : String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .align(Alignment.BottomCenter)
                .background(Color(0x44000000))
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .alpha(0.6f)
                .background(customBlack0))
            Text(
                text     = txt,
                style    = NormalTextStyles.TextStyleSZ7.copy(color = customWhite0),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun TrainingStage_preview() {
    TrainingStage()
}