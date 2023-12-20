package com.example.daracademy.view.screens.formationScreen.component.header

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.ui.theme.customWhite0

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeaderItem(
    images : List<String>,
    formationName : String,
    modifier: Modifier = Modifier
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

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White,
                            Color.Transparent
                        )
                    )
                )
                .zIndex(10f)
        )


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

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .background(Color(android.graphics.Color.parseColor("#55000000")))
                .padding(start = 26.dp, top = 8.dp, bottom = 8.dp)
        ) {
            Text(
                text     = formationName,
                style    = NormalTextStyles.TextStyleSZ2.copy(color = customWhite0 , fontFamily = firaSansFamily),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
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
        ),
        formationName = "formation on flutter"
    )
}