package com.example.daracademy.view.screens.navigationScreen.homeScreen

import android.app.Activity
import android.graphics.Paint.Join
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.daracademy.view.screens.navigationScreen.homeScreen.component.AcademyStage
import com.example.daracademy.view.screens.navigationScreen.homeScreen.component.HeaderSection
import com.example.daracademy.view.screens.navigationScreen.homeScreen.component.PostItem
import com.example.daracademy.viewModel.DaracademyViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.daracademy.model.dataClasses.ChatInfo
import com.example.daracademy.model.dataClasses.Formation
import com.example.daracademy.model.dataClasses.MessageBox
import com.example.daracademy.model.sealedClasses.screens.Screens
import com.example.daracademy.ui.theme.color1
import com.example.daracademy.ui.theme.color3
import com.example.daracademy.ui.theme.customWhite2
import com.example.daracademy.ui.theme.customWhite4
import com.example.daracademy.view.screens.navigationScreen.homeScreen.dialogs.AddChatFeatureDialog
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel     : DaracademyViewModel ,
    modifier      : Modifier = Modifier
) {



    val window  = LocalView.current.context as Activity
    val context = LocalContext.current

    LaunchedEffect(key1 = window){
        window.window.apply {
            statusBarColor = Color.White.toArgb()
        }

    }



    var coroutineScope = rememberCoroutineScope()


    var show_dialog by remember{
        mutableStateOf(false)
    }
    var postId      by remember {
        mutableStateOf("")
    }


    AddChatFeatureDialog(
        show        = show_dialog,
        onDismiss   = {
            show_dialog = false
        },
        onDoneClick = {name->
            val result : Boolean

            if (name == ""){
                Toast.makeText(context , "enter your name" , Toast.LENGTH_LONG).show()
                result = false
            }
            else{
                val id = System.currentTimeMillis().toString()

                coroutineScope.launch {
                    viewModel.dataStoreRepo.insertAnonymInfo(ChatInfo(id = id , name = name))
                }

                show_dialog = false

                viewModel.screenRepo.navigate_to_screen(screen = Screens.Chat_Screen().root , params = arrayOf(id,postId,name))

                result      = false
            }

            result
        },
        modifier    = Modifier
            .fillMaxWidth(0.8f)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    )


    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {

        item {
            Spacer(modifier = Modifier.height(16.dp))

            HeaderSection(
                viewModel = viewModel,
                onNavigate = { screen , phase->
                    viewModel.screenRepo.navigate_to_screen(screen = Screens.AnneesScreen().root , params = arrayOf(phase))

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp, max = 200.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            AcademyStage(
                onClick = {
                    viewModel.formation = it
                    viewModel.screenRepo.navigate_to_screen(screen = Screens.FormationScreen().root)

                },
                formations = viewModel.formations,
                modifier = Modifier
            )

        }


        items(viewModel.posts){
            Spacer(modifier = Modifier.height(16.dp))

            PostItem(
                post = it,
                onChatClick = { _postId->
                    postId = _postId
                    coroutineScope.launch {
                        var anonymInfo : ChatInfo? = null
                        var existence   = false

                        anonymInfo = viewModel.dataStoreRepo.getAnonymInfo()
                        if (anonymInfo == null){
                            show_dialog = true
                            return@launch
                        }


                        /******/
                        /******/

//                            existence = viewModel.dataStoreRepo.isProductSaved(_postId)
                        viewModel.getAllMessageBoxs().forEach {
                            if (it.productId == _postId)
                                existence = true
                        }
                        if (!existence){
                            viewModel.createChatBox(anonymInfo  , _postId)
                            viewModel.dataStoreRepo.saveProduct(_postId)
                            existence = true
                        }

                        /*****/
                        /*****/



                        if (existence && anonymInfo != null){
                            viewModel.screenRepo.navigate_to_screen(screen = Screens.Chat_Screen().root , anonymInfo?.id ?: "" , postId , anonymInfo?.name ?: "" )
                        }

                    }



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

@Preview
@Composable
fun HomeScreen_preview() {

    val context = LocalContext.current
    val navHostController = rememberNavController()

    Surface(
        color = customWhite2
    ) {
        HomeScreen(
            viewModel = viewModel(
                factory = object : ViewModelProvider.Factory{
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        if(modelClass.isAssignableFrom(DaracademyViewModel::class.java))
                            return DaracademyViewModel(context , navHostController ) as T
                        else
                            throw IllegalArgumentException("can't create daracademyViewModel (home screen)")
                    }
                }
            ),
        )
    }
}