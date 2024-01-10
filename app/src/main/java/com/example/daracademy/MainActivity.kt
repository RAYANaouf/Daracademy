package com.example.daracademy

import android.graphics.Color.parseColor
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.objects.userType.user_type
import com.example.daracademy.view.screens.navigationScreen.annees_de_etude_Screen.AnneesDesEtudesScreen
import com.example.daracademy.view.screens.navigationScreen.homeScreen.HomeScreen
import com.example.daracademy.view.screens.navigationScreen.materiauxScreen.MatieresScreen
import com.example.daracademy.model.sealedClasses.screens.Screens
import com.example.daracademy.model.sealedClasses.userType.UserType
import com.example.daracademy.viewModel.DaracademyViewModel
import com.example.daracademy.ui.theme.DaracademyTheme
import com.example.daracademy.ui.theme.customWhite7
import com.example.daracademy.view.components.navigationDrawer.AlphaNavigationDrawer
import com.example.daracademy.view.screens.fullScreen.signIn.SignInScreen
import com.example.daracademy.view.screens.navigationScreen.CousesScreen.CoursesScreen
import com.example.daracademy.view.screens.navigationScreen.chat.ChatScreen
import com.example.daracademy.view.screens.navigationScreen.chatBoxs.ChatBoxsScreen
import com.example.daracademy.view.screens.navigationScreen.formation.FormationScreen
import com.example.daracademy.view.screens.navigationScreen.formations.FormationsScreen
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException


var appUserType : UserType? = null


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window , false)

        // At the top level of your kotlin file:


        setContent {

            DaracademyTheme {

                val context = LocalContext.current
                val navHostController = rememberNavController()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val viewModel : DaracademyViewModel =  viewModel(
                        factory = object : ViewModelProvider.Factory{
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                if (modelClass.isAssignableFrom(DaracademyViewModel::class.java))
                                    return DaracademyViewModel(context , navHostController) as T
                                else
                                    throw IllegalArgumentException("cant create daracademyViewModel")
                            }
                        }
                    )


                    LaunchedEffect(key1 = true ){

                        val anonymInfo = viewModel.dataStoreRepo.getAnonymInfo()

                        if (anonymInfo != null){
                            viewModel.setChatBoxsListener(
                                userId = anonymInfo.id
                            )
                        }

                        appUserType = viewModel.dataStoreRepo.getUserType()


                    }


                    MainScreen(viewModel =  viewModel)
                }
            }
        }
    }
}


@Composable
fun MainScreen(viewModel : DaracademyViewModel) {



    var chatId : String by rememberSaveable {
        mutableStateOf("")
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)


    val context        = LocalContext.current


    ModalNavigationDrawer(
        drawerContent = {
            AlphaNavigationDrawer(
                viewModel     = viewModel,
                drawerState   = drawerState
            )
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                if (viewModel.screenRepo.app_screen != Screens.FormationScreen().root){
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
                navController = viewModel.screenRepo.navController ,
                startDestination = Screens.HomeScreen().root,
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.ime)
            ){


                composable(route = Screens.HomeScreen().root){
                    HomeScreen(
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




                composable(
                    route = "${Screens.ChatBoxsScreen().root}"
                ){
                    ChatBoxsScreen(
                        viewModel  = viewModel,
                        onNavigate = {screen , messageBox ->
                            viewModel.screenRepo.navigate_to_screen(screen = Screens.Chat_Screen().root , params =  arrayOf(messageBox.userId  , messageBox.productId , messageBox.name) )

                        },
                        modifier = Modifier
                            .background(Color(parseColor("#f9f9f9")))
                            .padding(top = paddingValues.calculateTopPadding())
                            .windowInsetsPadding(WindowInsets.navigationBars)
                    )
                }


                composable(
                    route = "${Screens.Chat_Screen().root}/{userId}/{productId}/{name}",
                    arguments = listOf(
                        navArgument(name = "userId"){
                            type = NavType.StringType
                        },
                        navArgument(name = "productId"){
                            type = NavType.StringType
                        },
                        navArgument(name = "name"){
                            type = NavType.StringType
                        }
                    )
                ){navBackStackEntry->
                    ChatScreen(
                        viewModel = viewModel,
                        userId    = navBackStackEntry.arguments?.getString("userId") ?: "",
                        productId = navBackStackEntry.arguments?.getString("productId") ?: "",
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
                        formation = viewModel.formation!!,
                        modifier = Modifier
                            .background(Color(parseColor("#f9f9f9")))
                    )
                }

                composable(
                    route = "${Screens.FormationsScreen().root}"
                ){navBackStackEntry->
                    FormationsScreen(
                        viewModel = viewModel,
                        modifier = Modifier
                            .background(Color(parseColor("#f9f9f9")))
                            .padding(top = paddingValues.calculateTopPadding())
                            .windowInsetsPadding(WindowInsets.navigationBars)
                    )
                }

                composable(
                    route = "${Screens.SignInScreen().root}"
                ){navBackStackEntry->
                    SignInScreen(
                        modifier = Modifier
                            .background(Color(parseColor("#f9f9f9")))
                            .padding(top = paddingValues.calculateTopPadding())
                            .windowInsetsPadding(WindowInsets.navigationBars)
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
