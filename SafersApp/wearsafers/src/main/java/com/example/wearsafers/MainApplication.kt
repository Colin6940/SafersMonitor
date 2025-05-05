package com.example.wearsafers

import android.app.Application
import android.content.Intent

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val intent = Intent(this, HeartRateService::class.java)
        startService(intent)
    }
}
