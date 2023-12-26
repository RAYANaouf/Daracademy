package com.example.daracademy

import android.app.Activity
import android.graphics.Color.parseColor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bigsam.grafic.material.topBar.AlphaTopBar2
import com.example.daracademy.view.screens.annees_de_etude_Screen.AnneesDesEtudesScreen
import com.example.daracademy.view.screens.homeScreen.HomeScreen
import com.example.daracademy.view.screens.materiauxScreen.MatieresScreen
import com.example.daracademy.model.dataClasses.MessageBox
import com.example.daracademy.model.sealedClasses.screens.Screens
import com.example.daracademy.viewModel.DaracademyViewModel
import com.example.daracademy.ui.theme.DaracademyTheme
import com.example.daracademy.view.screens.CousesScreen.CoursesScreen
import com.example.daracademy.view.screens.chat.ChatScreen
import com.example.daracademy.view.screens.chatBoxs.ChatBoxsScreen
import com.example.daracademy.view.screens.formationScreen.FormationScreen
import java.lang.IllegalArgumentException


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window , false)



        setContent {


            DaracademyTheme {

                val context = LocalContext.current

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val viewModel : DaracademyViewModel =  viewModel(
                        factory = object : ViewModelProvider.Factory{
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                if (modelClass.isAssignableFrom(DaracademyViewModel::class.java))
                                    return DaracademyViewModel(context) as T
                                else
                                    throw IllegalArgumentException("cant create daracademyViewModel")
                            }
                        }
                    )


                    MainScreen(viewModel =  viewModel)
                }
            }
        }
    }
}


@Composable
fun MainScreen(viewModel : DaracademyViewModel) {


    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()


    var chatId : String by rememberSaveable {
        mutableStateOf("")
    }


    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()


    DismissibleNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {

        }
    ) {
        Scaffold(
            topBar = {
                if (navBackStackEntry?.destination?.route != Screens.FormationScreen().root){
                    AlphaTopBar2(
                        img = painterResource(id = R.drawable.daracademy),
                        txt = "Daracademy" ,
                        modifier = Modifier
                            .windowInsetsPadding(WindowInsets.statusBars)
                    )
                }
            },
            modifier = Modifier
        ) {paddingValues ->

            NavHost(
                navController = navController ,
                startDestination = Screens.HomeScreen().root,
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.ime)
            ){

                composable(route = Screens.HomeScreen().root){
                    HomeScreen(
                        navController = navController,
                        viewModel = viewModel,
                        onChat = {
                            chatId = it
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(top = paddingValues.calculateTopPadding())
                            .windowInsetsPadding(WindowInsets.navigationBars)
                    )
                }

                composable(
                    route = "${Screens.AnneesScreen().root}/{phase}",
                    arguments = listOf(
                        navArgument(name = "phase"){
                            type = NavType.StringType
                        }
                    ),
                ){navBackStackEntry->
                    AnneesDesEtudesScreen(
                        navController = navController,
                        viewModel = viewModel,
                        phase     = navBackStackEntry.arguments?.getString("phase") ?: "",
                        modifier  = Modifier
                            .padding(top = paddingValues.calculateTopPadding())
                            .windowInsetsPadding(WindowInsets.navigationBars)
                    )
                }

                composable(
                    route = "${Screens.MateriauxScreen().root}/{phase}/{annee}",
                    arguments = listOf(
                        navArgument("phase"){
                            type = NavType.StringType
                        },
                        navArgument("annee"){
                            type = NavType.StringType
                        }
                    )
                ){navBackStackEntry->

                    MatieresScreen(
                        viewModel     = viewModel,
                        navController = navController,
                        phase         = navBackStackEntry.arguments?.getString("phase") ?: "",
                        annee         = navBackStackEntry.arguments?.getString("annee") ?: "",
                        modifier      = Modifier
                            .padding(top = paddingValues.calculateTopPadding())
                            .windowInsetsPadding(WindowInsets.navigationBars)
                    )

                }


                composable(
                    route = "${Screens.CoursesScreen().root}/{phase}/{annee}/{matiere}",
                    arguments = listOf(
                        navArgument("phase"){
                            type = NavType.StringType
                        },
                        navArgument("annee"){
                            type = NavType.StringType
                        },
                        navArgument("matiere"){
                            type = NavType.StringType
                        }
                    )
                ){navBackStackEntry->
                    CoursesScreen(
                        viewModel = viewModel,
                        phase = navBackStackEntry.arguments?.getString("phase") ?: "",
                        annee = navBackStackEntry.arguments?.getString("annee") ?: "",
                        matiere = navBackStackEntry.arguments?.getString("matiere") ?: "",
                        modifier = Modifier
                            .background(Color(parseColor("#f9f9f9")))
                            .padding(top = paddingValues.calculateTopPadding())
                            .windowInsetsPadding(WindowInsets.navigationBars)
                    )
                }




                composable(route = Screens.ChatBox_Screen().root){
                    ChatBoxsScreen(
                        viewModel = viewModel,
                        onNavigate = {screen ->
                            navController.navigate(screen.root)
                            viewModel.setAppScreen(screen)

                        },
                        modifier = Modifier
                            .background(Color(parseColor("#f9f9f9")))
                            .padding(top = paddingValues.calculateTopPadding())
                            .windowInsetsPadding(WindowInsets.navigationBars)
                    )
                }


                composable(
                    route = "${Screens.Chat_Screen().root}/{chatId}/{name}",
                    arguments = listOf(
                        navArgument(name = "chatId"){
                            type = NavType.StringType
                        },
                        navArgument(name = "name"){
                            type = NavType.StringType
                        }
                    )
                ){navBackStackEntry->
                    ChatScreen(
                        viewModel = viewModel,
                        chatId    = navBackStackEntry.arguments?.getString("chatId") ?: "",
                        name      = navBackStackEntry.arguments?.getString("name")   ?: "",
                        modifier = Modifier
                            .background(Color(parseColor("#f9f9f9")))
                            .padding(top = paddingValues.calculateTopPadding())
                            .windowInsetsPadding(WindowInsets.navigationBars)
                    )
                }


                composable(
                    route = "${Screens.FormationScreen().root}"
                ){navBackStackEntry->
                    FormationScreen(
                        viewModel = viewModel,
                        navController = navController,
                        formation = viewModel.formation!!,
                        modifier = Modifier
                            .background(Color(parseColor("#f9f9f9")))
                    )
                }





            }


        }
    }

}


@Preview
@Composable
fun MainScreen_preview() {

    MainScreen(
        viewModel = viewModel()
    )
}
