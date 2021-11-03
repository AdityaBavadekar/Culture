package com.adityaamolbavadekar.android.apps.culture

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager

class ConfigureTheme {
    fun onCreate(TAG: String, activity: Context) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(activity)
        try {
            when (prefs.getString("theme", "3")) {
                "1" -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                "2" -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                "3" -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            }
        } catch (e: Exception) {
        }

    }


}
