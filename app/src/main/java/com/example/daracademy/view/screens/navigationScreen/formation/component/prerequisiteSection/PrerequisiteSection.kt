package com.example.daracademy.view.screens.navigationScreen.formation.component.prerequisiteSection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.variables.josefinSansFamily
import com.example.daracademy.model.variables.nunitoFamily
import com.example.daracademy.ui.theme.customBlack5

@Composable
fun PrerequisiteSection(
    prerequisite : List<String>,
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier
    ) {

        Text(
            text = "Prerequisite",
            style = NormalTextStyles.TextStyleSZ5.copy(fontFamily = josefinSansFamily , color = customBlack5),
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .padding(start = 26.dp)
        ) {
            Item(txt = "Pc with 8Gb of RAM.")

            Spacer(modifier = Modifier.height(4.dp))

            Item(txt = "just it, nothing else.")
        }

    }

}


@Composable
fun Item(
    txt : String ,
    modifier : Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {

        Image(
            painter = painterResource(id = R.drawable.square_point),
            contentDescription = null,
            modifier = Modifier
                .size(12.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "$txt",
            style = NormalTextStyles.TextStyleSZ8.copy(fontFamily = nunitoFamily ),
        )

    }
}


@Preview
@Composable
fun PrerequisiteSection_preview() {
    PrerequisiteSection(
        emptyList()
    )
}