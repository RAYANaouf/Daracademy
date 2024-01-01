package com.example.daracademy.view.screens.homeScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.model.variables.josefinSansFamily
import com.example.daracademy.viewModel.DaracademyViewModel
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.color2
import com.example.daracademy.ui.theme.color3
import com.example.daracademy.ui.theme.customBlack7
import com.example.daracademy.ui.theme.customWhite0
import com.example.daracademy.ui.theme.customWhite2
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.daracademy.model.sealedClasses.screens.Screens
import com.example.daracademyadmin.model.sealedClasses.phaseDesEtudes.PhaseDesEtudes

@Composable
fun HeaderSection(
    viewModel: DaracademyViewModel ,
    onNavigate : (Screens , String)->Unit = {_,_->},
    modifier: Modifier = Modifier
) {

    Column {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
        ) {

            Spacer(modifier = Modifier
                .height(2.dp)
                .weight(1f)
                .background(customBlack7))

            Divider(
                color = customBlack7,
                modifier = Modifier
                    .fillMaxHeight(0.25f)
                    .width(2.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Divider(
                color = customBlack7,
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .width(2.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Divider(
                color = customBlack7,
                modifier = Modifier
                    .fillMaxHeight(0.75f)
                    .width(2.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Divider(
                color = customBlack7,
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .width(2.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Phases de l’étude",
                style = NormalTextStyles.TextStyleSZ6,
                fontFamily = josefinSansFamily,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.width(10.dp))


            Divider(
                color = customBlack7,
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .width(2.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Divider(
                color = customBlack7,
                modifier = Modifier
                    .fillMaxHeight(0.75f)
                    .width(2.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Divider(
                color = customBlack7,
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .width(2.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Divider(
                color = customBlack7,
                modifier = Modifier
                    .fillMaxHeight(0.25f)
                    .width(2.dp)
            )

            Spacer(modifier = Modifier
                .height(2.dp)
                .weight(1f)
                .background(customBlack7))

        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp, bottom = 16.dp)
        ) {

            Spacer(modifier = Modifier.width(20.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color1)
                    .padding(4.dp)
                    .clickable {
                        onNavigate(Screens.AnneesScreen(), PhaseDesEtudes.Primaire().phase)
                    }
            ) {
                Text(
                    text = "Primaire",
                    style = NormalTextStyles.TextStyleSZ7.copy(color = customWhite0),
                    fontFamily = firaSansFamily,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(16.dp))


            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color2)
                    .padding(4.dp)
                    .clickable {
                        onNavigate(Screens.AnneesScreen(), PhaseDesEtudes.CEM().phase)
                    }
            ) {
                Text(
                    text = "C.E.M",
                    style = NormalTextStyles.TextStyleSZ7.copy(color = customWhite0),
                    fontFamily = firaSansFamily,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(16.dp))


            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color3)
                    .clickable {
                        onNavigate(Screens.AnneesScreen(), PhaseDesEtudes.Lycee().phase)
                    }
                    .padding(4.dp)

            ) {
                Text(
                    text = "Lycée",
                    style = NormalTextStyles.TextStyleSZ7.copy(color = customWhite0),
                    fontFamily = firaSansFamily,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(20 .dp))

        }
    }

}


@Preview
@Composable
fun HeaderSection_preview() {

    val context = LocalContext.current

    Surface(
        color = customWhite2
    ) {
        HeaderSection(
            viewModel = viewModel(
                factory = object : ViewModelProvider.Factory{
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        if(modelClass.isAssignableFrom(DaracademyViewModel::class.java))
                            return DaracademyViewModel(context) as T
                        else
                            throw IllegalArgumentException("can't create daracademyViewModel (headerSection)")
                    }
                }
            )
        )
    }

}


