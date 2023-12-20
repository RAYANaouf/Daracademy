package com.example.daracademy.view.screens.homeScreen.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.dataClasses.MessageBox
import com.example.daracademy.model.dataClasses.Post
import com.example.daracademy.model.sealedClasses.screens.Screens
import com.example.daracademy.model.variables.josefinSansFamily
import com.example.daracademy.ui.theme.color3
import com.example.daracademy.ui.theme.customBlack0
import com.example.daracademy.ui.theme.customBlack1
import com.example.daracademy.ui.theme.customBlack4
import com.example.daracademy.ui.theme.customWhite0
import com.example.daracademy.ui.theme.customWhite2
import com.example.daracademy.ui.theme.customWhite4


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostItem(
    post: Post,
    onChatClick     : (String)->Unit = {},
    modifier: Modifier = Modifier
) {

    val pagerstate = rememberPagerState(
        initialPage = 0
    ) {
        post.imgs.size
    }

    var like by rememberSaveable {
        mutableStateOf(false)
    }

    var saved by rememberSaveable {
        mutableStateOf(false)
    }

    var show by remember{
        mutableStateOf(false)
    }



    Surface(
        shadowElevation = 6.dp,
        shape = RoundedCornerShape(16.dp),
        color = customWhite0,
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = customWhite2,
                shape = RoundedCornerShape(16.dp)
            )
    ) {

        Column(
            horizontalAlignment = if (post.ltr) Alignment.Start else Alignment.End
        ) {

            Text(
                text = "11/42/2023",
                style = NormalTextStyles.TextStyleSZ9,
                fontFamily = josefinSansFamily,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .padding(start = 4.dp , end = 4.dp)
            )

            Text(
                text = post.title ,
                style = NormalTextStyles.TextStyleSZ8.copy(customBlack1),
                fontFamily = josefinSansFamily,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = if (post.ltr) TextAlign.Left else TextAlign.Right,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(start = 4.dp, end = 8.dp, top = 0.dp, bottom = 4.dp)
            )

            Text(
                text = post.desc,
                style = NormalTextStyles.TextStyleSZ9.copy(customBlack4),
                fontFamily = josefinSansFamily,
                maxLines = if (show) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis,
                textAlign = if (post.ltr) TextAlign.Left else TextAlign.Right,
                modifier = Modifier
                    .padding(start = 4.dp, end = 8.dp, top = 4.dp, bottom = 10.dp)
                    .fillMaxWidth()
                    .clickable {
                        show = !show
                    }
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(customWhite4))

            HorizontalPager(
                state = pagerstate,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Image(
                    painter = rememberAsyncImagePainter(post.imgs[it]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(customWhite4))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth()
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable {
                            like = !like
                        }

                ) {
                    Image(
                        painter = painterResource(id = if (like) R.drawable.selected_heart else R.drawable.heart),
                        contentDescription = null,
                        modifier = Modifier
                            .size(if (like) 40.dp else 26.dp)
                    )
                }

                Surface(
                    shadowElevation = 1.dp
                ) {
                    Divider(
                        color = customWhite4,
                        modifier = Modifier
                            .fillMaxHeight(0.4f)
                            .width(1.dp)
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable{
                            onChatClick(post.postId)
                        }
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.live_chat),
                        contentDescription = null,
                        modifier = Modifier
                            .size(26.dp)
                    )
                }

                Surface(
                    shadowElevation = 1.dp
                ) {
                    Divider(
                        color = customWhite4,
                        modifier = Modifier
                            .fillMaxHeight(0.4f)
                            .width(1.dp)
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable {
                            saved = !saved
                        }
                ) {
                    Icon(
                        painter = painterResource(id = if (saved) R.drawable.selected_save else R.drawable.unselected_save ),
                        contentDescription = null,
                        tint = if (saved) color3 else customBlack0,
                        modifier = Modifier
                            .size(if (saved) 30.dp else 26.dp)
                    )
                }
            }
        }
    }

}



@Composable
fun PostItem_preview() {

}


@Preview
@Composable
fun PostsSection_preview() {

}