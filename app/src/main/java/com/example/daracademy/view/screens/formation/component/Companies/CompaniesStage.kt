package com.example.daracademy.view.screens.formation.component.Companies

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.dataClasses.Company
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.model.variables.josefinSansFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.color2
import com.example.daracademy.ui.theme.color3
import com.example.daracademy.ui.theme.customBlack5
import com.example.daracademy.ui.theme.customBlack6
import com.example.daracademy.ui.theme.customBlack7

@Composable
fun CompaniesStage(
    companies : List<Company>,
    modifier  : Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {

        Text(
            text = "Companies",
            style = NormalTextStyles.TextStyleSZ5.copy(fontFamily = josefinSansFamily , color = customBlack5),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(

        ) {
            companies.forEachIndexed { index, company ->

                CompanyItem(company)

                if ( index != companies.size-1 ){
                    Spacer(modifier = Modifier.width(12.dp))
                }

            }
        }


    }

}


@Composable
fun CompanyItem(
    company : Company,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .width(70.dp)
    ) {
        Image(
            painter            = rememberAsyncImagePainter(model = company.img),
            contentDescription = null ,
            contentScale       = ContentScale.Crop,
            modifier           = Modifier
                .size(65.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = company.name,
            style = NormalTextStyles.TextStyleSZ9.copy(fontFamily = josefinSansFamily),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

}


@Preview
@Composable
fun CompaniesStage_preview() {
    CompaniesStage(
        companies = emptyList()
    )
}