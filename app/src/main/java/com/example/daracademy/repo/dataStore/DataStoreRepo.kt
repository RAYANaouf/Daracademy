package com.example.daracademyadmin.repo.dataStore

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.daracademy.model.dataClasses.ChatInfo
import com.example.daracademy.model.dataClasses.Student
import com.example.daracademy.model.dataClasses.Teacher
import com.example.daracademy.model.dataClasses.UserInfo
import com.example.daracademy.model.objects.userType.user_type
import com.example.daracademy.model.sealedClasses.userType.UserType
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DataStoreRepo {

    private val context : Context

    // At the top level of your kotlin file:
    private val dataStoreInstance  : DataStore<Preferences>

    var userInfo  by mutableStateOf(UserInfo())
        private set


    constructor(context: Context ){
        this.dataStoreInstance = com.example.daracademy.model.dataStore.dataStore.getInstance(context)
        this.context  = context
    }


    suspend fun getUserInfo() : UserInfo {

        if(userInfo.id == ""){
            val type     = dataStoreInstance.data.first()[dataStoreKeys.Key_accountType]
            val id       = dataStoreInstance.data.first()[dataStoreKeys.Key_userId]
            val name     = dataStoreInstance.data.first()[dataStoreKeys.Key_userName]
            val email    = dataStoreInstance.data.first()[dataStoreKeys.Key_userEmail]
            val image    = dataStoreInstance.data.first()[dataStoreKeys.Key_userImage]

            var userType : UserType
            when(type){
                user_type.teacher_user ->{
                    userType = UserType.TeacherUser()
                }
                user_type.student_user ->{
                    userType = UserType.StudentUser()
                }
                else -> {
                    userType = UserType.AnonymousUser()
                }
            }

            this.userInfo = UserInfo(userType = userType, id = id ?: "" , name = name ?: "" , email = email ?: "" , image = image ?: "")
        }



        return userInfo

    }


    suspend fun saveSignIn(teacher : Teacher){
        dataStoreInstance.edit {
            it[dataStoreKeys.Key_accountType] = user_type.teacher_user
            it[dataStoreKeys.Key_userId]      = teacher.id
            it[dataStoreKeys.Key_userName]    = teacher.name
            it[dataStoreKeys.Key_userEmail]   = teacher.email
            it[dataStoreKeys.Key_userImage]   = teacher.photo

        }

        this.userInfo = UserInfo(userType = UserType.TeacherUser() , id = teacher.id ?: "" , name = teacher.name ?: "" , email = teacher.email ?: "" , image = teacher.photo ?: "")
    }
    suspend fun saveSignIn(student: Student){
        dataStoreInstance.edit {
            it[dataStoreKeys.Key_accountType] = user_type.student_user
            it[dataStoreKeys.Key_userId]      = student.id
            it[dataStoreKeys.Key_userName]    = student.name
            it[dataStoreKeys.Key_userEmail]   = student.email
            it[dataStoreKeys.Key_userImage]   = student.photo
        }
        this.userInfo = UserInfo(userType = UserType.StudentUser() , id = student.id ?: "" , name = student.name ?: "" , email = student.email ?: "" , image = student.photo ?: "")
    }

    suspend fun deconnect(){
        dataStoreInstance.edit {
            it[dataStoreKeys.Key_accountType] = user_type.anonymous_user
            it[dataStoreKeys.Key_userId]      = ""
            it[dataStoreKeys.Key_userName]    = ""
            it[dataStoreKeys.Key_userEmail]   = ""
            it[dataStoreKeys.Key_userImage]   = ""
        }
        this.userInfo = UserInfo(userType = UserType.AnonymousUser())
    }


    suspend fun getAnonymInfo() : ChatInfo?{
        val id   = dataStoreInstance.data.first()[dataStoreKeys.Key_userId]
        val name = dataStoreInstance.data.first()[dataStoreKeys.Key_userName]
        if (id == null || name == null){
            return null
        }

        return ChatInfo(id = id , name = name)
    }

    suspend fun insertAnonymInfo(chatInfo : ChatInfo) {

        dataStoreInstance.edit {

            it[dataStoreKeys.Key_accountType] = user_type.anonymous_user
            it[dataStoreKeys.Key_userId]      = chatInfo.id
            it[dataStoreKeys.Key_userName]    = chatInfo.name

        }
    }

    suspend fun isProductSaved(productId : String) : Boolean{
        val postIdsSet   = dataStoreInstance.data.first()[dataStoreKeys.Key_postIds] ?: return false

        return postIdsSet.contains(productId)
    }

    suspend fun saveProduct(productId : String) {

        dataStoreInstance.edit {
            var mutableSet    = it[dataStoreKeys.Key_postIds]?.toMutableSet() ?: emptySet<String>().toMutableSet()
            mutableSet?.add(productId)

            it[dataStoreKeys.Key_postIds]   = mutableSet
        }
    }

}