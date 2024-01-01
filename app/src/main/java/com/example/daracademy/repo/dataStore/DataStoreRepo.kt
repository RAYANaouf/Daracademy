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
import kotlinx.coroutines.flow.first

class DataStoreRepo {

    private val context : Context

    // At the top level of your kotlin file:
    private val Context.dataStore  : DataStore<Preferences> by preferencesDataStore(name = "dataStore")


    constructor(context: Context , dataStore: DataStore<Preferences>){
        this.context  = context
    }

    suspend fun getAnonymInfo() : ChatInfo?{
        val id   = context.dataStore.data.first()[dataStoreKeys.Key_anonymeId]
        val name = context.dataStore.data.first()[dataStoreKeys.Key_anonymeName]
        if (id == null || name == null){
            return null
        }

        return ChatInfo(id = id , name = name)
    }

    suspend fun insertAnonymInfo(chatInfo : ChatInfo) {

        context.dataStore.edit {
            it[dataStoreKeys.Key_anonymeId] = chatInfo.id
            it[dataStoreKeys.Key_anonymeName]   = chatInfo.name
        }
    }

    suspend fun isProductSaved(productId : String) : Boolean{
        val postIdsSet   = context.dataStore.data.first()[dataStoreKeys.Key_postIds] ?: return false

        return postIdsSet.contains(productId)
    }

    suspend fun saveProduct(productId : String) {

        context.dataStore.edit {
            var mutableSet    = it[dataStoreKeys.Key_postIds]?.toMutableSet() ?: emptySet<String>().toMutableSet()
            mutableSet?.add(productId)

            it[dataStoreKeys.Key_postIds]   = mutableSet
        }
    }

}