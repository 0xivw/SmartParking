package com.example.android.marsphotos.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.moshi.Json

class TicketViewModel : ViewModel() {

    private val _idCard = MutableLiveData<String>()
    val idCard : MutableLiveData<String>
        get() = _idCard

    private val _license = MutableLiveData<String>()
    val license : MutableLiveData<String>
        get() = _license

    private val _type = MutableLiveData<String>()
    val type : MutableLiveData<String>
        get() = _type

    private val _username = MutableLiveData<String>()
    val username : MutableLiveData<String>
        get() = _username

    private val _parkingName = MutableLiveData<String>()
    val parkingName : MutableLiveData<String>
        get() = _parkingName

    private val _time = MutableLiveData<String>()
    val time : MutableLiveData<String>
        get() = _time

    init {

    }


}