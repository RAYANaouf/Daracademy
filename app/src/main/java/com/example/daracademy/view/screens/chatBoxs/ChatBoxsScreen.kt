package com.example.daracademy.view.screens.chatBoxs

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.dataClasses.MessageBox
import com.example.daracademy.model.sealedClasses.screens.Screens
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.viewModel.DaracademyViewModel
import com.example.daracademy.ui.theme.customBlack0

@Composable
fun ChatBoxsScreen(
    viewModel  : DaracademyViewModel,
    onNavigate : (Screens)->Unit = {},
    modifier   : Modifier = Modifier
) {



    val window = LocalView.current.context as Activity

    LaunchedEffect(key1 = window){
        window.window.apply {
            statusBarColor = Color.White.toArgb()
        }

    }

    LaunchedEffect(key1 = true ){
        viewModel.getAllMessageBoxs(
            onSuccessCallBack = {

            }
        )
    }



    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        LazyColumn(
            contentPadding = PaddingValues(top = 8.dp , end = 8.dp),
            modifier = Modifier
                .weight(1f)
        ) {

            items(viewModel.boxMessages){
                Item(
                    messageBox = it,
                    modifier = Modifier
                        .clickable {
                            onNavigate(Screens.ChatBox_Screen())
                        }
                        .padding(top = 8.dp, bottom = 8.dp)

                )
            }

        }

    }


}


@Composable
fun Item(
    messageBox : MessageBox,
    modifier: Modifier = Modifier
) {
    
    Row(
        modifier = modifier
    ) {

        Spacer(modifier = Modifier.width(16.dp))

        Image(
            painter = painterResource(id = R.drawable.math),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
//                .background(Color.Magenta)
                .padding(top = 16.dp)
                .size(45.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(20.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = messageBox.name ,
                style = NormalTextStyles.TextStyleSZ4.copy(color = customBlack0 , fontFamily = firaSansFamily , fontWeight = FontWeight.SemiBold ),
                modifier = Modifier
            )

            Text(
                text = messageBox.lastMessage,
                style = NormalTextStyles.TextStyleSZ9.copy(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }


        Spacer(modifier = Modifier.width(26.dp))
    }


    
}


@Preview
@Composable
fun ChatBoxsScreen_preview() {
    ChatBoxsScreen(
        viewModel = viewModel()
    )
}