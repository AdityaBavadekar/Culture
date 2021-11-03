package com.adityaamolbavadekar.android.apps.culture

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class LauncherActivity : AppCompatActivity() {


    private val normalTimeOut: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        ConfigureTheme().onCreate(TAG,this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_launcher)
        normalBehaviour()
    }
    private fun normalBehaviour() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, normalTimeOut)
    }

    companion object {
        const val TAG = "LauncherActivity"
    }

}
