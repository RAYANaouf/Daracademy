package com.example.daracademy

import android.graphics.Color.parseColor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.grafic.material.loadingEffect.LottieAnimation_loading_1
import com.example.bigsam.grafic.material.topBar.AlphaTopBar2
import com.example.daracademy.model.dataClasses.Teacher
import com.example.daracademy.model.dataClasses.UserInfo
import com.example.daracademy.view.screens.navigationScreen.annees_de_etude_Screen.AnneesDesEtudesScreen
import com.example.daracademy.view.screens.navigationScreen.homeScreen.HomeScreen
import com.example.daracademy.view.screens.navigationScreen.materiauxScreen.MatieresScreen
import com.example.daracademy.model.sealedClasses.screens.Screens
import com.example.daracademy.model.sealedClasses.userType.UserType
import com.example.daracademy.viewModel.DaracademyViewModel
import com.example.daracademy.ui.theme.DaracademyTheme
import com.example.daracademy.ui.theme.backgroundLight
import com.example.daracademy.view.components.navigationDrawer.anonimNavigationDrawer.AlphaNavigationDrawer
import com.example.daracademy.view.components.navigationDrawer.studentNavigationDrawer.Student_AlphaNavigationDrawer
import com.example.daracademy.view.components.navigationDrawer.teacherNavigationDrawer.Teacher_AlphaNavigationDrawer
import com.example.daracademy.view.screens.fullScreen.signIn.SignInScreen
import com.example.daracademy.view.screens.navigationScreen.CousesScreen.CoursesScreen
import com.example.daracademy.view.screens.navigationScreen.chat.ChatScreen
import com.example.daracademy.view.screens.navigationScreen.chatBoxs.ChatBoxsScreen
import com.example.daracademy.view.screens.navigationScreen.formation.FormationScreen
import com.example.daracademy.view.screens.navigationScreen.formations.FormationsScreen
import com.example.daracademy.view.screens.navigationScreen.teacherCourses.TeacherCourses
import com.example.daracademy.view.screens.navigationScreen.teacherHome.TeacherHome
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import java.lang.IllegalArgumentException




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

                        viewModel.dataStoreRepo.getUserInfo()

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
            if (viewModel.dataStoreRepo.userInfo.userType is UserType.AnonymousUser){
                AlphaNavigationDrawer(
                    viewModel     = viewModel,
                    drawerState   = drawerState
                )
            }
            else if (viewModel.dataStoreRepo.userInfo.userType is UserType.AnonymousUser){
                Student_AlphaNavigationDrawer(
                    viewModel     = viewModel,
                    drawerState   = drawerState
                )
            }
            else{
                Teacher_AlphaNavigationDrawer(
                    viewModel     = viewModel,
                    drawerState   = drawerState
                )
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                if (viewModel.screenRepo.app_screen != Screens.FormationScreen().root && viewModel.screenRepo.app_screen != Screens.SignInScreen().root ){
                    if (viewModel.dataStoreRepo.userInfo.userType is UserType.TeacherUser){

                        var user : UserInfo? by rememberSaveable{
                            mutableStateOf(null)
                        }

                        LaunchedEffect(key1 = true ){
                            user = viewModel.dataStoreRepo.getUserInfo()
                        }


                        AlphaTopBar2(
                            img = rememberAsyncImagePainter(model = user , placeholder = painterResource(id = R.drawable.teacher) , fallback = painterResource(id = R.drawable.teacher) , error = painterResource(id = R.drawable.teacher)),
                            txt = user?.name ?: "teacher name" ,
                            onMenuClick = {
                                viewModel.viewModelScope.launch {
                                    viewModel.dataStoreRepo.deconnect()
                                }
                            },
                            modifier = Modifier
                                .windowInsetsPadding(WindowInsets.statusBars)
                        )
                    }
                    else{

                        AlphaTopBar2(
                            img = painterResource(id = R.drawable.daracademy),
                            txt = "Daracademy" ,
                            modifier = Modifier
                                .windowInsetsPadding(WindowInsets.statusBars)
                        )
                    }
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


                    if (viewModel.dataStoreRepo.userInfo.userType is UserType.AnonymousUser){
                        HomeScreen(
                            viewModel = viewModel,
                            onChat = {
                                chatId = it
                            },
                            modifier = Modifier
                                .fillMaxSize()
                                .background(backgroundLight)
                                .padding(top = paddingValues.calculateTopPadding())
                                .windowInsetsPadding(WindowInsets.navigationBars)
                        )
                    }
                    else if(viewModel.dataStoreRepo.userInfo.userType is UserType.TeacherUser){
                        TeacherHome(
                            viewModel = viewModel,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(backgroundLight)
                                .padding(top = paddingValues.calculateTopPadding())
                                .windowInsetsPadding(WindowInsets.navigationBars)
                        )
                    }

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
                    route = "${Screens.TeacherCoursesScreen().root}",
//                    arguments = listOf(
//                        navArgument("teacherId"){
//                            type = NavType.StringType
//                        }
//                    )
                ){navBackStackEntry->
                    TeacherCourses(
                        viewModel = viewModel,
//                        teacherId = navBackStackEntry.arguments?.getString("teacherId") ?: "",
                        modifier  = Modifier
                            .background(Color(parseColor("#f9f9f9")))
                            .padding(top = paddingValues.calculateTopPadding())
                            .windowInsetsPadding(WindowInsets.navigationBars)
                    )
                }

                composable(
                    route = "${Screens.CoursesScreen().root}/{matiereId}",
                    arguments = listOf(
                        navArgument("matiereId"){
                            type = NavType.StringType
                        }
                    )
                ){navBackStackEntry->
                    CoursesScreen(
                        viewModel = viewModel,
                        matiereId = navBackStackEntry.arguments?.getString("matiereId") ?: "",
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
                        viewModel = viewModel,
                        modifier = Modifier
                            .background(Color(parseColor("#f9f9f9")))
                            .fillMaxHeight()
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
