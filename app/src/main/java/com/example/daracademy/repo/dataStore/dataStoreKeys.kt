package com.example.daracademyadmin.repo.dataStore

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

object dataStoreKeys {

    //
    val Key_accountType     = stringPreferencesKey("accountType")

    val Key_userId          = stringPreferencesKey("userId")
    val Key_userName        = stringPreferencesKey("userName")
    val Key_userEmail       = stringPreferencesKey("userEmail")
    val Key_userImage       = stringPreferencesKey("userImage")



    val Key_anonymeId     = stringPreferencesKey("anonymeId")
    val Key_anonymeName   = stringPreferencesKey("anonymeName")
    val Key_postIds       = stringSetPreferencesKey("postIds")

}