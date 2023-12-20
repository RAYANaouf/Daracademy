package com.example.daracademy.view.screens.homeScreen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.daracademy.view.screens.homeScreen.component.AcademyStage
import com.example.daracademy.view.screens.homeScreen.component.HeaderSection
import com.example.daracademy.view.screens.homeScreen.component.PostItem
import com.example.daracademy.viewModel.DaracademyViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.daracademy.model.dataClasses.Formation
import com.example.daracademy.model.dataClasses.MessageBox
import com.example.daracademy.model.sealedClasses.screens.Screens
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.color3
import com.example.daracademy.ui.theme.customWhite2
import com.example.daracademy.ui.theme.customWhite4
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(
    viewModel     : DaracademyViewModel = viewModel(),
    navController : NavController,
    onChat        : (String)->Unit = {},
    modifier      : Modifier = Modifier
) {

    val formations = viewModel.formations
    val posts      = viewModel.posts


    val window = LocalView.current.context as Activity

    LaunchedEffect(key1 = window){
        window.window.apply {
            statusBarColor = Color.White.toArgb()
        }
    }

    val swipeState = rememberSwipeRefreshState(isRefreshing = viewModel.isLoading.collectAsState().value)

    SwipeRefresh(
        state = swipeState ,
        onRefresh = {
                    viewModel.refresh()
        },
        indicator = { state, refreshTrigger ->  
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger ,
                backgroundColor = color1,
                contentColor = color3
            )
        }
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {

            item {
                Spacer(modifier = Modifier.height(16.dp))

                HeaderSection(
                    viewModel = viewModel,
                    onNavigate = { screen , phase->
                             navController.navigate("${Screens.AnneesScreen().root}/${phase}")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 200.dp, max = 200.dp)
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                AcademyStage(
                    viewModel = viewModel,
                    onClick = {
                        viewModel.formation = it
                        navController.navigate(Screens.FormationScreen().root)
                    },
                    formations = formations,
                    modifier = Modifier
                )

            }


            items(posts){
                Spacer(modifier = Modifier.height(16.dp))

                PostItem(
                    post = it,
                    onChatClick = { chatId->
                        navController.navigate("${Screens.Chat_Screen().root}/$chatId")
                    },
                    modifier = Modifier
                        .padding(start = 16.dp , end = 16.dp)
                )
            }




            item {
                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(modifier = Modifier
                        .clip(CircleShape)
                        .background(customWhite4)
                        .size(16.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))
            }






        }
    }
}

@Preview
@Composable
fun HomeScreen_preview() {
    Surface(
        color = customWhite2
    ) {
        HomeScreen(
            navController = rememberNavController()
        )
    }
}