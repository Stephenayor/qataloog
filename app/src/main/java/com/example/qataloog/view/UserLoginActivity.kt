package com.example.qataloog.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.qataloog.R
import com.example.qataloog.databinding.ActivityUserLoginBinding

class UserLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserLoginBinding
    private lateinit var userEmail: String
    private lateinit var userPassword: String
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.sign_in_nav_host)
    }

}