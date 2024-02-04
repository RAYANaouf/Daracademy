package com.example.daracademy.view.screens.navigationScreen.formation.component.timeLine

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.dataClasses.TimeLine
import com.example.daracademy.model.variables.josefinSansFamily
import com.example.daracademy.model.variables.nunitoFamily
import com.example.daracademy.ui.theme.backgroundLight
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.color2
import com.example.daracademy.ui.theme.color3
import com.example.daracademy.ui.theme.customBlack4
import com.example.daracademy.ui.theme.customBlack5
import com.example.daracademy.view.screens.navigationScreen.formation.component.targetedSection.Item
import com.example.daracademy.view.screens.navigationScreen.formation.component.timeLine.dialogs.point_dialog
import com.mxalbert.sharedelements.SharedElement

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
            modifier = Modifier
                .padding(start = 20.dp, end = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.width(20.dp))

            StartPointItem(
                passed = true
            )

            PointItem(
                txt    = "learn Kotlin basics",
                index  = 1,
                even   = (1%2 == 0),
                passed = true
            )

            PointItem(
                txt = "get familiar with android studio",
                index = 2,
                even = (2%2 == 0),
                passed = true
            )

            PointItem(
                txt = "build ui with jetpack compose",
                index = 3,
                even = (3%2 == 0),
                passed = false
            )

            PointItem(
                txt = "make your app alive with firebase",
                index = 4,
                even = (4%2 == 0),
                passed = false
            )

            EndPointItem(
                even = (5%2 == 0),
                passed = false
            )


            Spacer(modifier = Modifier.width(16.dp))
        }

        Spacer(modifier = Modifier.height(12.dp))

        phases(modifier = Modifier.padding(start = 16.dp))
    }


}


@Composable
fun PointItem(
    txt     : String,
    index   : Int,
    even    : Boolean,
    passed  : Boolean,
    modifier: Modifier = Modifier
) {

    var descSize by remember{
        mutableStateOf(Size.Zero)
    }

    var show_point_dialog by remember {
      mutableStateOf(false)
    }

    //dialogs
    point_dialog(
        show = show_point_dialog,
        onDismiss = {
            show_point_dialog = false
        }
    )

    Column(
        modifier = modifier
    ){

        if (even){
            descShape(
                "$txt",
                modifier = Modifier
                    .onGloballyPositioned {
                        descSize = it.size.toSize()
                    }
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        show_point_dialog = true
                    }
            )

            TriangleShape(
                modifier = Modifier
                    .padding(start = (10 + 5).dp)
                    .offset(y = (-1.5).dp)

            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {

                Divider(
                    color = if(passed) color1 else color2,
                    modifier = Modifier
                        .height(if (passed) 2.dp else 1.dp)
                        .width(10.dp)
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(26.dp)
                        .clip(CircleShape)
                        .border(
                            width = if (passed) 2.dp else 1.dp,
                            color = if (passed) color1 else color2,
                            shape = CircleShape
                        )
                        .background(Color.White)
                ) {
                    Text(text = "$index")
                }

                Divider(
                    color = if(passed) color1 else color2,
                    modifier = Modifier
                        .height(if (passed) 2.dp else 1.dp)
                        .width(with(LocalDensity.current) { descSize.width.toDp() - 20.dp })
                )
            }
        }
        else{

            Spacer(modifier = Modifier.height(with(LocalDensity.current){descSize.height.toDp() + 16.dp + 8.dp}))


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {

                Divider(
                    color = if(passed) color1 else color2,
                    modifier = Modifier
                        .height(if (passed) 2.dp else 1.dp)
                        .width(10.dp)
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(26.dp)
                        .clip(CircleShape)
                        .border(
                            width = if (passed) 2.dp else 1.dp,
                            color = if (passed) color1 else color2,
                            shape = CircleShape
                        )
                ) {
                    Text(text = "$index")
                }

                Divider(
                    color = if(passed) color1 else color2,
                    modifier = Modifier
                        .height(if (passed) 2.dp else 1.dp)
                        .width(with(LocalDensity.current) { descSize.width.toDp() - 20.dp })
                )
            }


            Spacer(modifier = Modifier.height(8.dp))

            TriangleShape(
                modifier = Modifier
                    .padding(start = (10 + 5).dp)
                    .rotate(180f)
                    .offset(y = (-1.5).dp)
                    .zIndex(1f)
            )

            descShape(
                "$txt",
                modifier = Modifier
                    .onGloballyPositioned {
                        descSize = it.size.toSize()
                    }
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        show_point_dialog = true
                    }
            )
        }

    }


}

@Composable
fun StartPointItem(
    passed    : Boolean ,
    modifier  : Modifier = Modifier
) {

    Column(
        modifier = modifier
    ){

        descShape(
            "Start",
            modifier = Modifier
                .padding(start = 16.dp)
        )

        TriangleShape(
            modifier = Modifier
                .padding(start = 25.dp)
                .offset(y = (-1).dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

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
                        width = if (passed) 2.dp else 1.dp,
                        color = if (passed) color1 else color2
                    )
            ) {
                Text(text = "0")
            }

            Divider(
                color = if(passed) color1 else color2,
                modifier = Modifier
                    .height(if (passed) 2.dp else 1.dp)
                    .width(90.dp)
            )
        }

    }


}

@Composable
fun EndPointItem(
    even   : Boolean,
    passed : Boolean,
    modifier: Modifier = Modifier
) {


    var descSize by remember{
        mutableStateOf(Size.Zero)
    }

    Column(
        horizontalAlignment = Alignment.End,
        modifier = modifier
    ){

        if (even){
            descShape(
                "End",
                modifier = Modifier
                    .padding(end = 16.dp)
            )

            TriangleShape(
                modifier = Modifier
                    .padding(end = 25.dp)
                    .offset(y = (-1).dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {


                Divider(
                    color = if (passed) color1 else color2,
                    modifier = Modifier
                        .height(if (passed) 2.dp else 1.dp)
                        .width(90.dp)
                )


                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(26.dp)
                        .border(
                            width = if (passed) 2.dp else 1.dp,
                            color = if (passed) color1 else color2
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
        else{

            Spacer(modifier = Modifier.height(with(LocalDensity.current){descSize.height.toDp() + 16.dp + 8.dp}))


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {


                Divider(
                    color = if (passed) color1 else color2,
                    modifier = Modifier
                        .height(if (passed) 2.dp else 1.dp)
                        .width(90.dp)
                )


                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(26.dp)
                        .border(
                            width = if (passed) 2.dp else 1.dp,
                            color = if (passed) color1 else color2
                        )
                ) {
                    Text(text = "5")
                }

                Spacer(
                    modifier = Modifier
                        .width(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            TriangleShape(
                modifier = Modifier
                    .padding(end = 25.dp)
                    .offset(y = (1.5).dp)
                    .rotate(180f)
                    .zIndex(1f)
            )

            descShape(
                "End",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .onGloballyPositioned {
                        descSize = it.size.toSize()
                    }
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
            .background(Color.White)
            .padding(8.dp)
    ) {
        Text(
            text = "$txt",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

}


@Composable
fun TriangleShape(
    modifier: Modifier = Modifier
) {

    Canvas(
        modifier = modifier
            .size(16.dp)
            .background(Color.Transparent),
        onDraw = {
            // Draw the green triangle
            val path = Path().apply {
                moveTo(size.width/2, size.height)
                lineTo(0f , 0f)
                lineTo(size.width, 0f)
                close()
            }

            drawPath(
                path = path,
                color =  Color.White,
            )

            drawLine(
                color = color1,
                strokeWidth = 1.dp.toPx(),
                start = Offset(0f , 0f),
                end   = Offset(size.width/2 , size.height )
            )

            drawLine(
                color = color1,
                strokeWidth = 1.dp.toPx(),
                start = Offset(size.width , 0f),
                end   = Offset(size.width/2 , size.height )
            )


        }
    )


}


@Composable
fun phases(
    modifier : Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {

        Text(
            text = "Phases",
            style = NormalTextStyles.TextStyleSZ7.copy(fontFamily = josefinSansFamily , color = color1),
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .padding(start = 26.dp)
        ) {
            phase(
                txt = "Learn Kotlin basics.",
                checked = true
            )

            Spacer(modifier = Modifier.height(4.dp))

            phase(
                txt = "Get familiar with android studio.",
                checked = true
            )

            Spacer(modifier = Modifier.height(4.dp))

            phase(
                txt = "build ui jetpack compose compose.",
                checked = false
            )

            Spacer(modifier = Modifier.height(4.dp))

            phase(
                txt = "make your app alive with firebase.",
                checked = false
            )
        }

    }

}

@Composable
fun phase(
    checked : Boolean,
    txt     : String ,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){
        Icon(
            painter  = painterResource(id = if(checked) R.drawable.checked_box else R.drawable.unchecked_box ),
            contentDescription = null,
            tint     = if (checked) color1 else color2,
            modifier = Modifier
                .size(16.dp)
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
fun TriangleShape_preview() {
    TriangleShape()
}


@Preview
@Composable
fun TimeLine_prev() {
    TimeLine()
}