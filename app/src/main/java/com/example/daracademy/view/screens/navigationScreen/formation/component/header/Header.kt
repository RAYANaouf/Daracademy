package com.example.daracademy.view.screens.navigationScreen.formation.component.header

import android.graphics.Color.parseColor
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.ui.theme.customWhite0

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeaderItem(
    images    : List<String>,
    onNavBack : ()->Unit = {},
    modifier  : Modifier = Modifier
) {

    val pagerState = rememberPagerState(
        initialPage = 0
    ) {
        images.size
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(top = 30.dp , start = 16.dp)
                .clip(CircleShape)
                .background(Color(parseColor("#33000000")))
                .zIndex(10f)
                .size(40.dp)
                .clickable { onNavBack() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.right_arrow ),
                contentDescription = null,
                tint = Color.White ,
                modifier =Modifier
                    .size(20.dp)
                    .rotate(180f)
            )
        }


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(top = 30.dp , end = 16.dp)
                .clip(CircleShape)
                .background(Color(parseColor("#33000000")))
                .zIndex(10f)
                .size(40.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.fullscreen_icon),
                contentDescription = null,
                tint = Color.White ,
                modifier =Modifier
                    .size(20.dp)
                    .rotate(180f)
            )
        }


        HorizontalPager(
            state    = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { index ->
            Image(
                painter            = rememberAsyncImagePainter(model = images[index]) ,
                contentDescription = null ,
                contentScale       = ContentScale.Crop ,
                modifier = Modifier
                    .fillMaxSize()
            )
        }



    }
}


@Preview
@Composable
fun HeaderItem_preview() {
    HeaderItem(
        images = listOf(
            "https://firebasestorage.googleapis.com/v0/b/daracademyfireproject.appspot.com/o/formations%2Fformation%20on%20flutter%20_formation_1703081027452%2Fimg_0?alt=media&token=18c914bf-c734-41f4-8f16-65711570d229",
            "https://firebasestorage.googleapis.com/v0/b/daracademyfireproject.appspot.com/o/formations%2Fformation%20on%20flutter%20_formation_1703081027452%2Fimg_1?alt=media&token=72dd74ab-0dda-47af-9be9-24f9bdd7393e",
            "https://firebasestorage.googleapis.com/v0/b/daracademyfireproject.appspot.com/o/formations%2Fformation%20on%20flutter%20_formation_1703081027452%2Fimg_2?alt=media&token=12ee1584-8aa6-4e5d-9439-784149839003"
        )
    )
}