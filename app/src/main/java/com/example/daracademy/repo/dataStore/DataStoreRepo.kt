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


    //data
    var  appUserType : UserType? by mutableStateOf(null)



    constructor(context: Context ){
        this.dataStoreInstance = com.example.daracademy.model.dataStore.dataStore.getInstance(context)
        this.context  = context
    }


    fun getUserType() : UserType?{

        if (appUserType == null){
            MainScope().launch {
                val type   = dataStoreInstance.data.first()[dataStoreKeys.Key_accountType]

                when(type){
                    user_type.teacher_user ->{
                        appUserType = UserType.TeacherUser()
                    }
                    user_type.student_user ->{
                        appUserType = UserType.StudentUser()
                    }
                    else -> {
                        appUserType = UserType.AnonymousUser()
                    }
                }
            }
        }

        return appUserType
    }


    suspend fun getUserInfo() : Any{

        var id    = dataStoreInstance.data.first()[dataStoreKeys.Key_anonymeId]
        var name  = dataStoreInstance.data.first()[dataStoreKeys.Key_userName]
        var email = dataStoreInstance.data.first()[dataStoreKeys.Key_userEmail]
        var image = dataStoreInstance.data.first()[dataStoreKeys.Key_userImage]


        if (appUserType is UserType.TeacherUser){
            return Teacher(id = id?: "" ,name = name?: "" , email = email?: "" , photo = image?: "")
        }
        else{
            return Student(id = id?: "" , name = name?: "" , email = email?: "" , photo = image?: "")
        }

    }


    suspend fun saveSignIn(teacher : Teacher){
        dataStoreInstance.edit {
            it[dataStoreKeys.Key_accountType] = user_type.teacher_user
            it[dataStoreKeys.Key_anonymeId]   = teacher.id
            it[dataStoreKeys.Key_userName]    = teacher.name
            it[dataStoreKeys.Key_userEmail]   = teacher.email
            it[dataStoreKeys.Key_userImage]   = teacher.photo

        }
        appUserType = UserType.TeacherUser()
    }
    suspend fun saveSignIn(student: Student){
        dataStoreInstance.edit {
            it[dataStoreKeys.Key_accountType] = user_type.student_user
            it[dataStoreKeys.Key_anonymeId]   = student.id
            it[dataStoreKeys.Key_userName]    = student.name
            it[dataStoreKeys.Key_userEmail]   = student.email
            it[dataStoreKeys.Key_userImage]   = student.photo
        }
        appUserType = UserType.StudentUser()
    }


    suspend fun getAnonymInfo() : ChatInfo?{
        val id   = dataStoreInstance.data.first()[dataStoreKeys.Key_anonymeId]
        val name = dataStoreInstance.data.first()[dataStoreKeys.Key_anonymeName]
        if (id == null || name == null){
            return null
        }

        return ChatInfo(id = id , name = name)
    }

    suspend fun insertAnonymInfo(chatInfo : ChatInfo) {

        dataStoreInstance.edit {
            it[dataStoreKeys.Key_anonymeId] = chatInfo.id
            it[dataStoreKeys.Key_anonymeName]   = chatInfo.name
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