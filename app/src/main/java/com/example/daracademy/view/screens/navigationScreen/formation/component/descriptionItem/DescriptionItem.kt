package com.example.daracademy.view.screens.navigationScreen.formation.component.descriptionItem

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.variables.josefinSansFamily
import com.example.daracademy.ui.theme.color2
import com.example.daracademy.ui.theme.customBlack5
import com.example.daracademy.ui.theme.customBlack6
import com.example.daracademy.ui.theme.customBlack7
import com.example.daracademy.ui.theme.customWhite7

@Composable
fun DescriptionItem(
    modifier    : Modifier = Modifier,
    description : String
) {

    var show by remember{
        mutableStateOf(false)
    }

    val animatedHeight by animateDpAsState(
        targetValue = if (show) 1500.dp else 85.dp,
        animationSpec = tween(
            durationMillis = 1000
        )
        ,
        label = "animated height"
    )


    Column(
        modifier = modifier
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text  = "Description ",
                style = NormalTextStyles.TextStyleSZ5.copy(fontFamily = josefinSansFamily , color = customBlack5),
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text  = "See All",
                style = NormalTextStyles.TextStyleSZ9.copy(fontFamily = josefinSansFamily , color = color2),
                modifier = Modifier
                    .clickable {
                        show = !show
                    }
            )
        }

        Box(
            modifier = Modifier
                .heightIn(max = animatedHeight)
                .pointerInput(Unit) {
                    detectTapGestures {
                        show = !show
                    }
                }

        ){
            Text(
                text = description,
                style = NormalTextStyles.TextStyleSZ9.copy(fontFamily = josefinSansFamily , lineHeight = 18.sp),
                overflow = TextOverflow.Ellipsis
            )

            if (!show){
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    Color.White
                                )
                            )
                        )
                        .align(Alignment.BottomCenter)
                )
            }
        }
    }

}


@Preview
@Composable
fun DescriptionItem_preview() {
    DescriptionItem(
        description = "Learning programming offers numerous benefits that extend far beyond merely writing code. Here's a 20-line paragraph highlighting the significance of learning programming:\n" +
                "\n" +
                "In our digital era, the mastery of programming is a gateway to innovation and problem-solving. Learning to code isn't solely about crafting software; it cultivates a mindset that fosters creativity, logic, and critical thinking. It empowers individuals to shape the future by crafting solutions to complex problems. Programming imparts the ability to automate tasks, streamline processes, and create efficient solutions across various industries, from healthcare to entertainment. Moreover, it cultivates resilience and adaptability, encouraging continuous learning in a constantly evolving technological landscape. Understanding programming languages enhances communication skills, enabling individuals to articulate ideas with precision and clarity. It encourages collaboration and teamwork by fostering a shared language and approach to problem-solving. Furthermore, programming skills open doors to diverse career opportunities, spanning software development, data analysis, artificial intelligence, and beyond. It equips individuals to comprehend and harness technology, enabling them to become informed citizens in our increasingly digital society. Ultimately, programming isn't merely a skill; it's a catalyst for innovation, empowerment, and driving positive change in the world."
    )
}