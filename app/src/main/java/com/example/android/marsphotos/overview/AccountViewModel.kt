package com.example.android.marsphotos.overview

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.network.JwtData
import com.example.android.marsphotos.network.MarsApi
import com.example.android.marsphotos.network.SignUpData
import kotlinx.coroutines.launch
import retrofit2.Response


class AccountViewModel : ViewModel() {
    private val _account = MutableLiveData<String>()
    val account: MutableLiveData<String>
        get() = _account

    private val _password = MutableLiveData<String>()
    val password: MutableLiveData<String>
        get() = _password

    private val _isSignUp = MutableLiveData<Boolean>()
    val isSignUp: MutableLiveData<Boolean>
        get() = _isSignUp

    init {

    }

    fun onPressButton(context: Context?, user: String, password: String, isSignUp: Boolean) {
        Log.d("TAG", "onPressButton: 15-06 " + isSignUp)
        if (!isSignUp) {
            viewModelScope.launch {
                try {
                    Log.d("TAG", "onPressButton: 14-06 " + user + " " + password)
                    val status: Response<JwtData> = MarsApi.retrofitService.login(user, password)
                    val jwt = status.body()
                    if (status.isSuccessful) {
                        Toast.makeText(context, "Login successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                    Log.d("TAG", "onPressButton: hihihihih " + jwt?.token)
                } catch (e: java.lang.Exception) {
                    Log.d("TAG", "onPressButton: huhuhuuh " + e)
                    Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            viewModelScope.launch {
                try {
                    Log.d("TAG", "onPressButton: 14-06 " + user + " " + password)
                    val status: Response<SignUpData> = MarsApi.retrofitService.signUp(user, password)
                    val jwt = status.body()
                    if (status.isSuccessful) {
                        Toast.makeText(context, "Register successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Register failed", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: java.lang.Exception) {
                    Log.d("TAG", "onPressButton:  resgister huhuhuuh " + e)
                    Toast.makeText(context, "Register failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}