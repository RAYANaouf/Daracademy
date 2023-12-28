package com.example.daracademyadmin.repo.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.daracademy.model.dataClasses.ChatInfo
import kotlinx.coroutines.flow.first

class DataStoreRepo {

    private val context : Context

    // At the top level of your kotlin file:
    private val Context.dataStore  : DataStore<Preferences> by preferencesDataStore(name = "dataStore")


    constructor(context: Context){
        this.context  = context
    }

    suspend fun getAnonymInfo() : ChatInfo?{
        val id   = context.dataStore.data.first()[dataStoreKeys.Key_anonymeId] ?: null
        val name = context.dataStore.data.first()[dataStoreKeys.Key_anonymeName]   ?: null
        if (id == null || name == null){
            return null
        }

        return ChatInfo(id = id!! , name = name!!)
    }

    suspend fun insertAnonymInfo(chatInfo : ChatInfo) {

        context.dataStore.edit {
            it[dataStoreKeys.Key_anonymeId] = chatInfo.id
            it[dataStoreKeys.Key_anonymeName]   = chatInfo.name
        }
    }

}