package com.fady.instgramclone.application

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.fady.instgramclone.data.preferences.AppLocalDataSource
import com.fady.instgramclone.data.preferences.AppLocalDataSource.FILE_PREFERENCES
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        var sharedPreferencesData: SharedPreferences? = null
    }

    override fun onCreate() {
        super.onCreate()
        AppLocalDataSource.initialize(PreferenceManager.getDefaultSharedPreferences(this))
        sharedPreferencesData = getSharedPreferences(FILE_PREFERENCES, MODE_PRIVATE)

    }

}