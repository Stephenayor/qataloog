package com.example.qataloog.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.qataloog.base.BaseFragment
import com.example.qataloog.databinding.FragmentUserSigninBinding
import com.example.qataloog.model.LoginRequestModel
import com.example.qataloog.model.LoginResponseModel
import com.example.qataloog.network.UserLoginApi
import com.example.qataloog.network.UsersRetrofitClientInstance
import com.example.qataloog.viewmodel.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class UserSigninFragment : BaseFragment() {
    private lateinit var binding: FragmentUserSigninBinding
    private lateinit var userEmail: String
    private lateinit var userPassword: String
    @Inject
    lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserSigninBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signInProgressBar.visibility = View.GONE
        binding.textButton.setOnClickListener {
            findNavController().
            navigate(UserSigninFragmentDirections.actionUserSigninFragmentToUserSignUpFragment())
        }
        loginViewModel = qataloogAppComponent.getLoginViewModel()
        binding.userSignInButton.setOnClickListener {
            userEmail = binding.userSignInEmailTextField?.text.toString()
            userPassword = binding.userSignInPassword?.text.toString()
            createLogin()
        }
    }

    private fun createLogin() {
        binding.signInProgressBar.visibility = View.VISIBLE
        loginViewModel.createUserLoginRequest(userEmail, userPassword).observe(viewLifecycleOwner, Observer {
            loginResponse -> if (loginResponse.data?.status == 200){
            val sharedPreferences =
                activity?.getSharedPreferences("login", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            editor?.putBoolean("isUserLogin", true)
            editor?.putString("authToken", loginResponse.data!!.token?.accessToken)
            editor?.putString("Username", loginResponse.data!!.user?.name)
            editor?.apply()
            Toast.makeText(
                activity, "SUCCESSFUL LOGIN",
                Toast.LENGTH_LONG
            ).show()
            binding.signInProgressBar.visibility = View.GONE
            findNavController().
            navigate(UserSigninFragmentDirections.actionUserSigninFragmentToMainActivity())
            }
        })
    }















//    private fun createLoginRequest() {
//        val userLoginApi: UserLoginApi = UsersRetrofitClientInstance.getRetrofitInstance()
//            ?.create(UserLoginApi::class.java)!!
//        val call: Call<LoginResponseModel> = userLoginApi
//            .createUserLoginRequest(LoginRequestModel(userEmail, userPassword))
//        call.enqueue(object : Callback<LoginResponseModel> {
//            override fun onResponse(
//                call: Call<LoginResponseModel>,
//                response: Response<LoginResponseModel>
//            ) {
//                Toast.makeText(
//                    activity, "SUCCESSFUL LOGIN",
//                    Toast.LENGTH_LONG
//                ).show()
//                Log.d("RAW", response.code().toString())
//                if (response.isSuccessful) {
//                    val sharedPreferences =
//                        activity?.getSharedPreferences("login", Context.MODE_PRIVATE)
//                    val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
//                    editor?.putBoolean("isUserLogin", true)
//                    editor?.apply()
//                    findNavController().
//                    navigate(com.example.qataloog.UserSigninFragmentDirections.actionUserSigninFragmentToBooksHomeFragment2())
//                }
//            }
//
//            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
//                Toast.makeText(activity, t.message, Toast.LENGTH_LONG)
//                    .show()
//                t.printStackTrace()
//            }
//
//        })
//    }
}
