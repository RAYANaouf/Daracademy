package com.example.daracademy.view.screens.annees_de_etude_Screen

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.variables.les_annees_d_etude.annees_de_primaire
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.customWhite0
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.daracademy.model.sealedClasses.screens.Screens
import com.example.daracademy.model.variables.les_annees_d_etude.annees_de_C_E_M
import com.example.daracademy.model.variables.les_annees_d_etude.annees_de_lycee
import com.example.daracademy.viewModel.DaracademyViewModel
import com.example.daracademy.ui.theme.color2
import com.example.daracademy.ui.theme.color3
import com.example.daracademyadmin.model.sealedClasses.phaseDesEtudes.PhaseDesEtudes

@Composable
fun AnneesDesEtudesScreen(
    navController: NavController,
    viewModel  : DaracademyViewModel = viewModel(),
    modifier   : Modifier = Modifier,
    phase : String,
) {


    val window = LocalView.current.context as Activity

    LaunchedEffect(key1 = window){
        window.window.apply {
            statusBarColor = Color.White.toArgb()
        }
    }

    var color  = remember {

        if (phase == PhaseDesEtudes.Primaire().phase){
            color1
        }
        else if(phase == PhaseDesEtudes.CEM().phase)
        {
            color2
        }
        else{
            color3
        }
    }
    var annees = remember {

        if (phase == PhaseDesEtudes.Primaire().phase){
            annees_de_primaire
        }
        else if(phase == PhaseDesEtudes.CEM().phase)
        {
            annees_de_C_E_M
        }
        else{
            annees_de_lycee
        }

    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    )
    {

        annees.forEachIndexed { index, annee ->
            Spacer(modifier = Modifier.height(26.dp))

            if (index != annees.lastIndex){
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = color)
                        .clickable {
                            navController.navigate("${Screens.MateriauxScreen().root}/${phase}/${annee.id}")
                        }
                        .padding(top = 4.dp, bottom = 4.dp, start = 10.dp, end = 10.dp)
                ) {
                    Text(
                        text = annee.name,
                        style = NormalTextStyles.TextStyleSZ5.copy(color = customWhite0),
                        fontFamily = firaSansFamily,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

            }
            else
            {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = color)
                        .clickable {
                            navController.navigate("${Screens.MateriauxScreen().root}/${phase}/${annee.id}")
                        }
                        .padding(top = 4.dp, bottom = 4.dp, start = 10.dp, end = 10.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.mortarboard),
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.CenterStart)
                            .rotate(-90f)
                    )

                    Text(
                        text = annee.name,
                        style = NormalTextStyles.TextStyleSZ5.copy(color = customWhite0),
                        fontFamily = firaSansFamily,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Image(
                        painter = painterResource(id = R.drawable.mortarboard),
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.CenterEnd)
                            .rotate(90f)
                    )
                }
            }



        }
        



        Spacer(modifier = Modifier.height(26.dp))


    }
}


@Preview
@Composable
fun AnneesDesEtudesScreen_preview() {
    AnneesDesEtudesScreen(
        navController = rememberNavController(),
        phase = ""
    )
}