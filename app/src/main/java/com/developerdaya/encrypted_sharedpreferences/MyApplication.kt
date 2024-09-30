package com.developerdaya.encrypted_sharedpreferences

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        PreferenceManager.init(this)
    }
}
