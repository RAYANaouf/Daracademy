package com.example.daracademy.view.screens.formations

import android.content.ClipData.Item
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.dataClasses.Formation
import com.example.daracademy.model.dataClasses.Teacher
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.model.variables.josefinSansFamily
import com.example.daracademy.ui.theme.customBlack5
import com.example.daracademy.ui.theme.customBlack7
import com.example.daracademy.ui.theme.customWhite4
import com.example.daracademy.ui.theme.customWhite7
import com.example.daracademy.view.material.lottie.LottieAnimation_loadingImage
import com.example.daracademy.viewModel.DaracademyViewModel


@Composable
fun FormationsScreen(
    viewModel     : DaracademyViewModel,
    navController : NavController = rememberNavController(),
    modifier      : Modifier = Modifier
) {

    val context = LocalContext.current

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 16.dp , bottom = 26.dp , start = 16.dp , end = 16.dp),
        modifier = modifier
    ){

        if ( viewModel.formations.isEmpty()){

        }
        else{
            items(viewModel.formations){
                Item(
                    formation = it,
                    viewModel = viewModel,
                    elevation = 2.dp,
                    modifier  = Modifier
                        .fillMaxWidth()
                )
            }
        }

    }

}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Item(
    formation   : Formation,
    viewModel   : DaracademyViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    color       : Color    = Color.White,
    shape       : Shape    = RoundedCornerShape(12.dp),
    imageShape  : Shape    = RoundedCornerShape(8.dp),
    elevation   : Dp       = 0.dp,
    imgModifier : Modifier = Modifier,
    modifier    : Modifier = Modifier
) {

    var teacher : Teacher? = null
    var context = LocalContext.current

    LaunchedEffect(key1 = true ){
        viewModel.getTeacherById(
            teacherId = formation.teacher,
            onSuccessCallBack = {
                teacher = it
            },
            onFailureCallBack = {
                Toast.makeText(context , "fail" , Toast.LENGTH_LONG).show()
            }
        )
    }


    Surface(
        color            = color,
        shape            = shape ,
        shadowElevation  = elevation,
        modifier         = modifier
    ) {

        Row(
//            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {


            val context = LocalContext.current

            val img = rememberAsyncImagePainter(
                model = formation.imgs[0] ,
            )

            val state = img.state
            
            
            //img
            Box(
                modifier = imgModifier
                    .size(100.dp)
                    .aspectRatio(1f)
                    .clip(imageShape)
                    .border(
                        width = 1.5.dp,
                        color = customWhite4,
                        shape = imageShape
                    )
            ){

                Image(
                    painter =  img ,
                    contentDescription = formation.name ,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(10f)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    LottieAnimation_loadingImage()
                }


            }


            Spacer(modifier = Modifier.width(10.dp))


            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = formation.name,
                    style = NormalTextStyles.TextStyleSZ6.copy(color = customBlack5 , fontFamily = josefinSansFamily),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.money_icon),
                            contentDescription = null,
                            tint = customWhite7 ,
                            modifier = Modifier
                                .size(20.dp)
                        )

//                        Spacer(modifier = Modifier.width(2.dp))

                        Text(
                            text = "${if (formation.prix == 0) "for free" else formation.prix}",
                            style = NormalTextStyles.TextStyleSZ9.copy(color = customWhite7 , fontFamily = josefinSansFamily , lineHeight = 16.sp),
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    if (formation.certaficate){
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                painter = painterResource(id = R.drawable.certificate),
                                contentDescription = null,
                                tint = customWhite7 ,
                                modifier = Modifier
                                    .size(20.dp)
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = "Certificate",
                                style = NormalTextStyles.TextStyleSZ9.copy(color = customWhite7 , fontFamily = josefinSansFamily , lineHeight = 16.sp),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.teacher_inline),
                            contentDescription = null,
                            tint = customWhite7 ,
                            modifier = Modifier
                                .size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(2.dp))

                        Text(
                            text = if (teacher?.name == null)  "...."  else  "${teacher?.name}",
                            style = NormalTextStyles.TextStyleSZ9.copy(color = customWhite7 , fontFamily = josefinSansFamily , lineHeight = 16.sp),
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }



                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = formation.desc,
                    style = NormalTextStyles.TextStyleSZ9.copy(color = customWhite7 , fontFamily = josefinSansFamily , lineHeight = 16.sp),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }

    }

}


@Preview
@Composable
fun Item_preview() {
    Item(
        Formation(
            "formation name" ,
            "formation descrition bal bal abal bal abal bal abal bal abal bal abal bal abal bal abal bal abal bal abal bal a",
            "rayan aouf",
            1500,
        )
    )
}


@Preview
@Composable
fun FormationsScreen_preview() {
    val context = LocalContext.current
    FormationsScreen(
        viewModel = viewModel(
            factory = object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(DaracademyViewModel::class.java)){
                        return DaracademyViewModel(context) as T
                    }
                    else
                        throw IllegalArgumentException("creation if daracademyViewModel (formations screen)")
                }
            }
        )
    )
}