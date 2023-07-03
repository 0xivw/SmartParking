package com.example.android.marsphotos.overview

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.Constant
import com.example.android.marsphotos.network.*
import com.example.android.marsphotos.sharePref.SharedPreferencesHelper
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
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

    private val _isLoginSuccess = MutableLiveData<Boolean>()
    val isLoginSuccess: MutableLiveData<Boolean>
        get() = _isLoginSuccess

    init {

    }

    fun onPressButton(context: Context?, user: String, password: String, isSignUp: Boolean){
        Log.d("TAG", "onPressButton: 15-06 " + isSignUp)
        val sharedPreferencesHelper = SharedPreferencesHelper(context!!)
        if (!isSignUp) {
            viewModelScope.launch {
                try {
                    Log.d("TAG", "onPressButton: 14-06 " + user + " " + password)
                    val status: Response<JwtData> = MarsApi.retrofitService.login(user, password)
                    val jwt = status.body()
                    sharedPreferencesHelper.saveString("token", jwt?.token)
                    val token :String? = jwt?.token
                    Constant.JWT_TOKEN = token
                    Log.d("TAG", "onPressButton: hihihihih " + jwt?.token)
                    if (status.isSuccessful) {
                        Toast.makeText(context, "Login successfully", Toast.LENGTH_SHORT).show()
                        Constant.USERNAME = user
                        isLoginSuccess.value = true
                    } else {
                        Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                        isLoginSuccess.value = false
                    }
                    /*val ticket = TicketData("1234", "17B11", "motor", "vuduc", "Truong Dinh Parking", "2023-05-01")
                    viewModelScope.launch {
                        try {
                            val status1: Response<List<TicketData>> = MarsApi.retrofitService.getTicket("vuduc")
                            val jwt = status1.body()
                            if (status1.isSuccessful) {
                                Toast.makeText(context, "xxvvxLogin successfully", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "xxxLogin failed", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: java.lang.Exception) {
                            Log.d("TAG", "send ticket : hhuhuhu " + e)
                        }
                    }*/
                } catch (e: java.lang.Exception) {
                    Log.d("TAG", "onPressButton: huhuhuuh " + e)
                    Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                    isLoginSuccess.value = false
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
                        isLoginSuccess.value = true
                    } else {
                        Toast.makeText(context, "Register failed", Toast.LENGTH_SHORT).show()
                        isLoginSuccess.value = false
                    }
                } catch (e: java.lang.Exception) {
                    Log.d("TAG", "onPressButton:  resgister huhuhuuh " + e)
                    Toast.makeText(context, "Register failed", Toast.LENGTH_SHORT).show()
                    isLoginSuccess.value = false
                }
            }
        }

    }
}