package com.example.daracademy.model.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

object dataStore {

    private val Context.dataStore  : DataStore<Preferences> by preferencesDataStore(name = "dataStore")

    fun getInstance(context: Context): DataStore<Preferences> {
        return context.dataStore
    }


}