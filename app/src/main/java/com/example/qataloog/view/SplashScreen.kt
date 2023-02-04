package com.example.qataloog.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.qataloog.MainActivity
import com.example.qataloog.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val preferences = getSharedPreferences("login", Context.MODE_PRIVATE)

        Handler().postDelayed({
            if (preferences.contains("isUserLogin")) {
                val mainActivityIntent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(mainActivityIntent)
                finish()
            } else {
                val loginActivityIntent = Intent(this@SplashScreen, UserLoginActivity::class.java)
                startActivity(loginActivityIntent)
                finish()
            }
        }, 3000)

    }
}