package com.example.qataloog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.qataloog.model.LoginResponseModel
import com.example.qataloog.repository.UserLoginRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(): ViewModel() {
    @Inject
    lateinit var userLoginRepository: UserLoginRepository

    fun createUserLoginRequest(userEmail: String, userPassword: String): LiveData<LoginResponseModel>{
        return userLoginRepository.createLoginRequest(userEmail, userPassword)
    }
}