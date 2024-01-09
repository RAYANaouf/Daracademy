package com.example.daracademyadmin.repo.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.daracademy.model.dataClasses.ChatInfo
import com.example.daracademy.model.objects.userType.user_type
import com.example.daracademy.model.sealedClasses.userType.UserType
import kotlinx.coroutines.flow.first

class DataStoreRepo {

    private val context : Context

    // At the top level of your kotlin file:
    private val dataStoreInstance  : DataStore<Preferences>



    constructor(context: Context ){
        this.dataStoreInstance = com.example.daracademy.model.dataStore.dataStore.getInstance(context)
        this.context  = context
    }


    suspend fun getUserType() : UserType{
        val type   = dataStoreInstance.data.first()[dataStoreKeys.Key_anonymeId]

        val app_user_type = when(type){
            user_type.teacher_user -> UserType.TeacherUser()
            user_type.student_user -> UserType.StudentUser()
            else -> UserType.AnonymousUser()
        }

        return app_user_type
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