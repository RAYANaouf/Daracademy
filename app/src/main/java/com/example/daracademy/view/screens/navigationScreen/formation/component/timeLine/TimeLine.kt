package com.example.daracademy.view.screens.navigationScreen.formation.component.timeLine

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.dataClasses.TimeLine
import com.example.daracademy.model.variables.josefinSansFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.customBlack5

@Composable
fun TimeLine(
    timeLine : TimeLine,
    modifier : Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {
        Text(
            text = "TimeLine",
            style = NormalTextStyles.TextStyleSZ5.copy(
                fontFamily = josefinSansFamily,
                color = customBlack5
            ),
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
        ) {
            StartPointItem()

            PointItem(
                txt = "learn Kotlin basics",
                index = 1
            )

            PointItem(
                txt = "get familiar with android studio",
                index = 2
            )

            PointItem(
                txt = "build ui with jetpack compose",
                index = 3
            )

            PointItem(
                txt = "make your app alive with firebase",
                index = 4
            )

            EndPointItem()
        }
    }


}


@Composable
fun PointItem(
    txt     : String,
    index   : Int,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ){
        descShape(
            "$txt",
            modifier = Modifier
                .padding(start = 20.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {

            Divider(
                color = color1,
                modifier = Modifier
                    .height(1.dp)
                    .width(50.dp)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(26.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = color1,
                        shape = CircleShape
                    )
            ) {
                Text(text = "$index")
            }

            Divider(
                color = color1,
                modifier = Modifier
                    .height(1.dp)
                    .width(100.dp)
            )
        }

    }

}

@Composable
fun StartPointItem(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ){
        descShape(
            "Start",
            modifier = Modifier
                .padding(start = 20.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(
                modifier = Modifier
                    .width(20.dp)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(26.dp)
                    .border(
                        width = 1.dp,
                        color = color1
                    )
            ) {
                Text(text = "0")
            }

            Divider(
                color = color1,
                modifier = Modifier
                    .height(1.dp)
                    .width(90.dp)
            )
        }

    }


}

@Composable
fun EndPointItem(
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.End,
        modifier = modifier
    ){

        descShape(
            "End",
            modifier = Modifier
                .padding(end = 20.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {


            Divider(
                color = color1,
                modifier = Modifier
                    .height(1.dp)
                    .width(90.dp)
            )


            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(26.dp)
                    .border(
                        width = 1.dp,
                        color = color1
                    )
            ) {
                Text(text = "5")
            }

            Spacer(
                modifier = Modifier
                    .width(20.dp)
            )
        }





    }

}


@Composable
fun descShape(
    txt : String ,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = color1,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(8.dp)
    ) {
        Text(text = "$txt")
    }

}


@Preview
@Composable
fun TimeLine_prev() {
    TimeLine()
}