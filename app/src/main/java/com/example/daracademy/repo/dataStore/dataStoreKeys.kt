package com.example.daracademyadmin.repo.dataStore

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

object dataStoreKeys {

    val Key_anonymeId     = stringPreferencesKey("anonymeId")
    val Key_anonymeName   = stringPreferencesKey("anonymeName")
    val Key_postIds       = stringSetPreferencesKey("postIds")

}