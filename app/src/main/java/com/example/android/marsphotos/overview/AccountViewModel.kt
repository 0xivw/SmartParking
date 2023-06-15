package com.example.android.marsphotos.overview

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.network.JwtData
import com.example.android.marsphotos.network.MarsApi
import kotlinx.coroutines.launch
import retrofit2.Response


class AccountViewModel : ViewModel() {
    private val _account = MutableLiveData<String>()
    val account: MutableLiveData<String>
        get() = _account

    private val _password = MutableLiveData<String>()
    val password: MutableLiveData<String>
        get() = _password


    init {

    }
    fun onPressButton(user:String, password:String) {
        viewModelScope.launch{
            try {
                Log.d("TAG", "onPressButton: 14-06 " + user + " " + password)
                val status : Response<JwtData> = MarsApi.retrofitService.login(user, password)
                val jwt = status.body()
                Log.d("TAG", "onPressButton: hihihihih " + jwt?.token)
            } catch (e : java.lang.Exception) {
                Log.d("TAG", "onPressButton: huhuhuuh " + e)
            }
        }
    }
}