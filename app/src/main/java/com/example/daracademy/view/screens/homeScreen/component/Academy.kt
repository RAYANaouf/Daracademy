package com.example.daracademy.view.screens.homeScreen.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.dataClasses.Formation
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.model.variables.josefinSansFamily
import com.example.daracademy.viewModel.DaracademyViewModel
import com.example.daracademy.ui.theme.color2
import com.example.daracademy.ui.theme.customBlack0
import com.example.daracademy.ui.theme.customBlack3
import com.example.daracademy.ui.theme.customWhite7

@Composable
fun AcademyStage(
    viewModel  : DaracademyViewModel,
    onClick    : (Formation)->Unit ={},
    modifier   : Modifier = Modifier,
    formations : List<Formation>
) {



    val context = LocalContext.current



    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {


            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text       = "Formations",
                style      = NormalTextStyles.TextStyleSZ6,
                fontFamily = firaSansFamily,
                fontWeight = FontWeight.Medium,
                modifier   = Modifier
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            Icon(
                painter            = painterResource(id = R.drawable.add_icon),
                contentDescription = null,
                tint = customWhite7,
                modifier           = Modifier
                    .size(20.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))
        }

        Spacer(modifier = Modifier.height(6.dp))

        LazyRow(
            modifier = Modifier
                .height(230.dp)
        ) {

            item {

                Spacer(modifier = Modifier.width(16.dp))

            }

            items(formations){
                AcademyStageItem(
                    formation = it,
                    modifier = Modifier
                        .width(160.dp)
                        .clickable {
                            onClick(it)
                        }
                )

                Spacer(modifier = Modifier.width(16.dp))
            }

        }
    }


}




@Composable
fun AcademyStageItem(
    formation : Formation,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = if (formation.imgs.isEmpty()) painterResource(id = R.drawable.photo_error) else rememberAsyncImagePainter(model = formation.imgs[0]),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(160.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = customWhite7,
                    shape = RoundedCornerShape(12.dp)
                )
        )

//        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = formation.name,
            style = NormalTextStyles.TextStyleSZ8.copy(customBlack0),
            fontFamily = josefinSansFamily,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = formation.desc,
            style = NormalTextStyles.TextStyleSZ9.copy(customBlack3),
            fontFamily = josefinSansFamily,
            fontWeight = FontWeight.Normal,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

    }
}



@Preview
@Composable
fun AcademyStage_preview() {
    AcademyStage(
        viewModel = viewModel(
            factory = object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(DaracademyViewModel::class.java))
                        return DaracademyViewModel() as T
                    else
                        throw IllegalArgumentException("cant create DaracademyViewModel (Academy stage)")
                }
            }
        ),
        formations = emptyList()
    )
}





/*

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

*/