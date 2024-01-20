package com.example.daracademy.view.components.navigationDrawer.anonimNavigationDrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.R
import com.example.daracademy.model.sealedClasses.screens.Screens
import com.example.daracademy.model.sealedClasses.userType.UserType
import com.example.daracademy.ui.theme.customWhite7
import com.example.daracademy.viewModel.DaracademyViewModel
import kotlinx.coroutines.launch

@Composable
fun AlphaNavigationDrawer(
    viewModel     : DaracademyViewModel ,
    drawerState   : DrawerState ,
    modifier      : Modifier = Modifier
) {
    
    val coroutineScope = rememberCoroutineScope()

    ModalDrawerSheet(
        drawerContainerColor = Color.White,
        modifier = modifier
            .widthIn(max = 450.dp)
            .fillMaxWidth(0.75f)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .drawBehind {
                    drawLine(
                        color = customWhite7,
                        strokeWidth = 1f,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height)
                    )
                }
                .clickable {
                    coroutineScope.launch {
                        drawerState.apply {
                            close()
                        }
                    }
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.daracademy),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .width(85.dp)
                    .height(110.dp)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(8f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = 16.dp)
                    .background(customWhite7)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(65.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .clickable {
                        coroutineScope.launch {
                            drawerState.apply {
                                close()
                            }
                        }

                        if (viewModel.dataStoreRepo.userInfo.userType is UserType.AnonymousUser || viewModel.dataStoreRepo.userInfo.userType == null)
                            viewModel.screenRepo.navigate_to_screen(Screens.SignInScreen().root)

                    }
                    .padding(top = 6.dp, bottom = 6.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user_account),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(26.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Account",
                    style = NormalTextStyles.TextStyleSZ7
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = 16.dp, bottom = 16.dp)
                    .height(1.dp)
                    .background(customWhite7)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(65.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .clickable {
                        coroutineScope.launch {
                            drawerState.apply {
                                close()
                            }
                        }
                    }
                    .padding(top = 6.dp, bottom = 6.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.support),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(26.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Support",
                    style = NormalTextStyles.TextStyleSZ7
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(65.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .clickable {

                        viewModel.screenRepo.navigate_to_screen(Screens.FormationsScreen().root)


                        coroutineScope.launch {
                            drawerState.apply {
                                close()
                            }
                        }
                    }
                    .padding(top = 6.dp, bottom = 6.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.formation),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(26.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Formations",
                    style = NormalTextStyles.TextStyleSZ7
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(65.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .clickable {
                        coroutineScope.launch {
                            drawerState.apply {
                                close()
                            }
                        }
                    }
                    .padding(top = 6.dp, bottom = 6.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.post),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(26.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Posts",
                    style = NormalTextStyles.TextStyleSZ7
                )
            }


            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = 16.dp, bottom = 16.dp)
                    .height(1.dp)
                    .background(customWhite7)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(65.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .clickable {
                        coroutineScope.launch {

                            viewModel.screenRepo.navigate_to_screen(Screens.ChatBoxsScreen().root)

                            drawerState.apply {
                                close()
                            }

                        }
                    }
                    .padding(top = 6.dp, bottom = 6.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.live_chat),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(26.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Chats",
                    style = NormalTextStyles.TextStyleSZ7
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = 16.dp, bottom = 16.dp)
                    .height(1.dp)
                    .background(customWhite7)
            )


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(65.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .clickable {
                        coroutineScope.launch {
                            drawerState.apply {
                                close()
                            }
                        }
                    }
                    .padding(top = 6.dp, bottom = 6.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.about_us),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(26.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "About us",
                    style = NormalTextStyles.TextStyleSZ7
                )
            }



            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(65.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .clickable {
                        coroutineScope.launch {
                            drawerState.apply {
                                close()
                            }
                        }
                    }
                    .padding(top = 6.dp, bottom = 6.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.development),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(26.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "developpeur",
                    style = NormalTextStyles.TextStyleSZ7
                )
            }

        }

    }
    
}

@Preview
@Composable
fun NavigationDrawer_preview() {

    val context = LocalContext.current
    val navHostController = rememberNavController()

    AlphaNavigationDrawer(
        viewModel = viewModel(
            factory = object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(DaracademyViewModel::class.java)){
                        return DaracademyViewModel(context , navHostController) as T
                    }
                    else
                        throw IllegalArgumentException("cant create DaracademyViewModel AlphaNavDrawer")
                }
            }
        ),
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
}