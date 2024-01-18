package com.example.daracademy.viewModel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.daracademy.model.dataClasses.ChatInfo
import com.example.daracademy.model.dataClasses.Course
import com.example.daracademy.model.dataClasses.Formation
import com.example.daracademy.model.dataClasses.Matiere
import com.example.daracademy.model.dataClasses.Message
import com.example.daracademy.model.dataClasses.MessageBox
import com.example.daracademy.model.dataClasses.Post
import com.example.daracademy.model.dataClasses.Student
import com.example.daracademy.model.dataClasses.Teacher
import com.example.daracademy.model.sealedClasses.Errors.Errors
import com.example.daracademy.model.sealedClasses.screens.Screens
import com.example.daracademy.repo.DaracademyRepo
import com.example.daracademy.repo.ScreenRepo
import com.example.daracademyadmin.repo.dataStore.DataStoreRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Delay
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DaracademyViewModel : ViewModel {


    var appScreen : String  by mutableStateOf(Screens.HomeScreen().root)
        private set

    private val _isLading = MutableStateFlow(false)
    val isLoading = _isLading.asStateFlow()

    var formations by mutableStateOf<List<Formation>>(emptyList())
        private set

    var posts by mutableStateOf<List<Post>>(emptyList())
        private set



    val repo : DaracademyRepo = DaracademyRepo()
    val dataStoreRepo : DataStoreRepo
    val screenRepo : ScreenRepo


    var matiere : List<Matiere> by mutableStateOf(emptyList())

    var courses : List<Course> by mutableStateOf(emptyList())


    var formation : Formation? by mutableStateOf(null)



    init {
        refresh_silent()
    }

    constructor(context : Context , navHostController: NavHostController){
        this.dataStoreRepo = DataStoreRepo(context = context )
        this.screenRepo    = ScreenRepo   (navHostController )
    }




    /***************************************************/

    fun refresh_silent(){

        this.getAllFormation(
            onSuccessCallBack = {
                formations = it
            },
            onFailureCallBack = {}
        )

        this.getAllPosts(
            onSuccessCallBack = {
                posts = it
            },
            onFailureCallBack = {}
        )


    }



    fun refresh(){
        _isLading.value = true


        this.getAllFormation(
            onSuccessCallBack = {
                formations = it
                _isLading.value = false
            },
            onFailureCallBack = {
                _isLading.value = false
            }
        )

        this.getAllPosts(
            onSuccessCallBack = {
                posts = it
                _isLading.value = false
            },
            onFailureCallBack = {
                _isLading.value = false
            }
        )


    }

    /***************************************************/



    /****************** screen *************************/

    fun setAppScreen(newScreen : Screens){

            this.appScreen = newScreen.root
    }

    /***************************************************/


    fun getAllFormation(onSuccessCallBack: (List<Formation>) -> Unit = {}, onFailureCallBack: (ex : Exception) -> Unit = {} ){

        this.repo.getAllFormation(
            onSuccessCallBack = {
                onSuccessCallBack(it)
            },
            onFailureCallBack = {
                onFailureCallBack(it)
            }
        )

    }

    fun getAllPosts(onSuccessCallBack: (List<Post>) -> Unit = {}, onFailureCallBack: (ex : Exception) -> Unit = {} ){

        this.repo.getAllPosts(
            onSuccessCallBack = {
                onSuccessCallBack(it)
            },
            onFailureCallBack = {
                onFailureCallBack(it)
            }
        )

    }


    //chat feature ****************************************************





    fun getAllMessageBoxs( ) : List<MessageBox>{
        return repo.chatBoxs
    }

    fun setChatBoxsListener(userId: String , onSuccessCallBack: (List<MessageBox>) -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}) {
        this.repo.setChatBoxsListener(userId, onSuccessCallBack, onFailureCallBack)
    }


        fun getBoxMessages(userId : String , productId : String , onSuccessCallBack: (List<Message>) -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}  ){
        repo.getBoxMessages(userId , productId , onSuccessCallBack, onFailureCallBack)
    }

    fun sendMsg(userId : String , productId: String , newMassage : Message, onSuccessCallBack: () -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}  ){
        repo.sendMsg(userId, productId, newMassage, onSuccessCallBack, onFailureCallBack)
    }

    fun createChatBox(chatInfo: ChatInfo , productId: String , onSuccessCallBack: () -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}  ) {
        repo.createChatBox(chatInfo, productId, onSuccessCallBack, onFailureCallBack)
    }



        fun getAllMatieres(phase : String, annee : String, onSuccessCallBack: (List<Matiere>) -> Unit, onFailureCallBack: (ex: Exception) -> Unit) {

        matiere = emptyList()

        this.repo.getAllMatieres(
            phase,
            annee,
            onSuccessCallBack = {
                matiere = it
                onSuccessCallBack(it)
            },
            onFailureCallBack
        )

    }

    fun getCourses(phase : String, annee : String, matiere : String, onSuccessCallBack: (List<Course>) -> Unit, onFailureCallBack: (ex: Exception) -> Unit) {

        courses = emptyList()

        this.repo.getCourses(
            phase,
            annee,
            matiere,
            onSuccessCallBack = {
                courses = it
                onSuccessCallBack(it)
            },
            onFailureCallBack
        )

    }

    fun getTeacherById(teacherId : String, onSuccessCallBack: (Teacher?) -> Unit, onFailureCallBack: (ex: Exception) -> Unit){

        this.repo.getTeacherById(teacherId , onSuccessCallBack , onFailureCallBack)

    }

    fun isTeacherExist(teacherId : String): Teacher?{
        return  repo.isTeacherExist(teacherId)
    }

    fun teacherSignIn(email : String, password : String, onSuccessCallBack: ( Teacher ) -> Unit = {}, onFailureCallBack: (exp: Exception) -> Unit = {}){
        repo.teacherSignIn(email , password , onSuccessCallBack , onFailureCallBack)
    }

    fun studentSignIn(email : String, password : String, onSuccessCallBack: ( Student ) -> Unit = {}, onFailureCallBack: (exp: Exception) -> Unit = {}){
        repo.studentSignIn(email , password , onSuccessCallBack , onFailureCallBack)
    }


}