package com.example.daracademy.view.screens.navigationScreen.formation.component.infoSection

import android.content.ClipData.Item
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.dataClasses.Formation
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.ui.theme.color2
import com.example.daracademy.ui.theme.customWhite6

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InfoSection(
    formation: Formation = Formation(),
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier
    ) {

        FlowRow() {

            if (formation.certaficate){
                Item(
                    icon = R.drawable.certificate,
                    text = "Certificate",
                )

                Spacer(modifier = Modifier.width(30.dp))

            }

            Item(
                icon = R.drawable.students_icon,
                text = "Students : ${formation.students}",
            )


        }


    }

}

@Composable
fun Item(
    icon    : Int,
    text    : String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            painter            = painterResource(id = icon),
            contentDescription = null,
            tint               = customWhite6,
            modifier           = Modifier
                .size(20.dp)
        )

        Spacer(
            modifier = Modifier.width(8.dp)
        )

        Text(
            text   = text,
            style  = NormalTextStyles.TextStyleSZ9
        )
    }
}


@Preview
@Composable
fun InfoSection_preview() {
    InfoSection()
}