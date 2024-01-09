package com.example.daracademy.view.screens.fullScreen.signIn.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alphaspace.screens.common.dropDownMenu.AlphaDropDownMenu
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.variables.firaSansFamily
import com.example.daracademy.model.variables.josefinSansFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.customWhite0
import com.example.daracademy.ui.theme.customWhite4
import com.google.android.gms.common.internal.AccountType
import com.google.android.gms.common.util.AndroidUtilsLight

@Composable
fun CoreSection(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .widthIn(max = 450.dp)
            .padding(start = 35.dp, end = 35.dp)
    ) {
        AccountTypeItem(
            items = listOf("Student" , "Teacher"),
        )
    }

}


@Composable
fun AccountTypeItem(
    items       : List<String>,
    modifier    : Modifier = Modifier,
    menuModifier: Modifier = Modifier
) {

    var expanded by remember{
        mutableStateOf(false)
    }
    var type by remember{
        mutableStateOf("Student")
    }


    Column(
        modifier = modifier
    ) {

        Text(
            text = "Account Type"
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 1.5.dp,
                    color = color1,
                    shape = RoundedCornerShape(12.dp)
                )
                .height(50.dp)
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .background(customWhite0)
                .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)

        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_down_icon),
                contentDescription = null,
                tint = color1,
                modifier = Modifier
                    .size(35.dp)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = type,
                style = NormalTextStyles.TextStyleSZ7.copy(fontFamily = josefinSansFamily),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        AlphaDropDownMenu(
            expanded         = expanded,
            items            = items,
            onClick          = {  type = it  } ,
            onDismissRequest = { expanded = false },
            background       = customWhite0,
            modifier         = Modifier
                .fillMaxWidth()
                .padding(start = 35.dp , end = 35.dp)
                .widthIn(max = 450.dp),
            menuModifier     = Modifier
                .fillMaxWidth()

        )



    }

}

@Preview
@Composable
fun CoreSection_preview() {
    CoreSection()
}