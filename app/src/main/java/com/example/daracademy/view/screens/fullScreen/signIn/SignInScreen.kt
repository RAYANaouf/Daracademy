package com.example.daracademy.view.screens.fullScreen.signIn

import android.app.Activity
import android.graphics.Color.parseColor
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.daracademy.model.sealedClasses.Errors.Errors
import com.example.daracademy.model.sealedClasses.screens.Screens
import com.example.daracademy.view.screens.fullScreen.signIn.components.CoreSection
import com.example.daracademy.view.screens.fullScreen.signIn.components.DoneSection
import com.example.daracademy.view.screens.fullScreen.signIn.components.SignInTitle
import com.example.daracademy.viewModel.DaracademyViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.jvm.Throws

@Composable
fun SignInScreen(
    viewModel: DaracademyViewModel,
    modifier : Modifier = Modifier
) {

    val window = LocalView.current.context as Activity

    LaunchedEffect(key1 = window){
        window.window.apply {
            statusBarColor     = Color(parseColor("#f9f9f9")).toArgb()
            navigationBarColor = Color(parseColor("#f9f9f9")).toArgb()
        }
    }
    val context = LocalContext.current

    var loading     by remember{
        mutableStateOf(false)
    }

    var email       by remember{
        mutableStateOf("")
    }
    var password    by remember{
        mutableStateOf("")
    }
    var accountType by remember{
        mutableStateOf("student")
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {


        SignInTitle(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        CoreSection(
            email       = email,
            password    = password,
            accountType = accountType,
            onChange    = {type,txt->
                when(type){
                    "email"->{
                        email = txt
                    }
                    "password"->{
                        password = txt
                    }
                    "accountType"->{
                        accountType = txt
                    }
                }
            },
            modifier    = Modifier
        )

        Spacer(modifier = Modifier.height(26.dp))

        DoneSection(
            loading = loading,
            onClick = {
                loading = true
                viewModel.viewModelScope.launch {
                    loading = true
                    if (accountType == "Teacher"){
                        viewModel.teacherSignIn(
                            email    = email,
                            password = password,
                            onFailureCallBack = {
                                Toast.makeText(context , "$accountType \n " + if (it is Errors) "${it.msg}" else it.message , Toast.LENGTH_LONG ).show()
                                loading = false
                            },
                            onSuccessCallBack = {
                                viewModel.viewModelScope.launch {
                                    viewModel.dataStoreRepo.saveSignIn(it)
                                }

                                viewModel.screenRepo.navigate_to_screen(Screens.HomeScreen().root)
                                Toast.makeText(context , "hello teacher" , Toast.LENGTH_LONG ).show()
                                loading = false
                            }
                        )
                    }
                    else{
                        viewModel.studentSignIn(
                            email    = email,
                            password = password,
                            onFailureCallBack = {
                                Toast.makeText(context , if (it is Errors) "${it.msg}" else it.message , Toast.LENGTH_LONG ).show()
                                loading = false
                            },
                            onSuccessCallBack = {
                                viewModel.viewModelScope.launch {
                                    viewModel.dataStoreRepo.saveSignIn(it)
                                }
                                viewModel.screenRepo.navigate_to_screen(Screens.HomeScreen().root)
                                Toast.makeText(context , "hello student" , Toast.LENGTH_LONG ).show()
                                loading = false
                            }
                        )
                    }

                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 35.dp, end = 35.dp)
        )






    }

}

@Preview
@Composable
fun SignInScreen_preview() {
    val context = LocalContext.current
    val navHostController = rememberNavController()
    SignInScreen(
        viewModel = viewModel(
            factory = object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(DaracademyViewModel::class.java))
                        return DaracademyViewModel(context , navHostController) as T
                    else
                        throw IllegalArgumentException("cant create viewModel (signIn Screen)")
                }
            }
        )
    )
}