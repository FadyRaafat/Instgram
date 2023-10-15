package com.fady.instgramclone.data.preferences

import android.content.SharedPreferences
import com.fady.instgramclone.application.MyApplication.Companion.sharedPreferencesData
import com.fady.instgramclone.data.models.User
import com.google.gson.Gson

object AppLocalDataSource {

    const val FILE_PREFERENCES = "instgram_prefernce"
    val gson = Gson()
    private var preferences: SharedPreferences? = null

    fun initialize(preferences: SharedPreferences?) {
        AppLocalDataSource.preferences = preferences
    }

    var userProfile: User?
        get() = sharedPreferencesData.processStoredObject(USER_PROFILE)
        set(value) = sharedPreferencesData.storeObject(USER_PROFILE, value)

    fun getUserId() = userProfile?.id ?: 0
}