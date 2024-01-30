package com.example.daracademy.view.screens.navigationScreen.teacherHome.components.ActsRow

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.daracademy.R
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.customWhite0
import com.example.daracademy.view.screens.navigationScreen.teacherHome.components.headerSection.actItem


@Composable
fun acts(
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
    ) {
        actItem(
            img = R.drawable.schedule,
            txt = "Schedules",
            modifier = Modifier
                .widthIn(max = 200.dp)
                .weight(3f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 1.5.dp,
                    color = color1,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(customWhite0)
        )

        Spacer(
            modifier = Modifier
                .width(32.dp)
        )

        actItem(
            img = R.drawable.blackboard,
            txt = "Rooms",
            modifier = Modifier
                .widthIn(max = 200.dp)
                .weight(3f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 1.5.dp,
                    color = color1,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(customWhite0)
        )
    }

}