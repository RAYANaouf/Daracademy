package com.example.daracademy.view.screens.fullScreen.signIn.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.alphaspace.screens.common.dropDownMenu.AlphaDropDownMenu
import com.example.alphaspace.screens.common.textFields.AlphaTextField
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.variables.josefinSansFamily
import com.example.daracademy.model.variables.nunitoFamily
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.customBlack3
import com.example.daracademy.ui.theme.customBlack7
import com.example.daracademy.ui.theme.customWhite0
import com.google.android.gms.common.internal.AccountType

@Composable
fun CoreSection(
    email    : String   ,
    password : String   ,
    accountType: String ,
    onChange : (type : String , txt : String)->Unit = {_,_->},
    modifier : Modifier = Modifier
) {




    Column(
        modifier = modifier
            .padding(start = 35.dp, end = 35.dp)
    ) {
        AccountTypeItem(
            items = listOf("Student" , "Teacher"),
            selectedItem = accountType,
            onSelectItem = {
                onChange("accountType" , it)
            }
        )


        Spacer(modifier = Modifier.height(26.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            AlphaTextField(
                text = email,
                textFieldStyle = NormalTextStyles.TextStyleSZ7,
                onValueChange = {
                    onChange("email" , it)
                },
                hint = "your email",
                hintStyle = NormalTextStyles.TextStyleSZ7,
                cursorColor = color1,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(26.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            AlphaTextField(
                text = password,
                textFieldStyle = NormalTextStyles.TextStyleSZ7,
                onValueChange = {
                    onChange("password" , it)
                },
                hint = "your password",
                hintStyle = NormalTextStyles.TextStyleSZ7,
                cursorColor = color1,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
        }

    }

}


@Composable
fun AccountTypeItem(
    items        : List<String>,
    selectedItem : String,
    onSelectItem : (String)->Unit ,
    modifier     : Modifier = Modifier,
) {

    var expanded by remember{
        mutableStateOf(false)
    }


    var textFieldValue by remember{
        mutableStateOf(Size.Zero)
    }


    Column(
        modifier = modifier
    ) {

        Text(
            style = NormalTextStyles.TextStyleSZ7.copy(fontFamily = nunitoFamily , color = customBlack3 , fontWeight = FontWeight.SemiBold),
            text  = "Account Type"
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = color1,
                    shape = RoundedCornerShape(12.dp)
                )
                .height(50.dp)
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .background(customWhite0)
                .onGloballyPositioned {
                    textFieldValue = it.size.toSize()
                }
                .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)

        ) {
            Text(
                text = selectedItem,
                style = NormalTextStyles.TextStyleSZ7.copy(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

//            Spacer(modifier = Modifier.width(6.dp))
//
//
//            Icon(
//                painter = painterResource(id = R.drawable.arrow_down_icon),
//                contentDescription = null,
//                tint = color1,
//                modifier = Modifier
//                    .size(35.dp)
//            )

        }

        AlphaDropDownMenu(
            expanded         = expanded,
            items            = items,
            onClick          = {  onSelectItem(it)  } ,
            onDismissRequest = { expanded = false },
            background       = customWhite0,
            modifier         = Modifier
                .width(with(LocalDensity.current){textFieldValue.width.toDp()}),
            menuModifier     = Modifier
                .width(with(LocalDensity.current){textFieldValue.width.toDp()})

        )

    }

}

@Preview
@Composable
fun CoreSection_preview() {
    CoreSection(
        email = "",
        password = "",
        accountType = "student",
    )
}