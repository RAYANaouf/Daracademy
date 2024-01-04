package com.example.daracademy.view.screens.chat

import android.app.Activity
import android.graphics.Color.parseColor
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.alphaspace.screens.common.textFields.AlphaTextField
import com.example.bigsam.grafic.material.loadingEffect.LottieAnimation_loading_1
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.dataClasses.Message
import com.example.daracademy.model.dataClasses.MessageBox
import com.example.daracademy.viewModel.DaracademyViewModel
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.customWhite0
import com.example.daracademy.ui.theme.customWhite1
import com.example.daracademy.ui.theme.customWhite2
import com.example.daracademy.ui.theme.customWhite3
import com.example.daracademy.ui.theme.customWhite6
import kotlin.math.max

@Composable
fun ChatScreen(
    viewModel   : DaracademyViewModel,
    userId      : String,
    productId   : String,
    name        : String = "",
    modifier    : Modifier = Modifier
) {


    val window  = LocalView.current.context as Activity
    val context = LocalContext.current

    LaunchedEffect(key1 = window){
        window.window.apply {
            statusBarColor = Color.White.toArgb()
        }
    }



    var message by remember {
        mutableStateOf("")
    }

    var photo_uri : Uri? by rememberSaveable {
        mutableStateOf(null)
    }




    var launcher_imgs = rememberLauncherForActivityResult(contract =  ActivityResultContracts.PickVisualMedia()){ uri->
        if (uri != null){
            photo_uri = uri
        }
    }

    var loading by remember {
        mutableStateOf(false)
    }


    var messages  = rememberSaveable {
        mutableListOf<Message>()
    }



    LaunchedEffect(key1 = true ){
        viewModel.getBoxMessages(
            userId            = userId,
            productId         = productId,
            onSuccessCallBack = {
                messages.clear()
                messages.addAll(it)
            },
            onFailureCallBack = {

            }
        )
    }


    
    Column(
        modifier = modifier
    ) {


        LazyColumn(
            contentPadding = PaddingValues(bottom = 12.dp),
            reverseLayout = true,
            modifier = Modifier
                .weight(1f)
        ) {

            items(viewModel.repo.chatMessages){
                messageItem(
                    message         = it,
                    rightColor      = color1,
                    leftColor       = customWhite1,
                    rightColor_text = customWhite0,
                    leftColor_text  = customWhite6,
                    modifier        = Modifier
                        .padding(4.dp)
                )


                Spacer(modifier = Modifier.height(6.dp))
            }


        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(customWhite0)
                .drawBehind {
                    drawLine(
                        color = customWhite2,
                        strokeWidth = 2f,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f)
                    )
                }
                .padding(top = 5.dp, bottom = 5.dp)
        ) {

            if (photo_uri != null){

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(customWhite0)
                ) {

                    Spacer(modifier = Modifier.width(12.dp))

                        Image(
                            painter = rememberAsyncImagePainter(model = photo_uri),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(150.dp)
                                .width(95.dp)
                                .padding(top = 10.dp, bottom = 10.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .border(
                                    width = 1.dp,
                                    color = customWhite2,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        )



                }

            }

            Row {
                Spacer(modifier = Modifier.width(16.dp))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            launcher_imgs.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.photo_icon_inline) ,
                        contentDescription = null  ,
                        tint               = color1,
                        modifier           = Modifier
                            .size(26.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                AlphaTextField(
                    text = message,
                    onValueChange = {
                        message = it
                    },
                    textFieldStyle = NormalTextStyles.TextStyleSZ8,
                    hint = "message",
                    hintStyle = NormalTextStyles.TextStyleSZ8,
                    maxLine = 4,
                    shape = RoundedCornerShape(20.dp),
                    background = Color(parseColor("#f5f5f5")),
                    cursorColor = color1,
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = 40.dp)
                )


                Spacer(modifier = Modifier.width(16.dp))

                if (!loading){
                    Image(
                        painter = painterResource(id = R.drawable.send_icon) ,
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .size(26.dp)
                            .clickable {

                                loading = true


                                viewModel.sendMsg(
                                    userId,
                                    productId,
                                    Message(
                                        msg = message,
                                        photo = photo_uri?.toString() ?: "",
                                        person_msg = true
                                    ),
                                    onSuccessCallBack = {
                                        loading = false
                                        message = ""
                                        photo_uri = null

                                    },
                                    onFailureCallBack = {
                                        loading = false
                                        Toast
                                            .makeText(context, "$it", Toast.LENGTH_LONG)
                                            .show()
                                    }
                                )
                            }
                    )
                }
                else{
                    LottieAnimation_loading_1(
                        modifier = Modifier
                            .size(26.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

            }

        }

    }
    
}


@Composable
fun messageItem(
    message         : Message,
    shape           : Shape = RoundedCornerShape(0.dp),
    elevation       : Dp = 0.dp,
    leftColor       : Color = Color.Blue,
    leftColor_text  : Color = Color.White,
    rightColor      : Color = Color.LightGray,
    rightColor_text : Color = Color.Black,
    modifier        : Modifier = Modifier
) {


    Row(
        horizontalArrangement = if (!message.person_msg) Arrangement.Start  else Arrangement.End,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = if (!message.person_msg) 4.dp else 65.dp,
                end = if (!message.person_msg) 65.dp else 4.dp
            )
    ) {




        Surface(
            shadowElevation = elevation,
            shape = RoundedCornerShape(topStart = if (!message.person_msg) 0.dp else 18.dp , topEnd = if (!message.person_msg) 18.dp else 0.dp , bottomStart = 18.dp , bottomEnd = 18.dp ),
            color = if (!message.person_msg) leftColor else rightColor,
            modifier = Modifier
                .padding(top = 18.dp)
        ) {

            Column(
                modifier = Modifier
                    .padding( 8.dp )
            ) {

                if (message.msg != ""){
                    Text(
                        text = message.msg,
                        style = NormalTextStyles.TextStyleSZ8.copy(color = if (!message.person_msg) leftColor_text else rightColor_text)
                    )
                }

                if (message.photo != ""){

                    if (message.msg != ""){
                        Spacer(modifier = Modifier.height(4.dp))
                    }

                    Box(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    topStart = if (!message.person_msg) 0.dp else 12.dp,
                                    topEnd = if (!message.person_msg) 12.dp else 0.dp,
                                    bottomStart = 12.dp,
                                    bottomEnd = 12.dp
                                )
                            )
                    ) {

                        Image(
                            painter = rememberAsyncImagePainter(model    = message.photo ) ,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier           = Modifier
                                .width(200.dp)
                                .height(200.dp)
                        )
                    }

                }

            }
        }

        if (message.person_msg){
            Image(
                painter = painterResource(id = R.drawable.student_icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))
        }
    }

}

@Preview
@Composable
fun ChatScreen_preview() {

    val context = LocalContext.current
    ChatScreen(
        userId    = "",
        viewModel = viewModel(
            factory = object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if(modelClass.isAssignableFrom(DaracademyViewModel::class.java))
                        return DaracademyViewModel(context ) as T
                    else
                        throw IllegalArgumentException("can't create daracademyViewModel (chat screen)")
                }
            }
        ),
        productId    = ""
    )
}