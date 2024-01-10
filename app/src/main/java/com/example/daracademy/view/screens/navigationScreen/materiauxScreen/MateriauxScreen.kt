package com.example.daracademy.view.screens.navigationScreen.materiauxScreen

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.dataClasses.Matiere
import com.example.daracademy.model.sealedClasses.screens.Screens
import com.example.daracademy.model.variables.les_annees_d_etude.matieres_primaire_premiere_annee
import com.example.daracademy.viewModel.DaracademyViewModel
import com.example.daracademy.ui.theme.customWhite0
import com.example.daracademy.ui.theme.customWhite4




@Composable
fun MatieresScreen(
    viewModel : DaracademyViewModel ,
    phase         : String,
    annee         : String,
    modifier      : Modifier = Modifier
) {


    val window = LocalView.current.context as Activity

    LaunchedEffect(key1 = window){
        window.window.apply {
            statusBarColor = Color.White.toArgb()
        }
    }

    viewModel.getAllMatieres(
        phase ,
        annee ,
        onSuccessCallBack = {
//            Toast.makeText(context , "success : ${viewModel.matiere}" , Toast.LENGTH_LONG).show()
        },
        onFailureCallBack = {
//            Toast.makeText(context , "failed : ${it.message}" , Toast.LENGTH_LONG).show()
        }
    )

    LazyVerticalGrid(
        horizontalArrangement = Arrangement.spacedBy(26.dp),
        verticalArrangement = Arrangement.spacedBy(26.dp),
        contentPadding = PaddingValues(top = 26.dp , bottom = 26.dp),
        columns = GridCells.Fixed(2),
        modifier = modifier
            .padding( start = 16.dp , end = 16.dp)
    ){
        items(viewModel.matiere){
            MatieresScreenItem(
                matiere = it,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        viewModel.screenRepo.navigate_to_screen(screen = Screens.CoursesScreen().root , phase , annee , it.name)
                    }
            )
        }

    }

    
}

@Composable
fun MatieresScreenItem(
    matiere : Matiere,
    modifier : Modifier = Modifier
) {

    Surface(
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 2.dp,
            color = customWhite4,
        ),
        color = customWhite0,
        modifier = modifier
            .aspectRatio(1f)
            .heightIn(min = 100.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
        ) {
            Image(
                painter = if (matiere.img>=0) painterResource(id = matieres_primaire_premiere_annee[matiere.img].img) else rememberAsyncImagePainter(model = matiere.imgUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = matiere.name,
                style = NormalTextStyles.TextStyleSZ9,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}




@Preview
@Composable
fun MatieresScreen_preview() {

    val context = LocalContext.current
    val navHostController = rememberNavController()


    MatieresScreen(
        viewModel = viewModel(
            factory = object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if(modelClass.isAssignableFrom(DaracademyViewModel::class.java))
                        return DaracademyViewModel(context , navHostController) as T
                    else
                        throw IllegalArgumentException("can't create daracademyViewModel (MatiereScreen)")
                }
            }
        ),
        phase = "",
        annee = ""
    )
}


@Preview
@Composable
fun MatieresScreenItem_preview() {


    MatieresScreenItem(
        matieres_primaire_premiere_annee[0]
    )
}
