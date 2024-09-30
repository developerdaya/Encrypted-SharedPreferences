package com.developerdaya.encrypted_sharedpreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PreferenceManager.init(this)
        PreferenceManager.saveApiKey("daya123")
        val apiKey = PreferenceManager.getApiKey()
        Log.d("API_KEY", apiKey ?: "No API Key Found")
    }

}